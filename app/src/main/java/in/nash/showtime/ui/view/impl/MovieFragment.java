package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.adapter.MovieGridAdapter;
import in.nash.showtime.model.Movie;
import in.nash.showtime.ui.presenter.IMoviesPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IMoviesView;
import in.nash.showtime.utils.SdkUtil;
import in.nash.showtime.utils.SpacesItemDecoration;
import in.nash.showtime.utils.TransitionHelper;

/**
 * Created by avinash on 18/06/15.
 */
public class MovieFragment extends Fragment implements IMoviesView {

    //region View variables
    @Bind(R.id.movies_grid)
    RecyclerView mRecyclerView;
    @BindDimen(R.dimen.spacing)
    int spacingInPixels;
    //endregion

    private MovieType mMovieType;
    private Movie mMovie;
    private ProgressBar mProgressBar;
    private IMoviesPresenter mMoviePresenter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies_grid, container, false);
        ButterKnife.bind(this, rootView);

        displayGridView();

        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        initPresenter();
        return rootView;
    }

    private void displayListView() {
        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void displayGridView() {
        mLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initPresenter() {

        mMoviePresenter = PresenterFactory.createMoviePresenter(this);
        mMoviePresenter.queryMovies(getMovieCategory());

    }

    public void refresh() {
        mMoviePresenter.queryMovies(getMovieCategory());
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setError(@StringRes int stringRes) {
        Snackbar.make(mRecyclerView, stringRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setMovies(final List<Movie> movies) {
        mRecyclerView.setAdapter(new MovieGridAdapter(getActivity(), movies, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mRecyclerView.getChildAdapterPosition(v);
                Movie movie = movies.get(position);
                mMovie = movie;
                // MovieDetailActivity.navigateTo(getActivity(), movie.getId());

                Activity activity = getActivity();
                startMovieDetailActivityWithTransition(activity, v,
                        movie);

            }
        }));

    }

    public MovieType getMovieCategory() {
        return mMovieType;
    }

    public void setMovieCategory(MovieType category) {
        mMovieType = category;
    }

    public enum MovieType {
        PLAYING("playing"),
        TOP("top"),
        UPCOMING("upcoming"),
        SEEN("seen"),
        WISHLIST("wishlist");

        private final String movieType;

        MovieType(String type) {
            movieType = type;
        }

        public String getMovieType() {
            return movieType;
        }
    }

    public static MovieFragment getInstance(MovieType type) {
        MovieFragment movieFragment = new MovieFragment();
        movieFragment.setMovieCategory(type);
        return movieFragment;
    }

    private void startMovieDetailActivityWithTransition(Activity activity, View toolbar,
                                                        Movie movie) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));
        if (SdkUtil.hasLollipop()) {
            ActivityOptions sceneTransitionAnimation = ActivityOptions
                    .makeSceneTransitionAnimation(activity, pairs);
            // Start the activity with the participants, animating from one to the other.
            final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
            activity.startActivity(MovieDetailActivity.getStartIntent(activity, movie), transitionBundle);
        } else {
            activity.startActivity(MovieDetailActivity.getStartIntent(activity, movie));
        }
    }

}
