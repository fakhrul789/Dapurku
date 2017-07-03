package it.app.dapurku.data;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import it.app.dapurku.Api;
import it.app.dapurku.config.ResepConst;
import it.app.dapurku.model.DiscoverResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Islam on 12/05/2016.
 */
public class DataHelper {
    private static List<DiscoverResponse> sDiscover = new ArrayList<>();

    public interface OnFindResultsListener {

        void onResults(List<ResepSuggestion> results);
    }

    public static List<ResepSuggestion> getHistory(Context context, int count) {
        List<ResepSuggestion> suggestionList = new ArrayList<>();

        ResepSuggestion colorSuggestion;
        for (int i = 0; i < count; i++) {
            colorSuggestion = new ResepSuggestion(sDiscover.get(i));
            colorSuggestion.setmIsHistory(true);
            suggestionList.add(colorSuggestion);
        }

        return suggestionList;
    }

    public static void find(Context context, String query, final OnFindResultsListener listener) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FlagPrefer.getFlag(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final Api api = retrofit.create(Api.class);
        Call<List<DiscoverResponse>> mCoeg = api.discoverResepSearch(query);
        if(mCoeg!=null){
            mCoeg.enqueue(new Callback<List<DiscoverResponse>>() {
                @Override
                public void onResponse(Call<List<DiscoverResponse>> call, Response<List<DiscoverResponse>> response) {

                    sDiscover.addAll(response.body());
                }

                @Override
                public void onFailure(Call<List<DiscoverResponse>> call, Throwable t) {

                }
            });
        }

        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {


                List<ResepSuggestion> suggestionList = new ArrayList<>();

                if (!(constraint == null || constraint.length() == 0)) {

                    for (DiscoverResponse color : sDiscover) {

                        if (color.getResepNama().toUpperCase().startsWith(constraint.toString().toUpperCase())){
                            suggestionList.add(new ResepSuggestion(color));
                        }
                    }

                }

                FilterResults results = new FilterResults();
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {

                if (listener != null)
                    listener.onResults((List<ResepSuggestion>) results.values);
            }
        }.filter(query);

    }
}
