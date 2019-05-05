package com.raddarapp.domain.model.enums;

public enum FootprintLevel {

    CONVENCIONAL(1);

    private final int value;

    FootprintLevel(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
