package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by Avinash Hindupur on 04/07/15.
 */
public class AboutActivity extends AppCompatActivity {

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar mToolbarView;
    @Bind(R.id.version)
    TextView versionText;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbarView);
        getSupportActionBar().setTitle(R.string.about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        versionText.setText("Version: " + AppUtils.getVersionName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, AboutActivity.class);
    }
}
