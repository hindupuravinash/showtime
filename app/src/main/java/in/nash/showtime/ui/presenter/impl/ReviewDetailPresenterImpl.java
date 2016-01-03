package in.nash.showtime.ui.presenter.impl;

import in.nash.showtime.model.Review;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IReviewDetailPresenter;
import in.nash.showtime.ui.view.IReviewDetailView;
import retrofit.Result;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/20/15.
 */
public class ReviewDetailPresenterImpl implements IReviewDetailPresenter {

    private final IReviewDetailView mReviewDetailView;
    private final String mReviewId;

    public ReviewDetailPresenterImpl(IReviewDetailView reviewDetailView, String reviewId) {
        this.mReviewDetailView = reviewDetailView;
        this.mReviewId = reviewId;

    }

    @Override
    public void fetchReview() {
        mReviewDetailView.showProgressBar();
        Tmdb tmdb = new Tmdb();
        Observable<Result<Review>> reviewObservable = tmdb.detailService().fetchMovieReview(mReviewId);
        reviewObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Review>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        mReviewDetailView.hideProgressBar();
                    }

                    @Override
                    public void onNext(Result<Review> result) {
                        mReviewDetailView.hideProgressBar();
                        mReviewDetailView.setReview(result.response().body());

                    }
                });

    }

}
