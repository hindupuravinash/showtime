package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.model.Review;
import in.nash.showtime.ui.presenter.IReviewDetailPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IReviewDetailView;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewActivity extends AppCompatActivity implements IReviewDetailView{
    private String mId;

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.review_content)
    TextView reviewText;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    private String mReviewId;
    //endregion

    protected void onCreate(Bundle savedInstanceState) {

        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        mReviewId = extras.getString("id");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initPresenter();
    }

    private void initPresenter() {

        IReviewDetailPresenter reviewDetailPresenter = PresenterFactory.createReviewDetailPresenter(this, mReviewId);
        reviewDetailPresenter.fetchReview();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void navigateTo(Activity fromActivity, Review review) {
        Intent intent = new Intent(fromActivity, ReviewActivity.class);
        intent.putExtra("id", review.getId());
        fromActivity.startActivity(intent);
    }


    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setReview(Review review) {
        String title = review.getAuthor() +"'s Review";
        String reviewContent = review.getContent();

        toolbar.setTitle(title);
       reviewText.setText(reviewContent);
    }

}
