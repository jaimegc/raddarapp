package com.raddarapp.presentation.android.error.local;

public enum ErrorLocalCode {

    SUCCESS(0),

    EMPTY_USERNAME(1),

    EMPTY_PASSWORD(2),

    EMPTY_IMAGE(3),

    EMPTY_TITLE(4),

    UNCLASSIFIED_FOOTPRINT_EMOJI_CATEGORY(5),

    EMPTY_DESCRIPTION(6),

    EMPTY_COMMENT(7),

    FILL_ALL_PASSWORDS(8),

    NEW_DIFFERENT_PASSWORDS(9),

    ALL_EQUALS_PASSWORDS(10),

    MINIMUM_RANGE_MINED(11),

    MAXIMUM_RANGE_MINED_PER_DAY(12),

    TERMS_CONDITIONS(13),

    EMPTY_EMAIL(14),

    EMAIL_PATTERN(15),

    EMPTY_BIRTHDATE(16),

    BIRTHDATE_PATTERN(17),

    UNDER_FOURTEEN(18),

    MAXIMUM_AGE(19),

    AGE_FUTURE(20),

    USERNAME_PATTERN(21),

    EMPTY_PROMO_CODE(22);

    private final int value;

    ErrorLocalCode(final int value) {
        this.value = value;
    }

    public final int getValue() { return value; }
}
