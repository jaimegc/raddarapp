package com.raddarapp.domain.model.enums;

public enum PromoCodeType {

    REGISTER("PR1");

    private final String value;

    PromoCodeType(final String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
