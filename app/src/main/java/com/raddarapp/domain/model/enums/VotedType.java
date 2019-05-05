package com.raddarapp.domain.model.enums;

public enum VotedType {

    VOTE_LIKE(0),

    VOTE_SAVE_COLLECTION_LIKE(1),

    VOTE_DISLIKE(2),

    VOTE_SAVE_COLLECTION_DISLIKE(3);

    private final int value;

    VotedType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}
