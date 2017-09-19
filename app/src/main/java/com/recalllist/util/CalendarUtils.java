package com.recalllist.util;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by estevao on 19/09/17.
 */
public class CalendarUtils {


    private static String actualTime;

    public static String getStringOfActualDate(Long millis) {
        if (millis != null) {
            Calendar now = Calendar.getInstance();
            long milliseconds = now.getTimeInMillis() - millis;

            long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
            long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);

            if (days > 0 && days == 1)
                return "Ontem";
            else if (days > 1)
                return days + " Dias";
            else if (hours > 0 && hours > 1)
                return hours + " Horas";
            else if (hours > 0)
                return hours + " Hora";
            else if (minutes > 0 && minutes > 1)
                return minutes + " Mins";
            else if (minutes > 0)
                return minutes + " Min";
            else return "Agora";
        }
        return "-";
    }

    public static Long getActualTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}