package in.nash.cram.ui.presenter;

import in.nash.cram.ui.presenter.impl.MovieDetailPresenterImpl;
import in.nash.cram.ui.presenter.impl.MoviesPresenterImpl;
import in.nash.cram.ui.presenter.impl.PersonListPresenterImpl;
import in.nash.cram.ui.presenter.impl.ReviewListPresenterImpl;
import in.nash.cram.ui.view.IMovieDetailView;
import in.nash.cram.ui.view.IMoviesView;
import in.nash.cram.ui.view.IPersonListView;
import in.nash.cram.ui.view.IReviewsListView;
import in.nash.cram.ui.view.impl.MovieDetailActivity;
import in.nash.cram.ui.view.impl.PersonListFragment;
import in.nash.cram.ui.view.impl.ReviewListFragment;

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

    public static IReviewsListPresenter createReviewsListPresenter(IReviewsListView reviewsListView) {
        return new ReviewListPresenterImpl(reviewsListView);
    }
}
