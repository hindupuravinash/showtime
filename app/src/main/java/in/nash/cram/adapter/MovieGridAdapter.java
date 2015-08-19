package in.nash.cram.adapter;

import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import in.nash.cram.R;
import in.nash.cram.model.Movie;
import in.nash.cram.ui.view.impl.MovieDetailActivity;
import rx.internal.util.UtilityFunctions;

/**
 * Created by avinash on 21/06/15.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private final List<Movie> mMoviesList;
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;

    @Override
    public MovieGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(final MovieGridAdapter.ViewHolder holder, int position) {

        holder.mBoundPosition = position;

        Movie movie = mMoviesList.get(position);
        String url = "http://image.tmdb.org/t/p/w300" + movie.getPosterUrl();

        Picasso.with(mContext)
                .load(url)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public int mBoundPosition;

        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.poster_image);
        }
    }

    public MovieGridAdapter(Context context, List<Movie> moviesList, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mMoviesList = new ArrayList<>(moviesList);
        this.mOnItemClickListener = onClickListener;
    }
}
