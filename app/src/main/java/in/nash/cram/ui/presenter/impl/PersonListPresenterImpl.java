package in.nash.cram.ui.presenter.impl;

import in.nash.cram.network.MoviesService;
import in.nash.cram.network.Tmdb;
import in.nash.cram.ui.presenter.IPersonListPresenter;
import in.nash.cram.ui.view.IPersonListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/16/15.
 */
public class PersonListPresenterImpl implements IPersonListPresenter {

    private IPersonListView mPersonListView;

    public PersonListPresenterImpl(IPersonListView personListView) {
        mPersonListView = personListView;
    }

    @Override
    public void queryPersons(String id) {

        Tmdb tmdb = new Tmdb();
        Observable<MoviesService.CreditResponse> persons;
        persons = tmdb.moviesService().fetchMovieCredits(id);
        persons.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviesService.CreditResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MoviesService.CreditResponse creditResponse) {

                        mPersonListView.setPersonList(creditResponse.mCast);
                    }
                });

    }

}
