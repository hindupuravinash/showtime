package in.nash.cram.ui.view.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.URL;

import in.nash.cram.R;
import in.nash.cram.model.Review;
import in.nash.cram.network.TmdbService;
import retrofit.RetrofitError;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewActivity extends AppCompatActivity {
    private String mId;
    private Review mReview;

    private TextView author;
    private TextView content;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");

        setContentView(R.layout.activity_review);

        new FetchReviewAsyncTask().execute();
    }

    private class FetchReviewAsyncTask extends AsyncTask<URL, Integer, Boolean> {


        protected Boolean doInBackground(URL... urls) {
            try {
                fetchReview();
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {

            if (result) {
                author.setText(mReview.getAuthor());
                content.setText(mReview.getContent());
                String url = mReview.getUrl();
                //TODO: Decide if Webview needs to be shown
            } else {
                //TODO: Throw error
            }
        }
    }

    private void fetchReview() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        mReview = tmdb.fetchMovieReview(mId);

    }

}
