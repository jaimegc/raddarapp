package com.raddarapp.domain.model.enums;

public enum AddVoteType {

    ADD_LIKE(0),

    ADD_DISLIKE(1);

    private final int value;

    AddVoteType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
