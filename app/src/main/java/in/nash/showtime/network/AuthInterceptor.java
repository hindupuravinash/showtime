package in.nash.showtime.network;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import in.nash.showtime.Secrets;

/**
 * Created by avinash on 10/4/15.
 */
public final class AuthInterceptor implements Interceptor {

    private final String apiKey;
    public static final String PARAM_API_KEY = "api_key";

    public AuthInterceptor() {
        this.apiKey = Secrets.PARAM_API_KEY;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        HttpUrl url = chain.request().httpUrl();
        HttpUrl.Builder builder = url.newBuilder();
        builder.addQueryParameter(PARAM_API_KEY, apiKey);

        HttpUrl appendedUrl = builder.build();

        Request authenticatedRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .url(appendedUrl)
                .build();

        return chain.proceed(authenticatedRequest);
    }
}