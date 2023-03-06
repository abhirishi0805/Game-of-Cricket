package com.tekion.gameofcricket.utility;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static String getCurrentDate() {
        return DATE_FORMAT.format(new Date());
    }
}
