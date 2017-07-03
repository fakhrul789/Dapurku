package it.app.dapurku;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.io.FilterReader;
import java.util.ArrayList;
import java.util.List;

import it.app.dapurku.adapter.GridAdapter;
import it.app.dapurku.config.ResepConst;
import it.app.dapurku.data.DataHelper;
import it.app.dapurku.data.FlagPrefer;
import it.app.dapurku.data.ResepSuggestion;
import it.app.dapurku.model.Detail;
import it.app.dapurku.model.DiscoverResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    GridView mGridView;
    GridAdapter mAdapter;
    FloatingSearchView mSearchView;
    Fragment fragment = null;
    AHBottomNavigation mBottomBar;
    FloatingSearchView floatingSearchView;

    Context context;

    List<DiscoverResponse> mMboh = new ArrayList<>();
    List<ResepSuggestion> mResult = new ArrayList<>();
    Call<List<DiscoverResponse>> mDiscover = null;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchView = (FloatingSearchView)findViewById(R.id.floating_search_view);

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if(oldQuery.equals("") && newQuery.equals("")){
                    Log.d("SEARCH","null");
                    mSearchView.clearSuggestions();
                }else if(!oldQuery.equals("") && !newQuery.equals("")){
                    DataHelper.find(context, newQuery, new DataHelper.OnFindResultsListener() {
                        @Override
                        public void onResults(List<ResepSuggestion> results) {
                            mSearchView.swapSuggestions(results);
                        }
                    });
                }
            }
        });

        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                final EditText ip = (EditText)dialog.findViewById(R.id.ip);
                final Button save = (Button)dialog.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FlagPrefer.setIpServer(context,ip.getText().toString());
                        reload();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                ResepSuggestion resepSuggestion = (ResepSuggestion) searchSuggestion;
                int movieID = resepSuggestion.getId();
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra(ResepConst.BUNDLE_RESEP_ID,movieID);
                Log.d(MainFragment.class.getSimpleName(),String.valueOf(movieID));
                startActivity(i);
            }

            @Override
            public void onSearchAction() {
                Toast.makeText(context,"Pencarian Test", Toast.LENGTH_SHORT).show();
            }
        });

        mBottomBar = (AHBottomNavigation)findViewById(R.id.bottom_navigation);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home",R.drawable.ic_home_white_24dp,
                Color.parseColor("#006064"));
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Favorite",R.drawable.ic_favorite_white_24dp,Color.parseColor("#00886A"));
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Recent",R.drawable.ic_history_white_24dp,Color.parseColor("#8B6B62"));

        mBottomBar.addItem(item1);
        mBottomBar.addItem(item2);
        mBottomBar.addItem(item3);

        // Set background color
        mBottomBar.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));

// Change colors
        mBottomBar.setAccentColor(Color.parseColor("#F63D2B"));
        mBottomBar.setInactiveColor(Color.parseColor("#747474"));

// Use colored navigation with circle reveal effect
        mBottomBar.setColored(true);

// Set current item programmatically
        mBottomBar.setCurrentItem(0);

        fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main,fragment,"main").commit();


        context = this;
        //floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mGridView = (GridView)findViewById(R.id.grid_main);
    }

    private void reload() {
        fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        if(fragment instanceof MainFragment){
            getSupportFragmentManager().beginTransaction()
                    .detach(fragment)
                    .attach(fragment)
                    .commit();
        }else{
            Log.d("Fragment","error");
        }
    }
}
