package com.raddarapp.domain.model.enums;

public enum VisibilityType {

    PUBLIC(0),

    FRIENDS(1),

    PERSONAL(2);

    private final int value;

    VisibilityType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
