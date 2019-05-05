package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UpdateComplimentDto {

    @SerializedName("userId")
    private String userId;
    @SerializedName("compliments")
    private long compliments;

    public UpdateComplimentDto(String userId, long compliments) {
        this.userId = userId;
        this.compliments = compliments;
    }

    public String getUserId() {
        return userId;
    }

    public long getCompliments() {
        return compliments;
    }
}
