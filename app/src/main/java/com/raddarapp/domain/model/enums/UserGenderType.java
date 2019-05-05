package com.raddarapp.domain.model.enums;

public enum UserGenderType {

    MALE(0),

    FEMALE(1),

    OTHER(2);

    private final int value;

    UserGenderType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
