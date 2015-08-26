package in.nash.showtime.ui.view.impl;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import in.nash.showtime.R;
import in.nash.showtime.adapter.PersonListAdapter;
import in.nash.showtime.model.Person;
import in.nash.showtime.ui.presenter.IPersonListPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IPersonListView;
import in.nash.showtime.utils.SpacesItemDecoration;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListFragment extends Fragment implements IPersonListView {

    public static final String TAG = "PersonListFrag";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_person_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.persons_list);

        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // mRecyclerView.setAdapter(new PersonListAdapter(getActivity().getBaseContext(), mPersonList,null));


        initPresenter();
        return rootView;
    }

    private void initPresenter() {

        IPersonListPresenter personListPresenter = PresenterFactory.createPersonListPresenter(this);
        personListPresenter.queryPersons(PersonListActivity.mId);
        Log.d("mId", PersonListActivity.mId);

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setError(@StringRes int stringRes) {
        Snackbar.make(mRecyclerView, stringRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPersonList(ArrayList<Person> mCast) {
        mRecyclerView.setAdapter(new PersonListAdapter(getActivity().getBaseContext(), mCast, null));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }

}
