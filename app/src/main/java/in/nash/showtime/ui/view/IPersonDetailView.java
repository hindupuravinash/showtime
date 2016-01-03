package in.nash.showtime.ui.view;

import in.nash.showtime.model.Person;

/**
 * Created by avinash on 8/27/15.
 */
public interface IPersonDetailView {

    void showProgressBar();

    void hideProgressBar();

    void setPerson(Person person);
}
