package in.nash.showtime.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.nash.showtime.Secrets;
import in.nash.showtime.model.Person;
import in.nash.showtime.model.Review;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by avinash on 19/06/15.
 */
public final class TmdbService {

    public static final String API_URL = "https://api.themoviedb.org/3";
    public static final String PARAM_API_KEY = Secrets.PARAM_API_KEY;

    private String apiKey = PARAM_API_KEY;
    private RestAdapter restAdapter;

    public interface Tmdb {

        @GET("/person/{id}")
        Person fetchPersonDetails(@Path("id") String id);

        @GET("/review/{id}")
        Review fetchMovieReview(@Path("id") String id);
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

    public class CreditResponse {
        @SerializedName("cast")
        public ArrayList<Person> mCast;

        @SerializedName("crew")
        public ArrayList<Person> mCrew;
    }

}
