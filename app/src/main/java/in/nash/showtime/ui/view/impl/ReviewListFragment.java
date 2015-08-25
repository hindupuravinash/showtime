package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;

import in.nash.showtime.R;
import in.nash.showtime.adapter.ReviewListAdapter;
import in.nash.showtime.model.Review;
import in.nash.showtime.network.TmdbService;
import in.nash.showtime.ui.presenter.IReviewsListPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IReviewsListView;
import in.nash.showtime.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewListFragment extends Fragment implements IReviewsListView {

    public static final String TAG = "ReviewListFrag";
    private RecyclerView mRecyclerView;
    private ArrayList<Review> mReviewList = new ArrayList<>();
    private TmdbService.ReviewResponse mReviews;
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
    public void setReviewsList(ArrayList<Review> mReviews) {
            mRecyclerView.setAdapter(new ReviewListAdapter(mReviews, null));
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }


    private class FetchMovieReviews extends AsyncTask<URL, Integer, Boolean> {

        protected Boolean doInBackground(URL... urls) {
            try {
                getReviewsList();
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                Log.d("Result", "true");
                mReviewList = mReviews.reviews;
                mRecyclerView.setAdapter(new ReviewListAdapter(mReviewList, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick Review");
                        Review review = mReviewList.get(mRecyclerView.getChildLayoutPosition(view));
                        String id = review.getId();
                        Intent intent = new Intent(getActivity(), ReviewActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                    }
                }));
                int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
                mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
            } else {
                Log.d("Error", "Oops something went wrong");
            }
        }
    }

    private void getReviewsList() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        mReviews = tmdb.fetchMovieReviews(mId);
        Log.d("movie id: ", mId);

    }
}