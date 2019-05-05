package com.raddarapp.presentation.android.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

    // FIXME: Incompleted
    public String numberToGroupedString(long number) {
        return rangeOrScopeToString(number);
    }

    public String numberToGroupedString(double number) {
        return formatDecimals(2).format(number);
    }

    public String numberToGroupedStringWithUnits(double number) {
        return formatDecimals(2).format(number) + "m";
    }

    public String numberToGroupedStringRaddarWithUnits(double number) {
        return formatDecimals(2).format(number);
    }

    public float rangeOrScopeToFloatInMap(float number) {
        return (number / 100.0f);
    }

    public String rangeOrScopeToString(long number) {
        return "" + formatNumber().format(number);
    }

    public String rangeOrScopeToStringDecimals(long number) {
        return formatDecimals(2).format((double) number / 100.0);
    }

    public String rangeOrScopeToStringWithoutDecimals(long number) {
        String range = formatDecimals(2).format((double) number / 100.0);
        return range.substring(0, range.length() - 3);
    }

    public String rangeOrScopeToStringPlus(long number) {
        return "+" + formatDecimals(2).format((double) number / 100.0);
    }

    public double calculateCircleRange(double range) {
        return range / 100.0;
    }

    public NumberFormat formatDecimals(int totalDecimals) {
        Locale locale = Locale.getDefault();
        NumberFormat result = NumberFormat.getInstance(locale);
        result.setMinimumFractionDigits(totalDecimals);
        result.setMaximumFractionDigits(totalDecimals);
        result.setRoundingMode(RoundingMode.DOWN);

        if (result instanceof DecimalFormat) {
            ((DecimalFormat) result).setDecimalSeparatorAlwaysShown(true);
        }
        return result;
    }

    public NumberFormat formatNumber() {
        Locale locale = Locale.getDefault();
        NumberFormat result = NumberFormat.getInstance(locale);
        return result;
    }

    public String humanReadableByteCount(long bytes) {
        int unit = 1000;

        if (bytes < unit) return bytes + "";

        int exp = (int) (Math.log(bytes) / Math.log(unit));

        String pre = String.valueOf("KMGTPE".charAt(exp-1));

        return String.format("%.1f%s", bytes / Math.pow(unit, exp), pre);
    }
}
