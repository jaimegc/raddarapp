package com.raddarapp.domain.model.enums;

public enum FootprintType {

    FOOTPRINT(0),

    GREETING(1),

    MEDAL(2),

    INSIGNE(3),

    TREASURE(4);

    private final int value;

    FootprintType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
