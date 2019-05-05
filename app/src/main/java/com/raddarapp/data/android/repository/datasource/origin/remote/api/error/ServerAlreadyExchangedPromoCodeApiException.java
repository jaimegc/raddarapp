package com.raddarapp.data.android.repository.datasource.origin.remote.api.error;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;

public class ServerAlreadyExchangedPromoCodeApiException extends ServerApiException {

    public ServerAlreadyExchangedPromoCodeApiException(String httpCode, String httpMessage, String errorCode) {
        super(httpCode, httpMessage, errorCode, null);
    }
}
