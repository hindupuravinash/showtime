package in.nash.showtime.network;


import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import in.nash.showtime.Secrets;
import in.nash.showtime.ShowtimeApplication;
import in.nash.showtime.ui.Globals;
import in.nash.showtime.utils.ConnectivityUtils;
import in.nash.showtime.utils.StethoUtil;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Avinash Hindupur on 7/20/15.
 */
public class Tmdb {

    public static final String API_URL = "https://api.themoviedb.org/3";

    public static final String PARAM_API_KEY = "api_key";

    private String apiKey;
    private boolean isDebug;
    private RestAdapter restAdapter;

    public Tmdb() {
    }

    public Tmdb setApiKey(String value) {
        this.apiKey = value;
        restAdapter = null;
        return this;
    }

    protected RestAdapter.Builder newRestAdapterBuilder() {
        return new RestAdapter.Builder();
    }

    protected RestAdapter getRestAdapter() {

        if (restAdapter == null) {

            File httpCacheDirectory = new File(ShowtimeApplication.getAppContext().getCacheDir(), Globals.CACHE_DIRECTORY);

            Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setCache(cache);
            StethoUtil.addStethoIntercepter(okHttpClient);

            RestAdapter.Builder builder = newRestAdapterBuilder();
            builder.setEndpoint(API_URL)
                    .setClient(new OkClient(okHttpClient))
                    .setRequestInterceptor(new RequestInterceptor() {
                        public void intercept(RequestFacade requestFacade) {
                            requestFacade.addQueryParam(PARAM_API_KEY, Secrets.PARAM_API_KEY);

                            if (ConnectivityUtils.isConnected(ShowtimeApplication.getAppContext())) {
                                int maxAge = Globals.CACHE_MAX_AGE__NETWORK_CONNECTED; // read from cache for 10 minutes if connected
                                requestFacade.addHeader("Cache-Control", "public, max-age=" + maxAge);
                            } else {
                                int maxStale = Globals.SECONDS_IN_MIN * Globals.MINUTES_IN_HOUR
                                        * Globals.HOURS_IN_DAY * Globals.CACHE_VALID_DAYS; // tolerate 1 month stale
                                requestFacade.addHeader("Cache-Control",
                                        "public, only-if-cached, max-stale=" + maxStale);
                            }
                        }
                    });

            if (isDebug) {
                builder.setLogLevel(RestAdapter.LogLevel.FULL);
            }

            restAdapter = builder.build();
        }

        return restAdapter;
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