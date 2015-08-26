package in.nash.showtime.ui.view.impl;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import in.nash.showtime.R;
import in.nash.showtime.adapter.ReviewListAdapter;
import in.nash.showtime.model.Review;
import in.nash.showtime.ui.presenter.IReviewsListPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IReviewsListView;
import in.nash.showtime.utils.SpacesItemDecoration;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewListFragment extends Fragment implements IReviewsListView {

    public static final String TAG = "ReviewListFrag";
    private RecyclerView mRecyclerView;
    private String mId;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_review_list, container, false);
       mId = ReviewListActivity.mId;
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.reviews_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);

        initPresenter();
        return rootView;
    }

    private void initPresenter() {

        IReviewsListPresenter reviewsListPresenter = PresenterFactory.createReviewsListPresenter(this);
        reviewsListPresenter.queryReviews(ReviewListActivity.mId);

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
    public void setReviewsList(ArrayList<Review> reviews) {
            mRecyclerView.setAdapter(new ReviewListAdapter(reviews, null));
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

}