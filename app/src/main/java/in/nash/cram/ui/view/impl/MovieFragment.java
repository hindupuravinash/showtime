package in.nash.cram.ui.view.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import in.nash.cram.R;
import in.nash.cram.adapter.MovieGridAdapter;
import in.nash.cram.model.Movie;
import in.nash.cram.network.TmdbService;
import in.nash.cram.ui.Globals;
import in.nash.cram.ui.view.IMoviesView;
import in.nash.cram.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 18/06/15.
 */
public class MovieFragment extends Fragment implements IMoviesView {

    private MovieType mMovieType;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Movie> mMovies;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movies_grid, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.movies_grid);

        mLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new FetchTopMoviesAsync().execute();

        return rootView;
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
    public void setMovies(ArrayList<Movie> movies) {
        mAdapter = new MovieGridAdapter(getActivity(), movies, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mRecyclerView.getChildAdapterPosition(v);
                Movie movie = mMovies.get(position);
                MovieDetailActivity.navigateTo(getActivity(), movie.getId());
            }
        });
    }

    private class FetchTopMoviesAsync extends AsyncTask<URL, Integer, Boolean> {

        protected Boolean doInBackground(URL... urls) {
            try {
                getMovies(getMovieCategory());
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {
            setMovies(Globals.moviesList);
            mRecyclerView.setAdapter(mAdapter);
            mMovies = Globals.moviesList;
            mRecyclerView.setAdapter(new MovieGridAdapter(getActivity(), Globals.moviesList, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mRecyclerView.getChildAdapterPosition(v);
                    Movie movie = mMovies.get(position);
                    MovieDetailActivity.navigateTo(getActivity(), movie.getId());
                }
            }));
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        }
    }

    private void getMovies(MovieType category) {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        TmdbService.MovieResponse movies;
        switch (category) {

            case TOP:
                movies = tmdb.fetchTopMovies();
                break;
            case UPCOMING:
                movies = tmdb.fetchUpcomingMovies();
                break;
            case PLAYING:
                movies = tmdb.fetchNowPlayingMovies();
                break;
            default:
                movies = tmdb.fetchPopularMovies();

        }

        Globals.moviesList = movies.mMovies;

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
        UPCOMING("upcoming");

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
}
