package in.nash.cram.ui;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import in.nash.cram.R;
import in.nash.cram.model.Movie;

/**
 * Created by Avinash Hindupur on 24/06/15.
 */
public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE_POSITION = "movie_position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        final int moviePosition = intent.getIntExtra(MOVIE_POSITION, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Movie");

        Context context = MovieDetailActivity.this;

        Movie movie = Globals.moviesList.get(moviePosition);
        String url = "http://image.tmdb.org/t/p/w300" + movie.getPosterUrl();
        final ImageView imageView = (ImageView) findViewById(R.id.movie_backdrop);
        Picasso.with(context)
                .load(url)
                .into(imageView);
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
