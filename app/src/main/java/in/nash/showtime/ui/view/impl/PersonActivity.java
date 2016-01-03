package in.nash.showtime.ui.view.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.nash.showtime.R;
import in.nash.showtime.model.Person;
import in.nash.showtime.ui.presenter.IPersonDetailPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IPersonDetailView;

/**
 * Created by Avinash Hindupur on 05/07/15.
 */
public class PersonActivity extends AppCompatActivity implements IPersonDetailView{

    //region View variables
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.person_backdrop)
    ImageView personBackdrop;
    @Bind(R.id.summary)
    TextView summary;
    private Context mContext;
    private String mPersonId;
    //endregion

    protected void onCreate(Bundle savedInstanceState) {

        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        mPersonId = extras.getString("id");


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = PersonActivity.this;

        initPresenter();
    }

    private void initPresenter() {

        IPersonDetailPresenter personDetailPresenter = PresenterFactory.createPersonDetailPresenter(this, mPersonId);
        personDetailPresenter.fetchPerson();

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

    public static void navigateTo(Activity fromActivity, Person person) {
        Intent intent = new Intent(fromActivity, PersonActivity.class);
        intent.putExtra("id", person.getId());
        fromActivity.startActivity(intent);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPerson(Person person) {
        String url = "http://image.tmdb.org/t/p/w780" + person.getProfilePath();
        String personName = person.getName();

        collapsingToolbar.setTitle(personName);
        Picasso.with(mContext)
                .load(url)
                .into(personBackdrop);
        summary.setText(person.biography);
    }
}
