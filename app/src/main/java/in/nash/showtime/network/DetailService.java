package in.nash.showtime.network;

import in.nash.showtime.model.Person;
import in.nash.showtime.model.Review;
import retrofit.Result;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by avinash on 8/27/15.
 */
public interface DetailService {

    @GET("/person/{id}")
    Observable<Result<Person>> fetchPersonDetails(@Path("id") String id);

    @GET("/review/{id}")
    Observable<Result<Review>> fetchMovieReview(@Path("id") String id);
}
