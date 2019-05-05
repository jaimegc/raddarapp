package com.raddarapp.presentation.general.validation.utils;

import android.text.TextUtils;

import com.raddarapp.presentation.android.utils.DateUtils;

import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{1,15}$";

    public boolean isEmptyTrim(String field) {
        field = field.trim();
        
        return TextUtils.isEmpty(field);
    }

    public boolean isEmpty(String field) {
        return TextUtils.isEmpty(field);
    }

    public boolean equals(String field1, String field2) {
        return field1.equals(field2);
    }

    public boolean equals(String field1, String field2, String field3) {
        return field1.equals(field2) && field1.equals(field3);
    }

    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidDate(String birthdate) {
        return new DateUtils().isValidDateProfile(birthdate);
    }

    public int calculateAgeFromDate(String birthdate) {
        return new DateUtils().calculateAgeFromDate(birthdate);
    }

    public boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_PATTERN, username);
    }
}
