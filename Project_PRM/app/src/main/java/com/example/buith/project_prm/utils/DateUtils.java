package com.example.buith.project_prm.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    private static final Calendar calendar = Calendar.getInstance();
    public static String getTimeByFormat(Date date, String format) {
        if (date == null || TextUtils.isEmpty(format))
            return null;
        calendar.setTimeInMillis(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }
}
