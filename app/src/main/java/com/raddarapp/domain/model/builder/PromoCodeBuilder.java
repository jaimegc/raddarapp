package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.PromoCode;

public class PromoCodeBuilder {

    private String key;
    private String promoCode;

    public PromoCodeBuilder() {}

    public PromoCode build() {
        return new PromoCode(key, promoCode);
    }

    public PromoCodeBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public PromoCodeBuilder withMediaName(String mediaName) {
        this.promoCode = mediaName;
        return this;
    }
}
