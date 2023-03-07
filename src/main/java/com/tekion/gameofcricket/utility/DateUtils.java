package com.tekion.gameofcricket.utility;

import java.text.SimpleDateFormat;


public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static String getCurrentDate() {
        return DATE_FORMAT.format(System.currentTimeMillis());
    }
}
