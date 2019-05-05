package com.raddarapp.domain.model.enums;

public enum FootprintMediaType {

    IMAGE(0),

    VIDEO(1),

    AUDIO(2),

    NOTE(3),

    GIF(4);

    private final int value;

    FootprintMediaType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
