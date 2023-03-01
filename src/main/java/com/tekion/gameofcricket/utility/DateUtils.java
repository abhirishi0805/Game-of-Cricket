package com.tekion.gameofcricket.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}
