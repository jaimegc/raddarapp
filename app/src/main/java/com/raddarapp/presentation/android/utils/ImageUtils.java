package com.raddarapp.presentation.android.utils;

import android.app.Activity;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Locale;

public class ImageUtils {

    private static final String DENSITY_MEDIUM = "mdpi";
    private static final String DENSITY_HIGH = "hdpi";
    private static final String DENSITY_XHIGH = "xhdpi";
    private static final String DENSITY_XXHIGH = "xxhdpi";
    private static final String DENSITY_XXXHIGH = "xxxhdpi";
    private Activity activity;

    public ImageUtils(Activity activity) {
        this.activity = activity;
    }

    public String getExtensionDensityLanguage() {
        String extensionDensityLanguage;

        extensionDensityLanguage = "-" + getDensity() + "-" + getLanguage() + ".png";

        return extensionDensityLanguage;
    }

    private String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        String LANGUAGE_ES = "es";
        String LANGUAGE_EN = "en";

        return language.equals(LANGUAGE_ES) ? LANGUAGE_ES : LANGUAGE_EN;
    }

    private String getDensity() {
        String density = DENSITY_XXXHIGH;
        float densityValue = activity.getResources().getDisplayMetrics().density;

        if (densityValue >= 4.0) {
            density = DENSITY_XXXHIGH;
        } else if (densityValue >= 3.0 && densityValue < 4.0) {
            density = DENSITY_XXHIGH;
        } else if (densityValue >= 2.0 && densityValue < 3.0) {
            density = DENSITY_XHIGH;
        } else if (densityValue >= 1.5 && densityValue < 2.0) {
            density = DENSITY_HIGH;
        } else if (densityValue >= 1.0 && densityValue < 1.5) {
            density = DENSITY_MEDIUM;
        }

        return density;

    }
}
