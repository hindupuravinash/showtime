package in.nash.cram.ui.view.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.net.URL;

import in.nash.cram.R;
import in.nash.cram.adapter.PersonListAdapter;
import in.nash.cram.network.TmdbService;
import in.nash.cram.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 8/4/15.
 */
public class PersonListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PersonListAdapter mAdapter;
    private String mId;
    private TmdbService.CreditResponse mCredits;
    private String fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        fragment = intent.getStringExtra("fragment");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        new FetchMovieCredits().execute();

    }

    private class FetchMovieCredits extends AsyncTask<URL, Integer, Boolean> {

        protected Boolean doInBackground(URL... urls) {
            try {
                getCreditsList();
            } catch (RetrofitError e) {

                Log.d("Error", e.getMessage());
                return false;
            }
            return true;
        }

        protected void onPostExecute(Boolean result) {
            mAdapter = new PersonListAdapter(mCredits.mCast, null);
            mRecyclerView.setAdapter(mAdapter);
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
            mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        }
    }

    private void getCreditsList() {
        TmdbService tmdbService = new TmdbService();
        TmdbService.Tmdb tmdb = tmdbService.getRestAdapter().create(TmdbService.Tmdb.class);

        mCredits = tmdb.fetchMovieCredits(mId);

    }

}
