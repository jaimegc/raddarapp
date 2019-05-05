package com.raddarapp.domain.model.enums;

public enum UserStatusType {

    NO_ACTIVE(0),

    ACTIVE(1),

    BANNED(2),

    INACTIVE(3);

    private final int value;

    UserStatusType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}