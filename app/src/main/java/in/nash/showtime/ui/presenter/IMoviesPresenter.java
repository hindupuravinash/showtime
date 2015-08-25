package in.nash.showtime.ui.presenter;

import in.nash.showtime.ui.view.impl.MovieFragment;

/**
 * Created by avinash on 8/18/15.
 */
public interface IMoviesPresenter {
    void queryMovies(MovieFragment.MovieType category);
}
