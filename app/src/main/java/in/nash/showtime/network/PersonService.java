package in.nash.showtime.network;

import in.nash.showtime.model.Person;
import retrofit.Result;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by avinash on 7/31/15.
 */
public interface PersonService {

    @GET("person/{id}")
    Observable<Result<Person>> fetchPersonDetails(@Path("id") String id);
}
