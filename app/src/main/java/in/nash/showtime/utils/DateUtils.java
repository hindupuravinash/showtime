package in.nash.showtime.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by avinash on 1/3/16.
 */
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String PRETTY_DATE_WITHOUT_TIME = "d MMM yyyy";
    public static final Locale LOCALE = Locale.US;

    public static Date parseDate(String dateStr) {
        return parseDateWithFormat(dateStr, DATE_FORMAT);
    }

    private static Date parseDateWithFormat(String dateStr, String format) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, LOCALE);

        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String toDateWithoutTime(String dateStr) {

        Date date = parseDate(dateStr);
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(PRETTY_DATE_WITHOUT_TIME, LOCALE);
            return dateFormat.format(date);
        } else {
            return "";
        }
    }

}
