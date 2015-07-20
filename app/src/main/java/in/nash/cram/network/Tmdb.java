package in.nash.cram.network;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

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
            RestAdapter.Builder builder = newRestAdapterBuilder();
            builder.setEndpoint(API_URL);
            builder.setRequestInterceptor(new RequestInterceptor() {
                public void intercept(RequestFacade requestFacade) {
                    requestFacade.addQueryParam(PARAM_API_KEY, apiKey);
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
}