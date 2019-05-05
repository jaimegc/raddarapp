package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class MyPromoCodeDto {

    @SerializedName("code")
    private String code;
    @SerializedName("exchangeDate")
    private String exchangeDate;
    @SerializedName("exchanged")
    private boolean exchanged;
    @SerializedName("timesExchanged")
    private long timesExchanged;
    @SerializedName("userId")
    private String myUserId;

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

    public String getMyUserId() {
        return myUserId;
    }
}