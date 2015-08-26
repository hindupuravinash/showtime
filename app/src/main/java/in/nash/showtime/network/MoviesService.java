package in.nash.showtime.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import in.nash.showtime.model.Movie;
import in.nash.showtime.model.Person;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by avinash on 7/20/15.
 */
public interface MoviesService {

    @GET("/movie/top_rated")
    Observable<MoviesResponse> fetchTopMovies();

    @GET("/movie/popular")
    Observable<MoviesResponse> fetchPopularMovies();

    @GET("/movie/upcoming")
    Observable<MoviesResponse> fetchUpcomingMovies();

    @GET("/movie/now_playing")
    Observable<MoviesResponse> fetchNowPlayingMovies();

    @GET("/movie/{id}/similar")
    Observable<MoviesResponse> fetchSimilarMovies(@Path("id") String id);

    @GET("/movie/{id}/credits")
    Observable<CreditResponse> fetchMovieCredits(@Path("id") String id);

    @GET("/movie/{id}/reviews")
    Observable<ReviewsResponse> fetchMovieReviews(@Path("id") String id);

    @GET("/movie/{id}")
    Observable<Movie> fetchMovieDetails(@Path("id") String id);

    class CreditResponse {
        @SerializedName("cast")
        public ArrayList<Person> mCast;

        @SerializedName("crew")
        public ArrayList<Person> mCrew;
    }

}
