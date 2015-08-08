package in.nash.cram.ui.view.impl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.URL;
import java.util.ArrayList;

import in.nash.cram.R;
import in.nash.cram.adapter.PersonListAdapter;
import in.nash.cram.model.Person;
import in.nash.cram.network.TmdbService;
import in.nash.cram.utils.SpacesItemDecoration;
import retrofit.RetrofitError;

/**
 * Created by avinash on 7/29/15.
 */
public class PersonListFragment extends Fragment {

    public static final String TAG = "PersonListFrag";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Person> mPersonList = new ArrayList<>();
    private PersonListAdapter mAdapter;
    private String mId;
    private TmdbService.CreditResponse mCredits;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_person_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.persons_list);

        mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(new PersonListAdapter(mPersonList,null));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        new FetchMovieCredits().execute();
        return rootView;
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
