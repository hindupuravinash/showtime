package in.nash.showtime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.nash.showtime.R;
import in.nash.showtime.model.Video;

/**
 * Created by avinash on 9/29/15.
 */
public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {
    private final List<Video> mVideosList;
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;

    @Override
    public VideoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_movie, parent, false);

        mContext = parent.getContext();

        if (mOnItemClickListener != null) {
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
    public void onBindViewHolder(final VideoListAdapter.ViewHolder holder, int position) {

        holder.mBoundPosition = position;

        Video video = mVideosList.get(position);
        String url = "https://www.youtube.com/watch?v=" + video.getKey();
    }

    @Override
    public int getItemCount() {
        return mVideosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public int mBoundPosition;

        public ViewHolder(View view) {
            super(view);
        }
    }

    public VideoListAdapter(Context context, List<Video> videosList, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mVideosList = new ArrayList<>(videosList);
        this.mOnItemClickListener = onClickListener;
    }
}
