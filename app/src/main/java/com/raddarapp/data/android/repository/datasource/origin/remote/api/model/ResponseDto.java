package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class ResponseDto {

    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }
}
