package in.nash.cram.ui.view;

import android.support.annotation.StringRes;

import java.util.ArrayList;

import in.nash.cram.model.Review;

/**
 * Created by avinash on 8/24/15.
 */
public interface IReviewsListView {

    void showProgressBar();

    void hideProgressBar();

    void setError(@StringRes int stringRes);

    void setReviewsList(ArrayList<Review> mReviews);
}
