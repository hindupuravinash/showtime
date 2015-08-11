package in.nash.cram.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.model.Review;

/**
 * Created by avinash on 8/1/15.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {
    private final View.OnClickListener mOnItemClickListener;
    private final ArrayList<Review> mReviewsList;

    public ReviewListAdapter(ArrayList<Review> reviewsList, View.OnClickListener onClickListener) {
        this.mReviewsList = new ArrayList<>(reviewsList);
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);

        if(mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v);
                }
            });
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mReviewsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public int mBoundPosition;

        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.poster_image);
        }
    }
}