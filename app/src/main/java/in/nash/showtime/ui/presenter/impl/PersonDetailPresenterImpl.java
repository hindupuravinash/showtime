package in.nash.showtime.ui.presenter.impl;

import in.nash.showtime.model.Person;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IPersonDetailPresenter;
import in.nash.showtime.ui.view.IPersonDetailView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/20/15.
 */
public class PersonDetailPresenterImpl implements IPersonDetailPresenter {

    private final IPersonDetailView mPersonDetailView;

    public PersonDetailPresenterImpl(IPersonDetailView personDetailView) {
        this.mPersonDetailView = personDetailView;

    }

    @Override
    public void fetchPerson(String id) {
        Tmdb tmdb = new Tmdb();

        Observable<Person> reviewObservable = tmdb.detailService().fetchPersonDetails(id);
        reviewObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Person>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Person person) {

                        mPersonDetailView.setPerson(person);

                    }
                });

    }

}
