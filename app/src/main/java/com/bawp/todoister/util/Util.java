package com.bawp.todoister.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM, D");

        return simpleDateFormat.format(date);
    }
}
