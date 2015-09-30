package in.nash.showtime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.nash.showtime.R;
import in.nash.showtime.model.Person;
import in.nash.showtime.ui.Globals;
import in.nash.showtime.utils.CircleTransform;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {
    private final View.OnClickListener mOnItemClickListener;
    private final ArrayList<Person> mPersonsList;
    private Context mContext;
    private boolean mPreview;

    public PersonListAdapter(Context context, ArrayList<Person> personsList, View.OnClickListener onClickListener, boolean isPreview) {
        this.mContext = context;
        this.mPersonsList = new ArrayList<>(personsList);
        this.mOnItemClickListener = onClickListener;
        this.mPreview = isPreview;
    }

    @Override
    public PersonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);

        if(mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v);
                }
            });
        }

        return new PersonListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonListViewHolder holder, int position) {

        Person person = mPersonsList.get(position);
        String url = "http://image.tmdb.org/t/p/w300" + person.getProfilePath();

        holder.mName.setText(person.getName());
        Log.d("name", person.getName());
        holder.mRole.setText(person.getCharacter());
        Picasso.with(mContext)
                .load(url)
                .transform(new CircleTransform(0F, 0))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        Log.d("person list", mPersonsList.size() + "");

        if(mPreview){
            return Globals.MAX_PREVIEW_LENGTH;

        }
        else{
            return mPersonsList.size();
        }

    }

    public static class PersonListViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mName;
        public TextView mRole;

        public PersonListViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.person_picture);
            mName = (TextView) view.findViewById(R.id.person_name);
            mRole = (TextView) view.findViewById(R.id.person_role);

        }
    }

}
