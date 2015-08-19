package in.nash.cram.ui.presenter;

import in.nash.cram.ui.presenter.impl.MoviesPresenterImpl;
import in.nash.cram.ui.view.IMoviesView;

/**
 * Created by avinash on 8/5/15.
 */
public class PresenterFactory {

    public static IMoviesPresenter createMoviePresenter(IMoviesView moviesView){
        return new MoviesPresenterImpl(moviesView);
    }
}
