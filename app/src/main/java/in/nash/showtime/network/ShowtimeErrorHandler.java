package in.nash.showtime.network;

import android.content.Context;

import java.net.HttpURLConnection;

import in.nash.showtime.ShowtimeApplication;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by avinash on 9/11/15.
 */
public class ShowtimeErrorHandler implements ErrorHandler {

    private Context mContext;

    public ShowtimeErrorHandler() {
        this.mContext = ShowtimeApplication.getAppContext();
    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause != null) {
            switch (cause.getKind()) {
                case NETWORK:
                    logError(cause);
                    break;

                default:
                    Response r = cause.getResponse();
                    if (r == null) return cause;

                    if (r.getStatus() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                        logError(cause);

                    } else if (r.getStatus() == HttpURLConnection.HTTP_FORBIDDEN) {
                        logError(cause);
                    } else if (r.getStatus() == HttpURLConnection.HTTP_NOT_FOUND) {
                        logError(cause);
                    } else if (r.getStatus() >= HttpURLConnection.HTTP_BAD_REQUEST
                            && r.getStatus() < HttpURLConnection.HTTP_INTERNAL_ERROR) {
                        logError(cause);

                    } else if (r.getStatus() >= HttpURLConnection.HTTP_INTERNAL_ERROR) {
                        logError(cause);

                    }
            }
        }
        return cause;
    }

    private void logError(RetrofitError cause) {
        //   Crashlytics.getInstance().core.setString("kind", cause.getKind());
        //   Crashlytics.getInstance().core.setString("message", cause.getMessage());
        //   Crashlytics.getInstance().core.logException(cause);
    }

}
