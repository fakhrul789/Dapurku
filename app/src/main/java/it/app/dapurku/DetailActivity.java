package it.app.dapurku;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.app.dapurku.config.ResepConst;
import it.app.dapurku.data.FlagPrefer;
import it.app.dapurku.model.Detail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    public static final String TAG = DetailActivity.class.getSimpleName();

    ShareActionProvider mShareActionProvider;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView headimage;
    TextView bumbu_tambahan;
    TextView bahan_text;
    TextView caramasak_text;
    Toolbar toolbar;
    Context mContext;

    private String bahan_tambah = "";
    private String resep_nama = "";
    private String resep_bahan = "";
    private String resep_masak = "";
    private String resep_gambar = "";

    List<Detail> mDetail = new ArrayList<>();
    Call<List<Detail>> mResepDetail = null;
    Context context;

    int movieId = 0;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mContext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;

        if(getIntent()!=null){
            movieId = getIntent().getIntExtra(ResepConst.BUNDLE_RESEP_ID,ResepConst.DEFAULT_VALUE);
            Log.d("DIDIDIDIDIDI",String.valueOf(movieId));
        }

        bumbu_tambahan = (TextView) findViewById(R.id.tips_text);
        bahan_text = (TextView) findViewById(R.id.bahan_text);
        caramasak_text = (TextView) findViewById(R.id.caramasa_text);

        CardView tambahan = (CardView) findViewById(R.id.tips);
        CardView bahan = (CardView) findViewById(R.id.bahan);
        CardView caramasak = (CardView) findViewById(R.id.caramasak);
        headimage = (ImageView)findViewById(R.id.headimage);

        toolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        fetchResepDetail(movieId);
    }

    private void fetchResepDetail(final int movieId) {
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(FlagPrefer.getFlag(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        mResepDetail = api.discoverResepDetail(movieId);

        mResepDetail.enqueue(new Callback<List<Detail>>() {
            @Override
            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {
                mDetail.addAll(response.body());
                resep_bahan = String.valueOf(mDetail.get(0).getResepBahan());
                resep_gambar = String.valueOf(mDetail.get(0).getResepGambar());
                resep_masak = String.valueOf(mDetail.get(0).getResepCaraMasak());
                bahan_tambah = String.valueOf(mDetail.get(0).getResepBahanHalus());
                resep_nama = String.valueOf(mDetail.get(0).getResepNama());

                if(bahan_tambah == null){
                    bumbu_tambahan.setVisibility(View.GONE);
                }

                collapsingToolbarLayout.setTitle(resep_nama);
                Picasso.with(context).load(resep_gambar)
                        .into(headimage);
                bumbu_tambahan.setText(bahan_tambah);
                bahan_text.setText(resep_bahan);
                caramasak_text.setText(resep_masak);

                Log.d("HASIL",String.valueOf(mDetail.get(0).getResepGambar()));
            }

            @Override
            public void onFailure(Call<List<Detail>> call, Throwable t) {
                Toast.makeText(mContext,"gagal mengambil resep detail",Toast.LENGTH_SHORT).show();
                Log.e("ERROR COK",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
    }

    private void setShareIntent(Intent intent){
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(intent);
        }
    }
}
