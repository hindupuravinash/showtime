package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.adapter.PersonListAdapter;
import in.nash.showtime.model.Person;
import in.nash.showtime.ui.presenter.IPersonListPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IPersonListView;
import in.nash.showtime.utils.SpacesItemDecoration;

/**
 * Created by avinash on 8/4/15.
 */
public class PersonListActivity extends AppCompatActivity implements IPersonListView {

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.persons_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    //engregion

    public static final String TAG = "PersonListActivity";
    public static String mId;
    private String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        fragment = intent.getStringExtra("fragment");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        initPresenter();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initPresenter() {

        IPersonListPresenter personListPresenter = PresenterFactory.createPersonListPresenter(this, mId);
        personListPresenter.queryPersons(fragment);
        Log.d("mId", mId);

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
        mRecyclerView.setAdapter(new PersonListAdapter(this, mCast, null, false));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }
}
