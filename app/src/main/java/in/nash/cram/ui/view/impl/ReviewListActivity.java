package in.nash.cram.ui.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.nash.cram.R;

/**
 * Created by avinash on 8/4/15.
 */
public class ReviewListActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout);
        ReviewListFragment reviewListFragment = new ReviewListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_container, reviewListFragment, reviewListFragment.TAG).commit();
    }

}
