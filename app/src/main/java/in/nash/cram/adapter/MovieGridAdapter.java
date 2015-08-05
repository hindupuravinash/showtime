package in.nash.cram.adapter;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.model.Movie;
import in.nash.cram.ui.view.impl.MovieDetailActivity;

/**
 * Created by avinash on 21/06/15.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    private final ArrayList<Movie> mMoviesList;
    private Context mContext;

    @Override
    public MovieGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_movie, parent, false);

        mContext = parent.getContext();
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

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE_POSITION, holder.mBoundPosition);

                context.startActivity(intent);
            }
        });
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

    public MovieGridAdapter(ArrayList<Movie> moviesList) {
        mMoviesList = moviesList;
    }
}
