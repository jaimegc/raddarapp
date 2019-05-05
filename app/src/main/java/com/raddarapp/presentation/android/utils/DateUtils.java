package com.raddarapp.presentation.android.utils;

import com.raddarapp.BuildConfig;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    private static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String FORMAT_HYPHEN = "yyyy-MM-dd";
    private static final String FORMAT_PROFILE = "dd/MM/yyyy";
    private static final String HTTP_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static final String ADD_UTC_FORMAT = "T00:00:00.000Z";
    private final static String API_FAKE = "API_FAKE";
    private final static boolean IS_FAKE = BuildConfig.DATA_ORIGIN.equals(API_FAKE);
    private final static long MS_24H = 60 * 60 * 24 * 1000;

    public long stringDateToMilliseconds(String date) {
        date = date.replace("Z", "+00:00");
        DateFormat df = new SimpleDateFormat(ISO8601_FORMAT);
        Date result;

        try {
            result = df.parse(date);
            return result.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    public long stringDateToMillisecondsProfile(String date) {
        DateFormat df = new SimpleDateFormat(FORMAT_PROFILE);
        Date result;

        try {
            result = df.parse(date);
            return result.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String millisecondsToString(long milliseconds) {
        Date d = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_FORMAT);

        return sdf.format(d);
    }

    public String millisecondsToStringUTC(long milliseconds) {
        Date d = new Date(milliseconds);
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(d);
    }

    public String stringDateToBirthdateFormat(String date) {
        Long millis = stringDateToMilliseconds(date);

        DateFormat df = new SimpleDateFormat(FORMAT_PROFILE);
        Date result = new Date(millis);

        return df.format(result);
    }

    public String stringDateToBirthdate(String date) {
        if (!date.isEmpty()) {
            return date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        } else {
            return "";
        }
    }

    public String mapperDateProfileToDateComplete(String dateProfile) {
        String dateMapped = "";

        dateMapped = dateProfile.substring(6, 10) + "-" + dateProfile.substring(3, 5) + "-" + dateProfile.substring(0, 2);
        dateMapped += ADD_UTC_FORMAT;

        return dateMapped;
    }

    public boolean isSameDateNowInUTC(String dateString) {
        Long timeNow = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_HYPHEN);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        String timeNowToString = sdf.format(new Date(timeNow));
        String timeToString;

        try {
            Date date = sdf.parse(dateString);
            timeToString = sdf.format(date);
        } catch (ParseException e) {
            return false;
        }

        return timeNowToString.equals(timeNowToString);
    }

    public long millisfromServerDateToMidnight(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(HTTP_FORMAT, Locale.US);

        long millisNowByServer;

        if (IS_FAKE) {
            dateString = millisToHttpFormatString(System.currentTimeMillis());
        }

        try {
            Date date = sdf.parse(dateString);

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.setTime(date);

            millisNowByServer = date.getTime();

            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            long timeMidnight = cal.getTimeInMillis() + 1000;

            return timeMidnight - millisNowByServer;

        } catch (ParseException e) {
            return 0;
        }
    }

    private String millisToHttpFormatString(Long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(HTTP_FORMAT, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String timeHttpFormatString = sdf.format(new Date(millis));

        return timeHttpFormatString;
    }
    
    public boolean isValidDateProfile(String dateString) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_PROFILE);
        sdf.setLenient(false);

        if (dateString.length() != 10) { // dd/MM/yyyy
            return false;
        }

        try {
            date = sdf.parse(dateString);

        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public int calculateAgeFromDate(String date) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        int age = 0;

        int day = Integer.valueOf(date.substring(0, 2));
        int month = Integer.valueOf(date.substring(3, 5)) - 1;
        int year = Integer.valueOf(date.substring(6, 10));

        birthDate.set(year, month, day);

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
                today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    public String actualDateForDateDialog() {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH) + 1) + "-" + today.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isRecent(Long dateMs) {
        return new Date().getTime() - dateMs <= MS_24H;
    }

    /*public static void main(String[] args) {
        //long ruina = millisfromServerDateToMidnight("Thu, 01 Mar 2018 17:05:23 GMT");
        //System.out.println(stringDateToBirthdateFormat("2018-03-04T00:00:00.000Z"));
        //System.out.println("RUINA: " + isValidDateProfile("10/02/1990"));
        //System.out.println("RUINA: " + isValidDateProfile("29/02/2018"));
        System.out.println("RUINA: " + calculateAgeFromDate("29/02/2015"));
        System.out.println("RUINA: " + calculateAgeFromDate("10/02/1986"));
        System.out.println("RUINA: " + calculateAgeFromDate("10/03/1990"));
        System.out.println("RUINA: " + calculateAgeFromDate("08/04/2018"));
    }

    private static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append(":");
        } else {
            sb.append(hour).append(":");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append(":");
        } else {
            sb.append(minute).append(":");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }

        return sb.toString();
    }*/
}
