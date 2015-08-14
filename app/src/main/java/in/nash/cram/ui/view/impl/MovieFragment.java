package in.nash.cram.ui.view.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;

import in.nash.cram.R;
import in.nash.cram.adapter.MovieGridAdapter;
import in.nash.cram.network.TmdbService;
import in.nash.cram.ui.Globals;
import in.nash.cram.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 18/06/15.
 */
public class MovieFragment extends Fragment {

    private MovieType mMovieType;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

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
            mAdapter = new MovieGridAdapter(Globals.moviesList);
            mRecyclerView.setAdapter(mAdapter);
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

    public MovieType getMovieCategory(){
        return mMovieType;
    }

    public void setMovieCategory(MovieType category){
        mMovieType = category;
    }

    public enum MovieType{
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

    public static MovieFragment getInstance(MovieType type){
        MovieFragment movieFragment = new MovieFragment();
        movieFragment.setMovieCategory(type);
        return movieFragment;
    }
}
