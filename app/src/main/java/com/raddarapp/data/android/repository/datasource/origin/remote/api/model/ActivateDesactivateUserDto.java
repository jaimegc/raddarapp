package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class ActivateDesactivateUserDto {

    @SerializedName("userId")
    private String userId;

    public ActivateDesactivateUserDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
