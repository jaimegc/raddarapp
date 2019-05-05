package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class NotificationUrlDto {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }
}
