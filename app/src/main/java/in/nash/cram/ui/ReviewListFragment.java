package in.nash.cram.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.adapter.ReviewListAdapter;
import in.nash.cram.model.Review;
import in.nash.cram.utils.SpacesItemDecoration;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewListFragment  extends Fragment {

    private static final String TAG = "ReviewListFrag";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Review> mReviewList = new ArrayList<>();

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

        return rootView;
    }

}