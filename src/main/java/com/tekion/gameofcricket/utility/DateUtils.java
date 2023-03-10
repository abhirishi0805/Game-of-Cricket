package com.tekion.gameofcricket.utility;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final Pattern DATE_REGEX_PATTERN = Pattern.compile("([0-9]{2})-([0-9]{2})-([0-9]{4})");

    public static String getDateFormat() {
        return DATE_FORMAT.toPattern();
    }

    public static String getCurrentDate() {
        return DATE_FORMAT.format(System.currentTimeMillis());
    }

    public static boolean isValidFormat(String date) {
        return DATE_REGEX_PATTERN.matcher(date).matches();
    }
}
