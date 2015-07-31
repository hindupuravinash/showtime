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
import in.nash.cram.adapter.MovieGridAdapter;
import in.nash.cram.adapter.PersonListAdapter;
import in.nash.cram.model.Person;
import in.nash.cram.utils.SpacesItemDecoration;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListFragment extends Fragment {

    private static final String TAG = "PersonListFrag";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Person> mPersonList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_person_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.persons_list);

        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new PersonListAdapter(mPersonList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick Person");
                Person person = mPersonList.get(mRecyclerView.getChildLayoutPosition(view));
                String id = person.getId();
                Intent intent = new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        }));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        return rootView;
    }

}
