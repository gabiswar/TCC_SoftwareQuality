package com.finance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return sdf.parse(dateStr);
    }
}