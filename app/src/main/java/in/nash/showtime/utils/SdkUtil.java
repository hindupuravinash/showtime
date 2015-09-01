package in.nash.showtime.utils;

import android.os.Build;

/**
 * Created by avinash on 9/1/15.
 */
public final class SdkUtil {

    public static boolean hasMarshmallow() {
        return has(Build.VERSION_CODES.M); //23
    }

    public static boolean hasLollipop() {
        return has(Build.VERSION_CODES.LOLLIPOP); //21
    }

    public static boolean hasKitKat() {
        return has(Build.VERSION_CODES.KITKAT); // 19
    }

    public static boolean hasJellyBean() {
        return has(Build.VERSION_CODES.JELLY_BEAN); // 16
    }

    public static boolean hasIceCreamSandwich() {
        return has(Build.VERSION_CODES.ICE_CREAM_SANDWICH); //14
    }

    private static int getLevel() {
        return android.os.Build.VERSION.SDK_INT;
    }

    private static boolean isBefore(int level) {
        return getLevel() < level;
    }

    private static boolean has(int level) {
        return !isBefore(level);
    }

    private SdkUtil() {
    }

}
