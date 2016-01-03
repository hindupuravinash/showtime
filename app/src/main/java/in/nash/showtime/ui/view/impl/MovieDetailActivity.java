package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.nash.showtime.R;
import in.nash.showtime.adapter.MovieGridAdapter;
import in.nash.showtime.adapter.PersonListAdapter;
import in.nash.showtime.adapter.ReviewListAdapter;
import in.nash.showtime.adapter.VideoListAdapter;
import in.nash.showtime.model.Movie;
import in.nash.showtime.model.Person;
import in.nash.showtime.model.Review;
import in.nash.showtime.model.Video;
import in.nash.showtime.ui.presenter.IMovieDetailPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IMovieDetailView;
import in.nash.showtime.utils.DateUtils;
import in.nash.showtime.utils.LinearLayoutManager;
import in.nash.showtime.utils.SdkUtil;
import in.nash.showtime.utils.SpacesItemDecoration;
import in.nash.showtime.utils.StringUtil;
import in.nash.showtime.utils.TransitionHelper;

/**
 * Created by Avinash Hindupur on 24/06/15.
 */
public class MovieDetailActivity extends AppCompatActivity implements IMovieDetailView, View.OnClickListener {

    private String mMovieId;
    private Movie mMovie;
    private Context mContext;

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.movies_similar)
    RecyclerView mSimilarsRecyclerView;
    @Bind(R.id.movie_videos)
    RecyclerView mVideoRecyclerView;
    @Bind(R.id.movie_cast)
    RecyclerView mCastRecyclerView;
    @Bind(R.id.movie_crew)
    RecyclerView mCrewRecyclerView;
    @Bind(R.id.movie_reviews)
    RecyclerView mReviewsRecyclerView;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.rating_tmdb)
    TextView tmdb;
    @Bind(R.id.cast)
    CardView castLayout;
    @Bind(R.id.crew)
    CardView crewLayout;
    @Bind(R.id.reviews)
    CardView reviewsLayout;
    @Bind(R.id.videos)
    CardView videosLayout;
    @Bind(R.id.similar)
    CardView similarsLayout;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.nested_scrollview)
    NestedScrollView mNestedScrollView;
    @BindDimen(R.dimen.spacing)
    int spacingInPixels;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mMovieId = intent.getStringExtra("id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = MovieDetailActivity.this;

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
    @OnClick({R.id.cast, R.id.crew, R.id.reviews})
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
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

        collapsingToolbar.setTitle(movieTitle);

        TextView overview = (TextView) findViewById(R.id.summary);
        overview.setText(movieOverview);
        final ImageView imageView = (ImageView) findViewById(R.id.movie_backdrop);
        Picasso.with(mContext)
                .load(url)
                .into(imageView);

        setMovieDetails(movie);
        setRatings(movie);
        setCast((ArrayList<Person>) movie.credits.cast);
        setCrew((ArrayList<Person>) movie.credits.crew);
        setSimilarMovies(movie.similar.results);
        setReviews(movie.reviews.results);
        Log.d("log reviews", "" + movie.reviews.results.size());
        Log.d("log similar", "" + movie.similar.results.size());
        setVideos(movie.videos.getResults());
        Log.d("log videos", "" + movie.videos.getResults().size());

    }

    private void setMovieDetails(Movie movie) {
        TextView released = (TextView) findViewById(R.id.released);
        TextView runtime = (TextView) findViewById(R.id.runtime);
        TextView budget = (TextView) findViewById(R.id.budget);
        TextView revenue = (TextView) findViewById(R.id.revenue);
        TextView language = (TextView) findViewById(R.id.language);
        TextView genres = (TextView) findViewById(R.id.genres);

        Log.d("Time", movie.getReleaseDate());
        Log.d("Time", movie.getReleaseDate());
        //released.setText(movie.getReleaseDate());

        String languageTitle = movie.getOriginalLanguage(); // TODO: Fix this, temporary hack

        switch (languageTitle) {
            case "en":
                languageTitle = "English";
                break;
            case "ja":
                languageTitle = "Japanese";
                break;
            case "fr":
                languageTitle = "French";
                break;
            case "es":
                languageTitle = "Spanish";
                break;
            case "de":
                languageTitle = "German";
                break;
        }

        released.setText(DateUtils.toDateWithoutTime(movie.getReleaseDate()));
        runtime.setText(StringUtil.getSrtingFromInt(movie.getRuntime()) + " mins");
        budget.setText("$ " + StringUtil.getSrtingFromInt(movie.getBudget()));
        revenue.setText(StringUtil.getSrtingFromInt(movie.getRevenue()));
        genres.setText(movie.getGenre());
        language.setText(languageTitle);
    }

    private void setSimilarMovies(final List<Movie> movies) {
        if (movies.size() > 0) {
            similarsLayout.setVisibility(View.VISIBLE);
            mSimilarsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mSimilarsRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
            mSimilarsRecyclerView.setAdapter(new MovieGridAdapter(this, movies, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mSimilarsRecyclerView.getChildAdapterPosition(v);
                    Movie movie = movies.get(position);
                    mMovie = movie;
                    // MovieDetailActivity.navigateTo(getActivity(), movie.getId());

                    startMovieDetailActivityWithTransition(MovieDetailActivity.this, v,
                            movie);

                }
            }));
        }
    }

    private void setCast(final ArrayList<Person> cast) {
        if (cast.size() > 0) {
            castLayout.setVisibility(View.VISIBLE);
            LinearLayoutManager castLayoutManager = new LinearLayoutManager(this);
            mCastRecyclerView.setLayoutManager(castLayoutManager);
            mCastRecyclerView.setAdapter(new PersonListAdapter(this, cast, null, true));
            mCastRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        }

    }

    private void setCrew(final ArrayList<Person> crew) {
        if (crew.size() > 0) {
            crewLayout.setVisibility(View.VISIBLE);
            LinearLayoutManager crewLayoutManager = new LinearLayoutManager(this);
            mCrewRecyclerView.setLayoutManager(crewLayoutManager);
            mCrewRecyclerView.setAdapter(new PersonListAdapter(this, crew, null, true));
            mCrewRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        }
    }

    private void setReviews(final List<Review> reviews) {
        if (reviews.size() > 0) {
            reviewsLayout.setVisibility(View.VISIBLE);
            Log.d("reviews", "" + reviews.size());
            LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this);
            mReviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
            mReviewsRecyclerView.setAdapter(new ReviewListAdapter(reviews, null, true));
            mReviewsRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        }
    }

    private void setVideos(final List<Video> videos) {
        if (videos.size() > 0) {
            videosLayout.setVisibility(View.VISIBLE);
            mVideoRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mVideoRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
            mVideoRecyclerView.setAdapter(new VideoListAdapter(this, videos, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mVideoRecyclerView.getChildAdapterPosition(v);
                    Video video = videos.get(position);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + video.getKey()));

                    startActivity(intent);
                }
            }));
        }
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
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mNestedScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mNestedScrollView.setVisibility(View.VISIBLE);
    }

    public void setRatings(Movie movie) {
        tmdb.setText("TMDB - " + movie.getVoteAverage() + "/10");
    }
}