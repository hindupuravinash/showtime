package in.nash.showtime.ui.presenter.impl;

import in.nash.showtime.model.Person;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IPersonDetailPresenter;
import in.nash.showtime.ui.view.IPersonDetailView;
import retrofit.Result;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/20/15.
 */
public class PersonDetailPresenterImpl implements IPersonDetailPresenter {

    private final IPersonDetailView mPersonDetailView;
    private final String mPersonId;

    public PersonDetailPresenterImpl(IPersonDetailView personDetailView, String personId) {
        this.mPersonDetailView = personDetailView;
        this.mPersonId = personId;

    }

    @Override
    public void fetchPerson() {
        Tmdb tmdb = new Tmdb();

        mPersonDetailView.showProgressBar();
        Observable<Result<Person>> reviewObservable = tmdb.detailService().fetchPersonDetails(mPersonId);
        reviewObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<Person>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mPersonDetailView.hideProgressBar();

                    }

                    @Override
                    public void onNext(Result<Person> result) {
                        mPersonDetailView.hideProgressBar();
                        mPersonDetailView.setPerson(result.response().body());

                    }
                });

    }

}
