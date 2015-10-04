package in.nash.showtime.ui.presenter.impl;

import android.util.Log;

import in.nash.showtime.R;
import in.nash.showtime.network.MoviesResponse;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IMoviesPresenter;
import in.nash.showtime.ui.view.IMoviesView;
import in.nash.showtime.ui.view.impl.MovieFragment;
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
        Observable<MoviesResponse> movies;
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
                .subscribe(new Subscriber<MoviesResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        // TODO: Log into Crashlytics
                        mMoviesView.setError(R.string.something_went_wrong);
                        Log.e("Error", e.getMessage());

                    }

                    @Override
                    public void onNext(MoviesResponse moviesResponse) {

                        mMoviesView.setMovies(moviesResponse.results);
                    }
                });

    }
}
