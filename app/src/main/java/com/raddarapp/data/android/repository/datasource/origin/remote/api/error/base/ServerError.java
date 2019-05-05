package com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base;

import com.google.gson.annotations.SerializedName;

public class ServerError {
    @SerializedName("http_code")
    private String httpCode;
    @SerializedName("http_message")
    private String httpMessage;
    @SerializedName("error_code")
    private String errorCode;

    public String getHttpCode() {
        return httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
