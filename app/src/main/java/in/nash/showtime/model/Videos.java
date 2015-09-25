package in.nash.showtime.model;

import java.util.List;

/**
 * Created by avinash on 9/25/15.
 */
public class Videos {

    public Integer id;
    public List<Video> results;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }
}
