package com.hsn.sureandroidtask.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by hassanshakeel on 2/27/18.
 */

public class Utils {

    public static final SimpleDateFormat UiDateFormat = new SimpleDateFormat("MMM dd, yyyy"); // second example
    public static final SimpleDateFormat UiDateTimeFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm:a"); // second example
    public static final SimpleDateFormat ServerDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH); // second example
    public static final SimpleDateFormat FilterDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // second example
    public static final SimpleDateFormat ServerDateFilterFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // second example

    public static String getDateTimeForUi() {
        return UiDateTimeFormat.format(System.currentTimeMillis());
    }

    public static String toUiDateFormat(String serverDate) {
        try {
            return UiDateTimeFormat.format(ServerDateTimeFormat.parse(serverDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return serverDate;
        }
    }


    public static Date parseServerDate(String serverDate) {
        try {
            return ServerDateTimeFormat.parse(serverDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseServerDateForFilter(String serverDate) {
        try {
            return ServerDateFilterFormat.parse(serverDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseFilterDate(String filterDate) {
        try {
            return FilterDateFormat.parse(filterDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
