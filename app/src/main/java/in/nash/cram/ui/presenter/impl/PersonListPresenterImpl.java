package in.nash.cram.ui.presenter.impl;


import in.nash.cram.ui.presenter.IPersonListPresenter;
import in.nash.cram.ui.view.IPersonListView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/16/15.
 */
public class PersonListPresenterImpl implements IPersonListPresenter{

    private IPersonListView mPersonListView;

    public PersonListPresenterImpl(IPersonListView personListView) {
        mPersonListView = personListView;
    }

    @Override
    public void queryPersons() {

    }
}
