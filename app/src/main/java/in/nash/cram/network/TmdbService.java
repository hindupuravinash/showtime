package in.nash.cram.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.nash.cram.Secrets;
import in.nash.cram.model.Movie;
import in.nash.cram.model.Person;
import in.nash.cram.model.Review;
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

        @GET("/movie/{id}/credits")
        CreditResponse fetchMovieCredits(@Path("id") String id);

        @GET("/movie/{id}/reviews")
        ReviewResponse fetchMovieReviews(@Path("id")String id);

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

    public class ReviewResponse {
        @SerializedName("id")
        public String id;

        @SerializedName("page")
        public int page;

        @SerializedName("results")
        public ArrayList<Review> reviews;

        @SerializedName("total_pages")
        public int totalPages;

        @SerializedName("total_results")
        public int totalResults;
    }

    public class CreditResponse {
        @SerializedName("cast")
        public ArrayList<Person> mCast;

        @SerializedName("crew")
        public ArrayList<Person> mCrew;
    }

}
