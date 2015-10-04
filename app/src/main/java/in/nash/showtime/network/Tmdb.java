package in.nash.showtime.network;


import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import in.nash.showtime.ShowtimeApplication;
import in.nash.showtime.ui.Globals;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Avinash Hindupur on 7/20/15.
 */
public class Tmdb {

    public static final String API_URL = ("https://api.themoviedb.org/3");

    private boolean isDebug;
    private Retrofit restAdapter;

    public Tmdb() {
    }

    protected Retrofit.Builder newRestAdapterBuilder() {
        return new Retrofit.Builder();
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=60")
                    .build();
        }
    };

    protected Retrofit getRestAdapter() {

            File httpCacheDirectory = new File(ShowtimeApplication.getAppContext().getCacheDir(), Globals.CACHE_DIRECTORY);

            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.interceptors().add(new AuthInterceptor());

            return new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(API_URL)
                    .client(okHttpClient)
                    .build();
    }

    public MoviesService moviesService() {
        return getRestAdapter().create(MoviesService.class);
    }

    public PersonService personService() {
        return getRestAdapter().create(PersonService.class);
    }

    public DetailService detailService() {
        return getRestAdapter().create(DetailService.class);
    }

}