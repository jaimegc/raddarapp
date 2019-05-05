package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UpdateMobileLanguageDto {

    @SerializedName("language")
    private final String mobileLanguage;

    public UpdateMobileLanguageDto(String mobileLanguage) {
        this.mobileLanguage = mobileLanguage;
    }

    public String getMobileLanguage() {
        return mobileLanguage;
    }
}
