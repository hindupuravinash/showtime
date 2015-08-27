package in.nash.showtime.ui.presenter.impl;

import in.nash.showtime.model.Review;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IReviewDetailPresenter;
import in.nash.showtime.ui.view.IReviewDetailView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/20/15.
 */
public class ReviewDetailPresenterImpl implements IReviewDetailPresenter {

    private final IReviewDetailView mReviewDetailView;

    public ReviewDetailPresenterImpl(IReviewDetailView reviewDetailView) {
        this.mReviewDetailView = reviewDetailView;

    }

    @Override
    public void fetchReview(String id) {
        Tmdb tmdb = new Tmdb();

        Observable<Review> reviewObservable = tmdb.detailService().fetchMovieReview(id);
        reviewObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Review>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Review review) {

                        mReviewDetailView.setReview(review);

                    }
                });

    }

}
