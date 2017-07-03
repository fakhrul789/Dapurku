package it.app.dapurku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.util.ArrayList;
import java.util.List;

import it.app.dapurku.adapter.GridAdapter;
import it.app.dapurku.config.ResepConst;
import it.app.dapurku.data.FlagPrefer;
import it.app.dapurku.model.DiscoverResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    FloatingSearchView mSearchView;
    GridView mGridView;
    GridAdapter mAdapter;

    private List<DiscoverResponse>mResep = new ArrayList<>();

    private Call<List<DiscoverResponse>> mDiscoverCall = null;

    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadResep() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FlagPrefer.getFlag(getContext()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        mDiscoverCall = api.discoverResep();

        if(mDiscoverCall!=null){
            mDiscoverCall.enqueue(new Callback<List<DiscoverResponse>>() {
                @Override
                public void onResponse(Call<List<DiscoverResponse>> call, Response<List<DiscoverResponse>> response) {
                    mResep.addAll(response.body());
                    Log.d("LASDKJFASDJF",response.toString());
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<DiscoverResponse>> call, Throwable t) {
                    Toast.makeText(getActivity(),R.string.failed_fetch_resep,Toast.LENGTH_SHORT);
                    Log.e(getString(R.string.failed_fetch_resep),t.getMessage());
                }
            });
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        loadResep();
        mGridView = (GridView) view.findViewById(R.id.grid_main);

        mAdapter = new GridAdapter(this.getContext(),mResep);
        mGridView.setAdapter(mAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int movieID = mResep.get(position).getResepId();
                Intent i = new Intent(getContext(),DetailActivity.class);
                i.putExtra(ResepConst.BUNDLE_RESEP_ID,mResep.get(position).getResepId());
                Log.d(MainFragment.class.getSimpleName(),String.valueOf(movieID));
                startActivity(i);
            }
        });

        return view;
    }
}
