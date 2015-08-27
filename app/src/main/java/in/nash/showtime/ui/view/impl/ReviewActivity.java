package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

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

    private TextView authorView;
    private TextView contentView;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");

        setContentView(R.layout.activity_review);

        authorView = (TextView) findViewById(R.id.author);
        contentView = (TextView) findViewById(R.id.contentView);

        initPresenter();
    }

    private void initPresenter() {

        IReviewDetailPresenter reviewDetailPresenter = PresenterFactory.createReviewDetailPresenter(this);
        reviewDetailPresenter.fetchReview(mId);
    }

    @Override
    public void setReview(Review review) {
        authorView.setText(review.getAuthor());
        contentView.setText(review.getContent());
        String url = review.getUrl();
        //TODO: Decide if Webview needs to be shown
    }

}
