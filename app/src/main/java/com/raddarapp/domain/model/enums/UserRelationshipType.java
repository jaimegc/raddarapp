package com.raddarapp.domain.model.enums;

public enum UserRelationshipType {
    UNKNOWN(0),

    FOLLOWING(1),

    FOLLOW_ME(2),

    FRIEND(3),

    ME(4);

    private final int value;

    UserRelationshipType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
