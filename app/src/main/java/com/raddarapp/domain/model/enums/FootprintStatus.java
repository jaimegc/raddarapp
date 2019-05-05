package com.raddarapp.domain.model.enums;

public enum FootprintStatus {

    NORMAL(0),
    DEAD(2);

    private final int value;

    FootprintStatus(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
