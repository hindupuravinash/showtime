package in.nash.showtime.network;


import com.squareup.okhttp.Cache;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import in.nash.showtime.ShowtimeApplication;
import in.nash.showtime.ui.Globals;
import in.nash.showtime.utils.ConnectivityUtils;
import in.nash.showtime.utils.StethoUtil;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Avinash Hindupur on 7/20/15.
 */
public class Tmdb {

    public static final HttpUrl API_URL = HttpUrl.parse("https://api.themoviedb.org/3/");

    public Tmdb() {
    }

    protected Retrofit.Builder newRestAdapterBuilder() {
        return new Retrofit.Builder();
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());

            if (ConnectivityUtils.isConnected(ShowtimeApplication.getAppContext())) {
                int maxAge = Globals.CACHE_MAX_AGE__NETWORK_CONNECTED; // read from cache for 10 minutes if connected

                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();

            } else {
                int maxStale = Globals.SECONDS_IN_MIN * Globals.MINUTES_IN_HOUR * Globals.HOURS_IN_DAY * Globals.CACHE_VALID_DAYS; // tolerate 1 month stale

                return originalResponse.newBuilder()
                        .header("Cache-Control",
                                "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

        }
    };

    protected Retrofit getRestAdapter() {

        File httpCacheDirectory = new File(ShowtimeApplication.getAppContext().getCacheDir(), Globals.CACHE_DIRECTORY);

        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient();
        StethoUtil.addStethoIntercepter(okHttpClient);
        okHttpClient.networkInterceptors().add(new AuthInterceptor());
        okHttpClient.setCache(cache);

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