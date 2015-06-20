package in.nash.cram.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by avinash on 19/06/15.
 */
public class Movie {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterUrl() {
        return posterPath;
    }

    public void setPosterUrl(String posterPath) {
        this.posterPath = posterPath;
    }
}
