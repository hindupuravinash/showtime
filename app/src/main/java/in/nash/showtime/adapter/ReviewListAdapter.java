package in.nash.showtime.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.nash.showtime.R;
import in.nash.showtime.model.Review;
import in.nash.showtime.ui.Globals;

/**
 * Created by avinash on 8/1/15.
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {
    private final View.OnClickListener mOnItemClickListener;
    private final ArrayList<Review> mReviewsList;
    private final boolean mIsPreview;

    public ReviewListAdapter(List<Review> reviewsList, View.OnClickListener onClickListener, boolean isPreview) {
        this.mReviewsList = new ArrayList<>(reviewsList);
        this.mOnItemClickListener = onClickListener;
        this.mIsPreview = isPreview;
    }

    @Override
    public ReviewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);

        if(mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v);
                }
            });
        }

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mBoundPosition = position;

        Review review = mReviewsList.get(position);
        String author = review.getAuthor();
        String description = review.getContent();
        Log.d(author, description);

        holder.mTitleView.setText(author);
        holder.mDescriptionView.setText(description);
    }

    @Override
    public int getItemCount() {
        if(mIsPreview){
            return Math.min(mReviewsList.size(), Globals.MAX_PREVIEW_LENGTH);

        }
        else{
            return mReviewsList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public int mBoundPosition;

        public TextView mTitleView;
        public TextView mDescriptionView;
        public ViewHolder(View view) {
            super(view);
            mTitleView = (TextView) view.findViewById(R.id.review_title);
            mDescriptionView = (TextView) view.findViewById(R.id.review_description);

        }
    }
}