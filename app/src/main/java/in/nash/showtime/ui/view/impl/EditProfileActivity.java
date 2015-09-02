package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by avinash on 8/31/15.
 */
public class EditProfileActivity extends AppCompatActivity {

    Toolbar mToolbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbarView);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, EditProfileActivity.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (id == R.id.done) {
            
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
