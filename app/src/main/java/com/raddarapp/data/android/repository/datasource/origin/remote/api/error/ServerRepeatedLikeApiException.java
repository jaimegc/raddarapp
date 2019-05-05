package com.raddarapp.data.android.repository.datasource.origin.remote.api.error;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;

public class ServerRepeatedLikeApiException extends ServerApiException {

    public ServerRepeatedLikeApiException(String httpCode, String httpMessage, String errorCode) {
        super(httpCode, httpMessage, errorCode, null);
    }
}
