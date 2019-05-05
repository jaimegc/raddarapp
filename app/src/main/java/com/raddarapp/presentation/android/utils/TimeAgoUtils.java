package com.raddarapp.presentation.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;

import com.raddarapp.R;

import java.util.Date;

/*
    https://github.com/kevinsawicki/java-timeago
 */

public class TimeAgoUtils {

    protected Context context;

    public TimeAgoUtils(Context context) {
        this.context = context;
    }

    public String timeAgo(Date date) {
        return timeAgo(date.getTime());
    }

    @SuppressLint("StringFormatInvalid")
    public String timeAgo(long millis) {
        long diff = new Date().getTime() - millis;

        Resources r = context.getResources();

        String prefix = r.getString(R.string.time_ago_prefix);
        String suffix = r.getString(R.string.time_ago_suffix);
        boolean isNow = false;

        double seconds = Math.abs(diff) / 1000;
        double minutes = seconds / 60;
        double hours = minutes / 60;
        double days = hours / 24;
        double years = days / 365;

        String words;

        if (seconds < 2) {
            isNow = true;
            words = r.getString(R.string.time_ago_now, Math.round(seconds));
        } else if (seconds < 45) {
            words = r.getString(R.string.time_ago_seconds, Math.round(seconds));
        } else if (seconds < 90) {
            words = r.getString(R.string.time_ago_minute, 1);
        } else if (minutes < 45) {
            words = r.getString(R.string.time_ago_minutes, Math.round(minutes));
        } else if (minutes < 90) {
            words = r.getString(R.string.time_ago_hour, 1);
        } else if (hours < 24) {
            words = r.getString(R.string.time_ago_hours, Math.round(hours));
        } else if (hours < 42) {
            words = r.getString(R.string.time_ago_day, 1);
        } else if (days < 30) {
            words = r.getString(R.string.time_ago_days, Math.round(days));
        } else if (days < 45) {
            words = r.getString(R.string.time_ago_month, 1);
        } else if (days < 365) {
            words = r.getString(R.string.time_ago_months, Math.round(days / 30));
        } else if (years < 1.5) {
            words = r.getString(R.string.time_ago_year, 1);
        } else {
            words = r.getString(R.string.time_ago_years, Math.round(years));
        }

        StringBuilder sb = new StringBuilder();

        if (!isNow) {
            if (prefix != null && prefix.length() > 0) {
                sb.append(prefix).append(" ");
            }

            sb.append(words);

            if (suffix != null && suffix.length() > 0) {
                sb.append(" ").append(suffix);
            }
        } else {
            sb.append(words);
        }

        return sb.toString().trim();
    }

    public void test() {
        final long second = 1000l;
        final long minute = second * 60;
        final long hour = minute * 60;
        final long day = hour * 24;
        final long month = day * 30;
        final long year = month * 12;

        long timeNow = System.currentTimeMillis();
        long timeOneSecond = timeNow - second;
        long timeTenSeconds = timeNow - second * 10;
        long timeOneMinute = timeNow - minute;
        long timeFortyMinute = timeNow - minute * 40;
        long timeOneHour = timeNow - hour;
        long timeFiveHours = timeNow - hour * 5;
        long timeOneDay = timeNow - day;
        long timeTwentyDays = timeNow - day * 20;
        long timeOneMonth = timeNow - month;
        long timeSixMonths = timeNow - month * 6;
        long timeOneYear = timeNow - year - day * 5; // 360 + 5 days
        long timeFiveYears = timeNow - year * 5;

        System.out.println("TEST TIME 1 Second: " + timeAgo(timeOneSecond));
        System.out.println("TEST TIME 10 Seconds: " + timeAgo(timeTenSeconds));
        System.out.println("TEST TIME 1 Minute: " + timeAgo(timeOneMinute));
        System.out.println("TEST TIME 40 Minutes: " + timeAgo(timeFortyMinute));
        System.out.println("TEST TIME 1 Hour: " + timeAgo(timeOneHour));
        System.out.println("TEST TIME 5 Hour: " + timeAgo(timeFiveHours));
        System.out.println("TEST TIME 1 Day: " + timeAgo(timeOneDay));
        System.out.println("TEST TIME 20 Days: " + timeAgo(timeTwentyDays));
        System.out.println("TEST TIME 1 Month: " + timeAgo(timeOneMonth));
        System.out.println("TEST TIME 6 Months: " + timeAgo(timeSixMonths));
        System.out.println("TEST TIME 1 Year: " + timeAgo(timeOneYear));
        System.out.println("TEST TIME 5 Years: " + timeAgo(timeFiveYears));
    }
}