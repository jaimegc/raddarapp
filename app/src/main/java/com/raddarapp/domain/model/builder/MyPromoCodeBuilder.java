package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyPromoCode;

public class MyPromoCodeBuilder {

    private String key;
    private String code;
    private String exchangeDate;
    private boolean exchanged;
    private long timesExchanged;
    private String myUserKey;

    public MyPromoCodeBuilder() {}

    public MyPromoCode build() {
        return new MyPromoCode(key, code, exchangeDate, exchanged, timesExchanged, myUserKey);
    }

    public MyPromoCodeBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyPromoCodeBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public MyPromoCodeBuilder withExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
        return this;
    }

    public MyPromoCodeBuilder withExchanged(boolean exchanged) {
        this.exchanged = exchanged;
        return this;
    }

    public MyPromoCodeBuilder withTimesExchanged(long timesExchanged) {
        this.timesExchanged = timesExchanged;
        return this;
    }

    public MyPromoCodeBuilder withMyUserKey(String myUserKey) {
        this.myUserKey = myUserKey;
        return this;
    }
}
