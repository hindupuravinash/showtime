package in.nash.showtime.ui.view;

import android.support.annotation.StringRes;

import java.util.List;

import in.nash.showtime.model.Review;

/**
 * Created by avinash on 8/24/15.
 */
public interface IReviewsListView {

    void showProgressBar();

    void hideProgressBar();

    void setError(@StringRes int stringRes);

    void setReviewsList(List<Review> mReviews);
}
