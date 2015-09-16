package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import in.nash.showtime.R;

/**
 * Created by Avinash Hindupur on 05/07/15.
 */
public class PersonActivity extends AppCompatActivity {
    
    private WebView mWebView;
    private String mUrl;
    private Toolbar toolbar;
    private ProgressBar mProgress;

    protected void onCreate(Bundle savedInstanceState) {

        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        mWebView = (WebView) findViewById(R.id.webview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgress = (ProgressBar) findViewById(R.id.progress_bar);

        if (savedInstanceState != null) {
            ((WebView) findViewById(R.id.webview)).restoreState(savedInstanceState);
        }

        Bundle extras = getIntent().getExtras();
        mUrl = extras.getString("url");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);

        mWebView.setWebViewClient(new CareWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        if (savedInstanceState == null) {
            mWebView.loadUrl(mUrl);
        }

        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int loadProgress) {
                mProgress.setVisibility(View.VISIBLE);
                mWebView.setVisibility(View.GONE);
                if (loadProgress == 100) {
                    mProgress.setVisibility(View.GONE);
                    mWebView.setVisibility(View.VISIBLE);

                }
            }
        });
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

    public static void navigateTo(Activity fromActivity, String url) {
        Intent intent = new Intent(fromActivity, PersonActivity.class);
        intent.putExtra("url", url);
        fromActivity.startActivity(intent);
    }

    private class CareWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mWebView.restoreState(savedInstanceState);
    }
}
