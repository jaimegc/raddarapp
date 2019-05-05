package com.raddarapp.presentation.android.utils;

public class StringUtils {

    private static final double KILOMETER_TO_METER = 1000.0;
    private static final String KILOMETER = "km";
    private static final String METER = "m";

    public String distanceFormat(double distance) {
        String result;

        if (distance < KILOMETER_TO_METER) {
            result = String.format("%.2f" + METER, distance);
        } else {
            result = String.format("%.2f" + KILOMETER, distance / KILOMETER_TO_METER);
        }

        return result;
    }

    public String accuracyFormat(float accuracy) {
        String result;
        Integer accuracyInt = (int) accuracy;

        if (accuracyInt < 100) {
            result = accuracyInt + "m";
        } else {
            result = "99m";
        }

        return result;
    }

    public String getHtmlColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
}
