package in.nash.showtime.ui.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.nash.showtime.R;

/**
 * Created by Avinash Hindupur on 24/07/15.
 */
public class FeatureRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mFeatureText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_request);
        Button button = (Button) findViewById(R.id.btn_submit);
        button.setOnClickListener(this);

        mFeatureText = (EditText) findViewById(R.id.feature_text);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_submit) {
            final String feature = mFeatureText.getText().toString().trim();
            if (feature.isEmpty()) {
                mFeatureText.setError("Feature cannot be empty");
                return;
            }
            mFeatureText.setError(null);

            // TODO: Post feature to Parse database
            postFeatureRequest();
        }
    }

    private void postFeatureRequest() {

    }
}
