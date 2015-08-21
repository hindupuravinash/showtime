package in.nash.cram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.model.Movie;
import in.nash.cram.model.Person;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder> {
    private final View.OnClickListener mOnItemClickListener;
    private final ArrayList<Person> mPersonsList;
    private Context mContext;

    public PersonListAdapter(ArrayList<Person> personsList, View.OnClickListener onClickListener) {
        this.mPersonsList = new ArrayList<>(personsList);
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public PersonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);

        mContext = parent.getContext();
        if(mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(v);
                }
            });
        }

        PersonListViewHolder vh = new PersonListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(PersonListViewHolder holder, int position) {
        holder.mBoundPosition = position;

        Person person = mPersonsList.get(position);
        String url = "http://image.tmdb.org/t/p/w300" + person.getProfilePath();

        holder.mName.setText(person.getName());
        holder.mRole.setText(person.getCharacter());
        Picasso.with(mContext)
                .load(url)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mPersonsList.size();
    }

    public static class PersonListViewHolder extends RecyclerView.ViewHolder {
        public int mBoundPosition;

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
