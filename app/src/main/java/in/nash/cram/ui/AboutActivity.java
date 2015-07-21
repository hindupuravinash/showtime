package in.nash.cram.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import in.nash.cram.R;
import in.nash.cram.utils.AppUtils;

/**
 * Created by Avinash Hindupur on 04/07/15.
 */
public class AboutActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView versionText = (TextView) findViewById(R.id.version);
        versionText.setText("Version: " + AppUtils.getVersionName());
    }
}
