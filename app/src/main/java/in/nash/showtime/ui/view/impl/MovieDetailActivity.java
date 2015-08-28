package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.nash.showtime.R;
import in.nash.showtime.model.Movie;
import in.nash.showtime.ui.presenter.IMovieDetailPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IMovieDetailView;

/**
 * Created by Avinash Hindupur on 24/06/15.
 */
public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView, View.OnClickListener {

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
        mMovieId = intent.getStringExtra("id");

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

        initPresenter();
    }

    private void initPresenter() {

        IMovieDetailPresenter movieDetailPresenter = PresenterFactory.createMovieDetailPresenter(this);
        movieDetailPresenter.fetchMovie(mMovieId);

    }

    public static Intent getStartIntent(Context context, Movie movie) {
        Intent starter = new Intent(context, MovieDetailActivity.class);
        starter.putExtra("id", movie.getId());
        return starter;
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

    public static void navigateTo(Activity fromActivity, String movieId) {
        Intent intent = new Intent(fromActivity, MovieDetailActivity.class);
        intent.putExtra("id", movieId);
        fromActivity.startActivity(intent);
    }

    @Override
    public void setMovie(Movie movie) {
        String url = "http://image.tmdb.org/t/p/w780" + movie.getBackdropPath();
        String movieTitle = movie.getTitle();
        String movieOverview = movie.getOverview();

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(movieTitle);

        TextView overview = (TextView) findViewById(R.id.summary);
        overview.setText(movieOverview);
        final ImageView imageView = (ImageView) findViewById(R.id.movie_backdrop);
        Picasso.with(mContext)
                .load(url)
                .into(imageView);

        setMovieDetails(movie);
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