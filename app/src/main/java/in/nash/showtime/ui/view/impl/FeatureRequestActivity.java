package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.nash.showtime.R;
import in.nash.showtime.utils.AppUtils;

/**
 * Created by Avinash Hindupur on 24/07/15.
 */
public class FeatureRequestActivity extends AppCompatActivity implements View.OnClickListener {

    //region View variables
    @Bind(R.id.feature_text)
    EditText mFeatureText;
    @Bind(R.id.toolbar)
    Toolbar mToolbarView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_request);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbarView);
        getSupportActionBar().setTitle(R.string.title_feature_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    @OnClick(R.id.btn_submit)
    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            final String feature = mFeatureText.getText().toString().trim();
            if (feature.isEmpty()) {
                mFeatureText.setError(getString(R.string.error_feature_empty));
                return;
            }
            mFeatureText.setError(null);

            // TODO: Post feature to Parse database
            postFeatureRequest();
        }
    }

    private void postFeatureRequest() {

    }

    public static void navigateTo(Activity fromActivity) {
        AppUtils.navigateToActivitySimple(fromActivity, FeatureRequestActivity.class);
    }
}
