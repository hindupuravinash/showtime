package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by avinash on 8/31/15.
 */
public class EditProfileActivity extends AppCompatActivity {

    Toolbar mToolbarView;

    EditText mUsername;
    EditText mEmail;

    String mUsernameText;
    String mEmailText;
    private ParseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mToolbarView = (Toolbar) findViewById(R.id.toolbar);

        mUsername = (EditText) findViewById(R.id.name);
        mEmail = (EditText) findViewById(R.id.email);

        mUser = ParseUser.getCurrentUser();

        mUsernameText = mUser.getUsername();
        mEmailText = mUser.getEmail();

        mUsername.setText(mUsernameText);
        mEmail.setText(mEmailText);
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

            mUsernameText = mUsername.getText().toString();
            mEmailText = mEmail.getText().toString();
            mUser.setUsername(mUsernameText);
            mUser.setEmail(mEmailText);

            mUser.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {
                    setProgressBarIndeterminateVisibility(false);

                    if (e == null) {
                        //Success!
                        finish();

                    } else {
                        Snackbar.make(null, "Error occurred while saving the details", Snackbar.LENGTH_LONG).show();

                    }
                }
            });

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
