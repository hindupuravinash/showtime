package in.nash.cram.ui.presenter.impl;

import in.nash.cram.model.Movie;
import in.nash.cram.network.Tmdb;
import in.nash.cram.ui.presenter.IMovieDetailPresenter;
import in.nash.cram.ui.view.IMovieDetailView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/20/15.
 */
public class MovieDetailPresenterImpl implements IMovieDetailPresenter{

    private final IMovieDetailView mMovieDetailView;

    public MovieDetailPresenterImpl(IMovieDetailView movieDetailView) {
        this.mMovieDetailView = movieDetailView;

    }

    @Override
    public void fetchMovie(String id) {
        Tmdb tmdb = new Tmdb();

        Observable<Movie> movieObservable = tmdb.moviesService().fetchMovieDetails(id);
        movieObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {

                        mMovieDetailView.setMovie(movie);

                    }
                });

    }

    @Override
    public void showMovieDetails() {

    }
}
