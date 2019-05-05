package com.raddarapp.data.android.repository.datasource.origin.remote.api.error;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;

public class ServerUnknownApiException extends ServerApiException {

    public ServerUnknownApiException(String httpCode, String httpMessage, String errorCode) {
        super(httpCode, httpMessage, errorCode, null);
    }

    public ServerUnknownApiException() {
        super("", "", "", null);
    }
}
