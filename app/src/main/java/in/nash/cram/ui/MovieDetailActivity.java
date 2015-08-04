package in.nash.cram.ui;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URL;

import in.nash.cram.R;
import in.nash.cram.model.Movie;
import in.nash.cram.network.TmdbService;
import retrofit.RetrofitError;

/**
 * Created by Avinash Hindupur on 24/06/15.
 */
public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String MOVIE_POSITION = "movie_position";
    private String mMovieId;
    private Movie mMovie;
    private Context mContext;
    private LinearLayout mReviewsLayout;
    private LinearLayout mCastLayout;
    private LinearLayout mCrewLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        final int moviePosition = intent.getIntExtra(MOVIE_POSITION, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = MovieDetailActivity.this;

        mReviewsLayout = (LinearLayout) findViewById(R.id.reviews);
        mReviewsLayout.setOnClickListener(this);
        mCastLayout = (LinearLayout) findViewById(R.id.cast);
        mCastLayout.setOnClickListener(this);
        mCrewLayout = (LinearLayout) findViewById(R.id.crew);
        mCrewLayout.setOnClickListener(this);
        Movie movie = Globals.moviesList.get(moviePosition);
        mMovieId = movie.getId();
        new FetchMovieDetailsAsync().execute();

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
            case R.id.cast:
                intent = new Intent(MovieDetailActivity.this, PersonListActivity.class);
                intent.putExtra("fragment", "cast");
                intent.putExtra("id", mMovieId);
                startActivity(intent);
                break;
            case R.id.crew:
                intent = new Intent(MovieDetailActivity.this, PersonListActivity.class);
                intent.putExtra("fragment", "crew");
                intent.putExtra("id", mMovieId);
                startActivity(intent);
                break;
            case R.id.reviews:
                intent = new Intent(MovieDetailActivity.this, ReviewListActivity.class);
                intent.putExtra("id", mMovieId);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private class FetchMovieDetailsAsync extends AsyncTask<URL, Integer, Boolean> {


        protected Boolean doInBackground(URL... urls) {
            try {
                getMovieDetails();
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {

            String url = "http://image.tmdb.org/t/p/w780" + mMovie.getBackdropPath();
            String movieTitle = mMovie.getTitle();
            String movieOverview = mMovie.getOverview();

            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(movieTitle);

            TextView overview = (TextView) findViewById(R.id.summary);
            overview.setText(movieOverview);
            final ImageView imageView = (ImageView) findViewById(R.id.movie_backdrop);
            Picasso.with(mContext)
                    .load(url)
                    .into(imageView);

            setMovieDetails(mMovie);
        }
    }

    private void getMovieDetails() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);
        mMovie = tmdb.fetchMovieDetails(mMovieId);


    }

    private void setMovieDetails(Movie movie) {
        TextView released = (TextView) findViewById(R.id.released);
        TextView runtime = (TextView) findViewById(R.id.runtime);
        TextView certification = (TextView) findViewById(R.id.certification);
        TextView genres = (TextView) findViewById(R.id.genres);
        TextView budget = (TextView) findViewById(R.id.budget);
        TextView language = (TextView) findViewById(R.id.language);

        released.setText(movie.getReleaseDate());
        runtime.setText("" + movie.getRuntime());
        //certification.setText(movie.setCertification);
        //genres.setText(movie.getGenres);
        budget.setText("" + movie.getRevenue());
        language.setText(movie.getOriginalLanguage());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
