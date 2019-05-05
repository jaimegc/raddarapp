package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class MyUserLoginSocialTokenDto {

    @SerializedName("social_token")
    private String token;

    public MyUserLoginSocialTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
