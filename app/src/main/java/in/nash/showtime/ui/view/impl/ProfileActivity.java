package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.ParseUser;

import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by avinash on 9/3/15.
 */
public class ProfileActivity  extends AppCompatActivity {

    Toolbar mToolbarView;

    TextView mName;

    TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        mName = (TextView) findViewById(R.id.name);
        mEmail = (TextView) findViewById(R.id.email);

        setSupportActionBar(mToolbarView);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setProfileData();
    }

    private void setProfileData() {
        ParseUser currentUser = ParseUser.getCurrentUser();

        mName.setText(currentUser.getUsername());
        mEmail.setText(currentUser.getEmail());
    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, EditProfileActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (id == R.id.edit){
            EditProfileActivity.navigateTo(ProfileActivity.this);
        }

        return super.onOptionsItemSelected(item);
    }

}
