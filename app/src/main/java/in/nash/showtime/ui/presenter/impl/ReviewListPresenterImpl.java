package in.nash.showtime.ui.presenter.impl;


import android.util.Log;

import in.nash.showtime.network.ReviewsResponse;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IReviewsListPresenter;
import in.nash.showtime.ui.view.IReviewsListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/24/15.
 */
public class ReviewListPresenterImpl implements IReviewsListPresenter {
    private final IReviewsListView mReviewsListView;
    private final String mMovieId;

    public ReviewListPresenterImpl(IReviewsListView reviewsListView, String movieId) {
        this.mReviewsListView = reviewsListView;
        this.mMovieId = movieId;
    }

    @Override
    public void queryReviews() {
        mReviewsListView.showProgressBar();
        Tmdb tmdb = new Tmdb();
        Observable<ReviewsResponse> persons;
        persons = tmdb.moviesService().fetchMovieReviews(mMovieId);
        persons.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReviewsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mReviewsListView.hideProgressBar();
                    }

                    @Override
                    public void onNext(ReviewsResponse reviewsResponse) {
                        mReviewsListView.hideProgressBar();
                        mReviewsListView.setReviewsList(reviewsResponse.results);
                        Log.d("persons", reviewsResponse.results.size() + "");
                    }
                });

    }
}
