package in.nash.cram.network;

import in.nash.cram.model.Person;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by avinash on 7/31/15.
 */
public interface PersonService {

    @GET("/person/{id}")
    Person fetchPersonDetails(@Path("id") String id);

}
