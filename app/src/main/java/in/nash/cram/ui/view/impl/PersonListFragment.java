package in.nash.cram.ui.view.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.adapter.PersonListAdapter;
import in.nash.cram.model.Person;
import in.nash.cram.ui.presenter.IPersonListPresenter;
import in.nash.cram.ui.presenter.PresenterFactory;
import in.nash.cram.ui.view.IPersonListView;
import in.nash.cram.utils.SpacesItemDecoration;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListFragment extends Fragment implements IPersonListView{

    public static final String TAG = "PersonListFrag";
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

        mRecyclerView.setAdapter(new PersonListAdapter(mPersonList,null));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        initPresenter();
        return rootView;
    }

    private void initPresenter() {

        IPersonListPresenter personListPresenter = PresenterFactory.createPersonListPresenter(this);
        personListPresenter.queryPersons(PersonListActivity.mId);

    }

    @Override
    public void setPersonList(ArrayList<Person> mCast) {
        PersonListAdapter adapter = new PersonListAdapter(mCast, null);
        mRecyclerView.setAdapter(adapter);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

}
