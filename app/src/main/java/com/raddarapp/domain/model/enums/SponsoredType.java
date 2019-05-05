package com.raddarapp.domain.model.enums;

public enum SponsoredType {

    NO_SPONSORED(0),

    ENTERPRISE(1),

    INFLUENCER(2);

    private final int value;

    SponsoredType(final int value) {
        this.value = value;
    }

    public int getValue() { return value; }
}