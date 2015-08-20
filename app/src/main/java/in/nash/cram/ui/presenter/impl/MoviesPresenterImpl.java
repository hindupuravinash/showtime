package in.nash.cram.ui.presenter.impl;

import android.util.Log;

import java.util.List;

import in.nash.cram.R;
import in.nash.cram.model.Movie;
import in.nash.cram.network.Tmdb;
import in.nash.cram.network.TmdbService;
import in.nash.cram.ui.presenter.IMoviesPresenter;
import in.nash.cram.ui.view.IMoviesView;
import in.nash.cram.ui.view.impl.MovieFragment;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/18/15.
 */
public class MoviesPresenterImpl implements IMoviesPresenter {


    private IMoviesView mMoviesView;

    public MoviesPresenterImpl(IMoviesView moviesView) {
        mMoviesView = moviesView;
    }

    @Override
    public void queryMovies(MovieFragment.MovieType category) {

        Log.d("Category", category.getMovieType());
        Observable<TmdbService.MovieResponse> movies;
        Tmdb tmdb = new Tmdb();

        switch (category) {

            case TOP:
                movies = tmdb.moviesService().fetchTopMovies();
                break;
            case UPCOMING:
                movies = tmdb.moviesService().fetchUpcomingMovies();
                break;
            case PLAYING:
                movies = tmdb.moviesService().fetchNowPlayingMovies();
                break;
            default:
                movies = tmdb.moviesService().fetchPopularMovies();

        }

        movies.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TmdbService.MovieResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        // TODO: Log into Crashlytics
                        mMoviesView.setError(R.string.something_went_wrong);
                        Log.d("movies", "Error");

                    }

                    @Override
                    public void onNext(TmdbService.MovieResponse movies) {

                        Log.d("movies", "" + movies.mMovies.size());
                        mMoviesView.setMovies(movies.mMovies);
                    }
                });

    }
}
