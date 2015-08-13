package in.nash.cram;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by avinash on 8/13/15.
 */
public class CramApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, Secrets.PARSE_APP_ID, Secrets.PARSE_KEY);
    }
}
