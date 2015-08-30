package in.nash.showtime.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import in.nash.showtime.BuildConfig;

/**
 * Created by avinash on 7/21/15.
 */
public class AppUtils {

    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static void navigateToActivitySimple(Activity fromActivity, Class toActivity){
        Intent intent = new Intent(fromActivity, toActivity);
        ActivityCompat.startActivity(fromActivity, intent, new Bundle());
    }
}
