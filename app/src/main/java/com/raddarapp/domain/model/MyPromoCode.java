package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class MyPromoCode implements Identifiable<String> {

    private final String key;
    private final String code;
    private final String exchangeDate;
    private final boolean exchanged;
    private final long timesExchanged;
    private final String myUserKey;

    public MyPromoCode(String key, String code, String exchangeDate, boolean exchanged, long timesExchanged, String myUserKey) {
        this.key = key;
        this.code = code;
        this.exchangeDate = exchangeDate;
        this.exchanged = exchanged;
        this.timesExchanged = timesExchanged;
        this.myUserKey = myUserKey;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getCode() {
        return code;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public boolean isExchanged() {
        return exchanged;
    }

    public long getTimesExchanged() {
        return timesExchanged;
    }

    public String getMyUserKey() {
        return myUserKey;
    }
}