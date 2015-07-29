package in.nash.cram.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.nash.cram.model.Person;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListAdapter extends RecyclerView.Adapter {
    public PersonListAdapter(ArrayList<Person> mPersonList) {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
