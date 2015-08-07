package in.nash.cram.ui.view.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.adapter.PersonListAdapter;
import in.nash.cram.adapter.ReviewListAdapter;
import in.nash.cram.model.Review;
import in.nash.cram.network.TmdbService;
import in.nash.cram.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewListFragment  extends Fragment {

    private static final String TAG = "ReviewListFrag";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Review> mReviewList = new ArrayList<>();
    private ReviewListAdapter mAdapter;
    private TmdbService.ReviewResponse mReviews;
    private String mId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_review_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.reviews_list);

        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

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

        new FetchMovieReviews().execute();
        return rootView;
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
            mAdapter = new ReviewListAdapter(mReviews.mReviews, null);
            mRecyclerView.setAdapter(mAdapter);
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        }
    }

    private void getReviewsList() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        mReviews = tmdb.fetchMovieReviews(mId);

    }
}