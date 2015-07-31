package in.nash.cram.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.model.Person;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListAdapter extends RecyclerView.Adapter {
    private final View.OnClickListener mOnItemClickListener;
    private final ArrayList<Person> mPersonsList;

    public PersonListAdapter(ArrayList<Person> personsList, View.OnClickListener onClickListener) {
        this.mPersonsList = new ArrayList<>(personsList);
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mPersonsList.size();
    }
}
