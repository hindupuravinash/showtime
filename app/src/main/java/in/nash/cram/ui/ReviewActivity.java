package in.nash.cram.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.net.URL;

import in.nash.cram.model.Review;
import in.nash.cram.network.TmdbService;
import retrofit.RetrofitError;

/**
 * Created by avinash on 8/2/15.
 */
public class ReviewActivity extends AppCompatActivity{
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");

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

            //TODO: Setup Review
        }
    }

    private void fetchReview() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        Review review = tmdb.fetchMovieReview(mId);

    }

}
