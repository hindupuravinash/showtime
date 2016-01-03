package in.nash.showtime.ui.presenter;

import in.nash.showtime.ui.presenter.impl.MovieDetailPresenterImpl;
import in.nash.showtime.ui.presenter.impl.MoviesPresenterImpl;
import in.nash.showtime.ui.presenter.impl.PersonDetailPresenterImpl;
import in.nash.showtime.ui.presenter.impl.PersonListPresenterImpl;
import in.nash.showtime.ui.presenter.impl.ReviewDetailPresenterImpl;
import in.nash.showtime.ui.presenter.impl.ReviewListPresenterImpl;
import in.nash.showtime.ui.view.IMovieDetailView;
import in.nash.showtime.ui.view.IMoviesView;
import in.nash.showtime.ui.view.IPersonDetailView;
import in.nash.showtime.ui.view.IPersonListView;
import in.nash.showtime.ui.view.IReviewDetailView;
import in.nash.showtime.ui.view.IReviewsListView;

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

    public static IPersonListPresenter createPersonListPresenter(IPersonListView personListView, String movieId) {

        return new PersonListPresenterImpl(personListView, movieId);

    }

    public static IReviewsListPresenter createReviewsListPresenter(IReviewsListView reviewsListView) {
        return new ReviewListPresenterImpl(reviewsListView);
    }

    public static IReviewDetailPresenter createReviewDetailPresenter(IReviewDetailView reviewDetailView) {
        return new ReviewDetailPresenterImpl(reviewDetailView);
    }

    public static IPersonDetailPresenter createPersonDetailPresenter(IPersonDetailView personDetailView) {
        return new PersonDetailPresenterImpl(personDetailView);
    }
}
