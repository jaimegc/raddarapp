package com.raddarapp.presentation.android.utils;

import android.widget.ImageView;
import android.widget.TextView;

import com.raddarapp.R;

public class StarsUtils {

    private static final int STARS_RESOURCES[] = {R.drawable.ic_stars_zero, R.drawable.ic_stars_one, R.drawable.ic_stars_one_half,
            R.drawable.ic_stars_two, R.drawable.ic_stars_two_half, R.drawable.ic_stars_three, R.drawable.ic_stars_three_half,
            R.drawable.ic_stars_four, R.drawable.ic_stars_four_half, R.drawable.ic_stars_five};
    private static final int INDEX_ZERO_STARS = 0;
    private static final int INDEX_ONE_STAR = 1;
    private static final int INDEX_ONE_HALF_STAR = 2;
    private static final int INDEX_TWO_STARS = 3;
    private static final int INDEX_TWO_HALF_STARS = 4;
    private static final int INDEX_THREE_STARS = 5;
    private static final int INDEX_THREE_HALF_STARS = 6;
    private static final int INDEX_FOUR_STARS = 7;
    private static final int INDEX_FOUR_HALF_STARS = 8;
    private static final int INDEX_FIVE_STARS = 9;

    public void calculateStars(ImageView imageStars, TextView totalStars, TextView totalStarsVoted, TextView miniDecimals, long likes, long dislikes) {
        NumberUtils numberUtils = new NumberUtils();
        long total = likes + dislikes;
        String doubleString;
        int starsRes;

        if (total != 0) {
            doubleString = numberUtils.formatDecimals(3).format((double) (likes * 5 + dislikes) / total);
            double value = Double.valueOf(doubleString.replace(",", "."));

            if (value >= 1.0 && value <= 1.099) {
                starsRes = STARS_RESOURCES[INDEX_ONE_STAR];
            } else if (value >= 1.1 && value <= 1.999) {
                starsRes = STARS_RESOURCES[INDEX_ONE_HALF_STAR];
            } else if (value >= 2.0 && value <= 2.099) {
                starsRes = STARS_RESOURCES[INDEX_TWO_STARS];
            } else if (value >= 2.1 && value <= 2.999) {
                starsRes = STARS_RESOURCES[INDEX_TWO_HALF_STARS];
            } else if (value >= 3.0 && value <= 3.099) {
                starsRes = STARS_RESOURCES[INDEX_THREE_STARS];
            } else if (value >= 3.1 && value <= 3.999) {
                starsRes = STARS_RESOURCES[INDEX_THREE_HALF_STARS];
            } else if (value >= 4.0 && value <= 4.099) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_STARS];
            } else if (value >= 4.1 && value <= 4.999) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_HALF_STARS];
            } else {
                starsRes = STARS_RESOURCES[INDEX_FIVE_STARS];
            }
        } else {
            starsRes = STARS_RESOURCES[INDEX_ZERO_STARS];
            doubleString = numberUtils.formatDecimals(3).format(0.0);
        }

        imageStars.setImageResource(starsRes);
        totalStars.setText(doubleString.substring(0, doubleString.length() - 2));
        miniDecimals.setText(doubleString.substring(doubleString.length() - 2, doubleString.length()));
        totalStarsVoted.setText(" (" + total + ")");
    }

    public void calculateStars(TextView totalStars, TextView miniDecimals, long likes, long dislikes) {
        NumberUtils numberUtils = new NumberUtils();
        long total = likes + dislikes;
        String doubleString;
        int starsRes;

        if (total != 0) {
            doubleString = numberUtils.formatDecimals(3).format((double) (likes * 5 + dislikes) / total);
            double value = Double.valueOf(doubleString.replace(",", "."));

            if (value >= 1.0 && value <= 1.099) {
                starsRes = STARS_RESOURCES[INDEX_ONE_STAR];
            } else if (value >= 1.1 && value <= 1.999) {
                starsRes = STARS_RESOURCES[INDEX_ONE_HALF_STAR];
            } else if (value >= 2.0 && value <= 2.099) {
                starsRes = STARS_RESOURCES[INDEX_TWO_STARS];
            } else if (value >= 2.1 && value <= 2.999) {
                starsRes = STARS_RESOURCES[INDEX_TWO_HALF_STARS];
            } else if (value >= 3.0 && value <= 3.099) {
                starsRes = STARS_RESOURCES[INDEX_THREE_STARS];
            } else if (value >= 3.1 && value <= 3.999) {
                starsRes = STARS_RESOURCES[INDEX_THREE_HALF_STARS];
            } else if (value >= 4.0 && value <= 4.099) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_STARS];
            } else if (value >= 4.1 && value <= 4.999) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_HALF_STARS];
            } else {
                starsRes = STARS_RESOURCES[INDEX_FIVE_STARS];
            }
        } else {
            starsRes = STARS_RESOURCES[INDEX_ZERO_STARS];
            doubleString = numberUtils.formatDecimals(3).format(0.0);
        }

        totalStars.setText(doubleString.substring(0, doubleString.length() - 2));
        miniDecimals.setText(doubleString.substring(doubleString.length() - 2, doubleString.length()));
    }

    public void calculateStars(ImageView imageStars, long likes, long dislikes) {
        NumberUtils numberUtils = new NumberUtils();
        long total = likes + dislikes;
        int starsRes;

        if (total != 0) {
            String doubleString = numberUtils.formatDecimals(3).format((double) (likes * 5 + dislikes) / total);
            double value = Double.valueOf(doubleString.replace(",", "."));

            if (value == 1.0) {
                starsRes = STARS_RESOURCES[INDEX_ONE_STAR];
            } else if (value > 1.0 && value <= 1.999) {
                starsRes = STARS_RESOURCES[INDEX_ONE_HALF_STAR];
            } else if (value == 2.0) {
                starsRes = STARS_RESOURCES[INDEX_TWO_STARS];
            } else if (value > 2.0 && value <= 2.999) {
                starsRes = STARS_RESOURCES[INDEX_TWO_HALF_STARS];
            } else if (value == 3.0) {
                starsRes = STARS_RESOURCES[INDEX_THREE_STARS];
            } else if (value > 3.0 && value <= 3.999) {
                starsRes = STARS_RESOURCES[INDEX_THREE_HALF_STARS];
            } else if (value == 4.0) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_STARS];
            } else if (value > 4.0 && value <= 4.999) {
                starsRes = STARS_RESOURCES[INDEX_FOUR_HALF_STARS];
            } else {
                starsRes = STARS_RESOURCES[INDEX_FIVE_STARS];
            }
        } else {
            starsRes = STARS_RESOURCES[INDEX_ZERO_STARS];
        }

        imageStars.setImageResource(starsRes);
    }
}
