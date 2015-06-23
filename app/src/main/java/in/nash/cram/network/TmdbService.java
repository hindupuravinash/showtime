package in.nash.cram.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.nash.cram.model.Movie;
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
        MovieResponse fetchTopMovies();

        @GET("/movie/popular")
        MovieResponse fetchPopularMovies();

        @GET("/movie/upcoming")
        MovieResponse fetchUpcomingMovies();

        @GET("/movie/now_playing")
        MovieResponse fetchNowPlayingMovies();
    }

    protected RestAdapter.Builder newRestAdapterBuilder() {
        return new RestAdapter.Builder();
    }

    public RestAdapter getRestAdapter() {
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

    public class MovieResponse {

        @SerializedName("results")
        public ArrayList<Movie> mMovies;

    }
}
