package in.nash.showtime;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by avinash on 8/13/15.
 */
public class ShowtimeApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, Secrets.PARSE_APP_ID, Secrets.PARSE_KEY);
    }
}
