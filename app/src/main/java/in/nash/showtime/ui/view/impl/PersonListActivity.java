package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import in.nash.showtime.R;

/**
 * Created by avinash on 8/4/15.
 */
public class PersonListActivity extends AppCompatActivity {

    public static String mId;
    private String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        fragment = intent.getStringExtra("fragment");

        PersonListFragment personListFragment = new PersonListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.list_container, personListFragment, personListFragment.TAG).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

}
