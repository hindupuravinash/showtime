package in.nash.showtime.model;

import java.util.ArrayList;
import java.util.List;

import in.nash.showtime.network.MoviesResponse;
import in.nash.showtime.network.ReviewsResponse;

/**
 * Created by avinash on 19/06/15.
 */

public class Movie {

    private String id;

    private String original_language;

    private String original_title;

    private String release_date;

    public Integer budget;

    public List<Genre> genres;

    private String popularity;

    private String title;

    private String backdrop_path;

    private String poster_path;

    private String overview;

    private Integer revenue;

    private int runtime;

    private double vote_average;

    private int vote_count;

    public Credits credits;

    public Videos videos;

    public MoviesResponse similar;

    public ReviewsResponse reviews;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public String getPosterUrl() {
        return poster_path;
    }

    public void setPosterUrl(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }


    public String getGenre(){
        if(genres.size() >0){
            ArrayList<String> genreStringList = new ArrayList<>();

            for(Genre genre: genres){
                genreStringList.add(genre.name);
            }
            return android.text.TextUtils.join(", ", genreStringList);

        }
        return "";
    }

}
