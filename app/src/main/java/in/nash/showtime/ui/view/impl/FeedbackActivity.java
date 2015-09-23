package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by avinash on 9/3/15.
 */
public class FeedbackActivity extends AppCompatActivity {

    Toolbar mToolbar;

    EditText mFeedbackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mFeedbackView = (EditText) findViewById(R.id.feedback);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.feedback_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, FeedbackActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        if (id == R.id.send) {

            String feedbackText = mFeedbackView.getText().toString().trim();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"howdy@nash.in"});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject));
            intent.putExtra(Intent.EXTRA_TEXT, feedbackText);
            startActivity(Intent.createChooser(intent, ""));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
