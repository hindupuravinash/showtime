package in.nash.cram.ui.view.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.net.URL;

import in.nash.cram.model.Person;
import in.nash.cram.network.TmdbService;
import retrofit.RetrofitError;

/**
 * Created by Avinash Hindupur on 05/07/15.
 */
public class PersonActivity extends AppCompatActivity {

    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");

        new FetchPersonAsyncTask().execute();
    }

    private class FetchPersonAsyncTask extends AsyncTask<URL, Integer, Boolean> {


        protected Boolean doInBackground(URL... urls) {
            try {
                fetchPerson();
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {

            //TODO: Setup Person
        }
    }

    private void fetchPerson() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        Person person = tmdb.fetchPersonDetails(mId);

    }
}
