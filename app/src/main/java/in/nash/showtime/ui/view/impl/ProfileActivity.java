package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by avinash on 9/3/15.
 */
public class ProfileActivity extends AppCompatActivity {

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar mToolbarView;
    @Bind(R.id.rootview)
    LinearLayout mRootview;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.email)
    EditText mEmail;
    @Bind(R.id.name_til)
    TextInputLayout mNameTil;
    @Bind(R.id.email_til)
    TextInputLayout mEmailTil;
    //endregion

    private boolean inProfile = true;
    private ParseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbarView);
        getSupportActionBar().setTitle(R.string.profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setProfileData();
    }

    private void setProfileData() {

        mUser = ParseUser.getCurrentUser();

        mName.setEnabled(false);
        mEmail.setEnabled(false);

        mName.setText(mUser.getUsername());
        mEmail.setText(mUser.getEmail());
    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, ProfileActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        MenuItem save = menu.findItem(R.id.save);
        MenuItem edit = menu.findItem(R.id.edit);

        if (!inProfile) {
            save.setVisible(true);
            edit.setVisible(false);
        } else {
            save.setVisible(false);
            edit.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (id == R.id.edit) {

            mName.setEnabled(true);
            mEmail.setEnabled(true);

            inProfile = false;
            this.invalidateOptionsMenu();

            return true;
        }

        if (id == R.id.save) {

            String name = mName.getText().toString();
            String email = mEmail.getText().toString();
            //   String mobile = mMobile.getText().toString();


            mName.setEnabled(false);
            mEmail.setEnabled(false);
            //   mMobile.setEnabled(false);

            mUser.setUsername(name);
            mUser.setEmail(email);

            mUser.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {
                    setProgressBarIndeterminateVisibility(false);

                    if (e == null) {
                        setProfileData();

                    } else {
                        Snackbar.make(mRootview, R.string.error_saving_changes, Snackbar.LENGTH_LONG).show();

                    }
                }
            });

            inProfile = true;
            this.invalidateOptionsMenu();

            return true;

        }

        if (id == R.id.signout) {

            navigateToLoginView();
        }

        return super.onOptionsItemSelected(item);
    }

    public void navigateToLoginView() {
        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}