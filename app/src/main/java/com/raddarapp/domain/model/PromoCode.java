package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class PromoCode implements Identifiable<String> {

    private final String key;
    private final String promoCode;

    public PromoCode(String key, String promoCode) {
        this.key = key;
        this.promoCode = promoCode;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getPromoCode() {
        return promoCode;
    }
}
