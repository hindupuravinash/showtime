package in.nash.showtime.ui.view;

import in.nash.showtime.model.Review;

/**
 * Created by avinash on 8/27/15.
 */
public interface IReviewDetailView {

    void showProgressBar();

    void hideProgressBar();

    void setReview(Review review);
}
