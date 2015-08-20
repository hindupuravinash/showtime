package in.nash.cram.network;

import in.nash.cram.model.Person;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by avinash on 7/31/15.
 */
public interface PersonService {

    @GET("/person/{id}")
    Observable<Person> fetchPersonDetails(@Path("id") String id);
}
