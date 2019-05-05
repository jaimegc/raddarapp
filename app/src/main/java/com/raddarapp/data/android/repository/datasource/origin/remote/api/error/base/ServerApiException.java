package com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base;

public class ServerApiException extends Exception {

    private final String httpCode;
    private final String httpMessage;
    private final String errorCode;

    public ServerApiException(String httpCode, String httpMessage, String errorCode, Throwable cause) {
        super(httpMessage, cause);
        this.httpCode = httpCode;
        this.httpMessage = httpMessage;
        this.errorCode = errorCode;
    }

    public ServerApiException(String httpMessage, Throwable cause) {
        super(httpMessage, cause);
        this.httpCode = "";
        this.httpMessage = "";
        this.errorCode = "";
    }

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
