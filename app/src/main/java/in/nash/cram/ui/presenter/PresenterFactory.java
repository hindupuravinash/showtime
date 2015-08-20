package in.nash.cram.ui.presenter;

import in.nash.cram.ui.presenter.impl.MovieDetailPresenterImpl;
import in.nash.cram.ui.presenter.impl.MoviesPresenterImpl;
import in.nash.cram.ui.presenter.impl.PersonListPresenterImpl;
import in.nash.cram.ui.view.IMovieDetailView;
import in.nash.cram.ui.view.IMoviesView;
import in.nash.cram.ui.view.IPersonListView;
import in.nash.cram.ui.view.impl.MovieDetailActivity;
import in.nash.cram.ui.view.impl.PersonListFragment;

/**
 * Created by avinash on 8/5/15.
 */
public class PresenterFactory {

    public static IMoviesPresenter createMoviePresenter(IMoviesView moviesView){
        return new MoviesPresenterImpl(moviesView);
    }

    public static IMovieDetailPresenter createMovieDetailPresenter(IMovieDetailView movieDetailView) {
        return new MovieDetailPresenterImpl(movieDetailView);
    }

    public static IPersonListPresenter createPersonListPresenter(IPersonListView personListView) {

        return new PersonListPresenterImpl(personListView);

    }
}
