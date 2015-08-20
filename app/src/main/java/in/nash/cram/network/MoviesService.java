package in.nash.cram.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.nash.cram.model.Movie;
import in.nash.cram.model.Person;
import in.nash.cram.model.Review;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by avinash on 7/20/15.
 */
public interface MoviesService {

    @GET("/movie/top_rated")
    Observable<MovieResponse> fetchTopMovies();

    @GET("/movie/popular")
    Observable<MovieResponse> fetchPopularMovies();

    @GET("/movie/upcoming")
    Observable<MovieResponse> fetchUpcomingMovies();

    @GET("/movie/now_playing")
    Observable<MovieResponse> fetchNowPlayingMovies();

    @GET("/movie/{id}/similar")
    Observable<MovieResponse> fetchSimilarMovies(@Path("id") String id);

    @GET("/movie/{id}/credits")
    Observable<CreditResponse> fetchMovieCredits(@Path("id") String id);

    @GET("/movie/{id}/reviews")
    Observable<ReviewResponse> fetchMovieReviews(@Path("id") String id);

    @GET("/movie/{id}")
    Observable<Movie> fetchMovieDetails(@Path("id") String id);

    class ReviewResponse {
        @SerializedName("id")
        public String id;

        @SerializedName("page")
        public int page;

        @SerializedName("results")
        public ArrayList<Review> mReviews;

        @SerializedName("total_pages")
        public int totalPages;

        @SerializedName("total_results")
        public int totalResults;
    }

    class CreditResponse {
        @SerializedName("cast")
        public ArrayList<Person> mCast;

        @SerializedName("crew")
        public ArrayList<Person> mCrew;
    }

    class MovieResponse {

        @SerializedName("results")
        public ArrayList<Movie> mMovies;

    }

}
