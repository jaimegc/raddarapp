package com.raddarapp.domain.model.enums;

public enum AspectRatioType {

    SQUARE(0),

    FOUR_FIVE(1),

    FIVE_FOUR(2),

    NINE_SIXTEEN(3),

    SIXTEEN_NINE(4);

    private final int value;

    AspectRatioType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
