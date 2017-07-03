package it.app.dapurku;

import java.util.List;

import it.app.dapurku.data.ResepSuggestion;
import it.app.dapurku.model.Detail;
import it.app.dapurku.model.DiscoverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Islam on 08/05/2016.
 */
public interface Api {
    @GET("dapurku/resep")
    Call<List<DiscoverResponse>>discoverResep();

    @GET("dapurku/resep/{id}")
    Call<List<Detail>>discoverResepDetail(@Path("id") int id_resep);

    @GET("dapurku/gambar/{id}")
    Call<DiscoverResponse>discoverImage(@Path("id") int id_resep);

    @GET("dapurku/search/{pattern}")
    Call<List<DiscoverResponse>> discoverResepSearch(@Path("pattern") String pattern);
}
