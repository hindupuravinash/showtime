package in.nash.cram;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by avinash on 19/06/15.
 */
public final class TmdbService {

    public static final String API_URL = "https://api.themoviedb.org/3";
    public static final String PARAM_API_KEY = "api_key";

    private String apiKey = PARAM_API_KEY;
    private RestAdapter restAdapter;

    public interface Tmdb {

        @GET("/movie/top_rated")
        MovieResponse movies();
    }

    protected RestAdapter.Builder newRestAdapterBuilder() {
        return new RestAdapter.Builder();
    }

    protected RestAdapter getRestAdapter() {
        if (this.restAdapter == null) {
            RestAdapter.Builder builder = this.newRestAdapterBuilder();
            builder.setEndpoint(API_URL);
            builder.setRequestInterceptor(new RequestInterceptor() {

                public void intercept(RequestFacade requestFacade) {
                    requestFacade.addQueryParam("api_key", TmdbService.this.apiKey);
                }
            });

            this.restAdapter = builder.build();
        }

        return this.restAdapter;
    }

    class MovieResponse {

        @SerializedName("results")
        public ArrayList<Movie> mMovies;

    }
}
