package in.nash.showtime.ui.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.nash.showtime.model.Person;
import in.nash.showtime.ui.presenter.IPersonDetailPresenter;
import in.nash.showtime.ui.presenter.PresenterFactory;
import in.nash.showtime.ui.view.IPersonDetailView;

/**
 * Created by Avinash Hindupur on 05/07/15.
 */
public class PersonActivity extends AppCompatActivity implements IPersonDetailView {

    private String mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mId = extras.getString("id");

        initPresenter();
    }

    private void initPresenter() {

        IPersonDetailPresenter personDetailPresenter = PresenterFactory.createPersonDetailPresenter(this);
        personDetailPresenter.fetchPerson(mId);
    }

    @Override
    public void setPerson(Person person) {
        //TODO: Setup Person
    }

}
