package in.nash.cram.ui.presenter.impl;

import java.util.List;

import in.nash.cram.network.TmdbService;
import in.nash.cram.ui.presenter.IMoviesPresenter;
import in.nash.cram.ui.view.IMoviesView;
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
    public void queryMovies(String category) {


    }
}
