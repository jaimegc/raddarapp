package com.raddarapp.domain.model.enums;

public enum UserRoleType {

    ADMIN(0),

    USER(1),

    COMPANY(2),

    USER_INACTIVE(3);

    private final int value;

    UserRoleType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}