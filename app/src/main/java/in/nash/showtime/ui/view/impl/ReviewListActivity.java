package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.nash.showtime.R;

/**
 * Created by avinash on 8/4/15.
 */
public class ReviewListActivity extends AppCompatActivity{

    public static String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layout);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");
        ReviewListFragment reviewListFragment = new ReviewListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_container, reviewListFragment, reviewListFragment.TAG).commit();
    }

}
