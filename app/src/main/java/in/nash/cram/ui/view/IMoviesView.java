package in.nash.cram.ui.view;

import android.support.annotation.StringRes;

import java.util.ArrayList;

import in.nash.cram.model.Movie;

/**
 * Created by avinash on 8/15/15.
 */
public interface IMoviesView {

    void showProgressBar();

    void hideProgressBar();

    void setError(@StringRes int stringRes);

    void setMovies(ArrayList<Movie> movies);

}