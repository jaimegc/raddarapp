package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class PromoCodeDto {

    @SerializedName("code")
    private String promoCode;

    public String getPromoCode() {
        return promoCode;
    }
}
