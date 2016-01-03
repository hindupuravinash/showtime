package in.nash.showtime;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.parse.Parse;

import io.fabric.sdk.android.Fabric;

/**
 * Created by avinash on 8/13/15.
 */
public class ShowtimeApplication extends Application{

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Answers(), new Crashlytics());

        //StethoUtil.initStetho(this);

        Parse.initialize(this, Secrets.PARSE_APP_ID, Secrets.PARSE_KEY);

        sApplicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }

}
