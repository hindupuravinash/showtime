package in.nash.cram.ui.view;

import android.support.annotation.StringRes;

import java.util.ArrayList;

import in.nash.cram.model.Person;

/**
 * Created by avinash on 8/16/15.
 */
public interface IPersonListView {

    void showProgressBar();

    void hideProgressBar();

    void setError(@StringRes int stringRes);

    void setPersonList(ArrayList<Person> mCast);
}
