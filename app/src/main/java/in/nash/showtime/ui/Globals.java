package in.nash.showtime.ui;

import java.util.ArrayList;

import in.nash.showtime.model.Movie;

/**
 * Created by avinash on 21/06/15.
 */
public class Globals {

    public static ArrayList<Movie> moviesList = new ArrayList<>();

    public static final int CACHE_MAX_AGE__NETWORK_CONNECTED = 10;
    public static final int SECONDS_IN_MIN = 60;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int HOURS_IN_DAY = 24;
    public static final int CACHE_VALID_DAYS = 30;

    public static final String CACHE_DIRECTORY = "responses";

    public static final int MAX_PREVIEW_LENGTH = 3;
}
