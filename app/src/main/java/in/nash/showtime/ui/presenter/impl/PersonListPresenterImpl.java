package in.nash.showtime.ui.presenter.impl;

import android.util.Log;

import in.nash.showtime.network.MoviesService;
import in.nash.showtime.network.Tmdb;
import in.nash.showtime.ui.presenter.IPersonListPresenter;
import in.nash.showtime.ui.view.IPersonListView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by avinash on 8/16/15.
 */
public class PersonListPresenterImpl implements IPersonListPresenter {

    private final String mMovieId;
    private IPersonListView mPersonListView;

    public PersonListPresenterImpl(IPersonListView personListView, String movieId) {
        mPersonListView = personListView;
        mMovieId = movieId;
    }

    @Override
    public void queryPersons(final String type) {

        Tmdb tmdb = new Tmdb();
        Observable<MoviesService.CreditResponse> persons;
        persons = tmdb.moviesService().fetchMovieCredits(mMovieId);
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

                        if(type.equalsIgnoreCase("cast")) {
                            mPersonListView.setPersonList(creditResponse.cast);
                            Log.d("persons", creditResponse.cast.size() + "");
                        }
                        if(type.equalsIgnoreCase("crew")){
                            mPersonListView.setPersonList(creditResponse.crew);
                            Log.d("persons", creditResponse.crew.size() + "");
                        }
                    }
                });

    }

}
