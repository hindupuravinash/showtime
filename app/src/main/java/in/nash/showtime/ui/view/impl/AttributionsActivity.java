package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;

/**
 * Created by avinash on 9/9/15.
 */
public class AttributionsActivity extends AppCompatActivity {
    //region View variables
    @Bind(R.id.web_view)
    WebView mWebView;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributions);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
        }

        String url = "file:///android_asset/licenses.html";

        if (savedInstanceState == null) {
            mWebView.loadUrl(url);
        }

    }

    public static void navigateTo(Activity fromActivity) {
        Intent intent = new Intent(fromActivity, AttributionsActivity.class);
        fromActivity.startActivity(intent);
    }
}
