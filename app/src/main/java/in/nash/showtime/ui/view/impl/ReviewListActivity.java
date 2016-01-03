package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.adapter.ReviewListAdapter;
import in.nash.showtime.model.Review;
import in.nash.showtime.ui.presenter.IReviewsListPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IReviewsListView;
import in.nash.showtime.utils.SpacesItemDecoration;

/**
 * Created by avinash on 8/4/15.
 */
public class ReviewListActivity extends AppCompatActivity implements IReviewsListView{

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.reviews_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindDimen(R.dimen.spacing)
    int spacingInPixels;
    //engregion

    public static String mMovieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mMovieId = extras.getString("id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reviews");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        initPresenter();
    }

    private void initPresenter() {

        IReviewsListPresenter reviewsListPresenter = PresenterFactory.createReviewsListPresenter(this, mMovieId);
        reviewsListPresenter.queryReviews();

    }

    public static void navigateTo(Activity fromActivity, String movieId) {
        Intent intent = new Intent(fromActivity, ReviewListActivity.class);
        intent.putExtra("id", movieId);
        fromActivity.startActivity(intent);
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
    public void setReviewsList(final List<Review> reviews) {
        mRecyclerView.setAdapter(new ReviewListAdapter(reviews, new View.OnClickListener() {
            @Override
            public void onClick(View item) {
                int position = mRecyclerView.getChildAdapterPosition(item);
                Review review = reviews.get(position);
                ReviewActivity.navigateTo(ReviewListActivity.this, review);

            }
        }, false));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

}
