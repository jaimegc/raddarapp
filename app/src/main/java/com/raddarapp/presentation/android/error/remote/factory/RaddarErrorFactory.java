package com.raddarapp.presentation.android.error.remote.factory;

import android.content.Context;
import android.net.ConnectivityManager;

import com.karumi.rosie.domain.usecase.error.ErrorFactory;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerAccessDataApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerAlreadyExchangedPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerConnectionApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerExpiredPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerGenericApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerBannedUserApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerIncorrectPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerInvalidPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedEmailApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedUserApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUnknownApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUploadFileApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserNotExistApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserUnauthorizedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUsernamePatternApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.presentation.android.error.remote.ConnectionError;
import com.raddarapp.presentation.android.error.remote.GenericError;
import com.raddarapp.presentation.android.error.remote.ServerAccessDataApiError;
import com.raddarapp.presentation.android.error.remote.ServerAlreadyExchangedPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerConnectionError;
import com.raddarapp.presentation.android.error.remote.ServerExpiredPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerBannedUserApiError;
import com.raddarapp.presentation.android.error.remote.ServerIncorrectPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerInvalidPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerRepeatedEmailApiError;
import com.raddarapp.presentation.android.error.remote.ServerRepeatedUserApiError;
import com.raddarapp.presentation.android.error.remote.ServerUploadFileApiError;
import com.raddarapp.presentation.android.error.remote.ServerUserNotExistApiError;
import com.raddarapp.presentation.android.error.remote.ServerUserUnauthorizedApiError;
import com.raddarapp.presentation.android.error.remote.ServerUsernamePatternApiError;
import com.raddarapp.presentation.android.error.remote.SilentError;
import com.raddarapp.presentation.android.error.remote.UnknownError;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

public class RaddarErrorFactory extends ErrorFactory {

    @Inject
    Context context;

    @Inject public RaddarErrorFactory() {
    }

    @Override
    public Error create(Exception exception) {
        Throwable targetException = exception;

        if (exception instanceof InvocationTargetException) {
            targetException = ((InvocationTargetException) exception).getTargetException();
        }

        if (targetException instanceof ServerApiException) {
            ServerApiException serverApiException = (ServerApiException) targetException;

            if (serverApiException instanceof ServerConnectionApiException) {

                if (!isNetworkAvailable()) {
                    return new ConnectionError();
                } else {
                    return new ServerConnectionError();
                }

            } else if (serverApiException instanceof ServerGenericApiException) {
                return new GenericError();
            } else if (serverApiException instanceof ServerUserUnauthorizedApiException) {
                // E00
                return new ServerUserUnauthorizedApiError();
            } else if (serverApiException instanceof ServerRepeatedUserApiException) {
                // E01
                return new ServerRepeatedUserApiError();
            } else if (serverApiException instanceof ServerRepeatedEmailApiException) {
                // E02
                return new ServerRepeatedEmailApiError();
            } else if (serverApiException instanceof ServerBannedUserApiException) {
                // E03
                return new ServerBannedUserApiError();
            } else if (serverApiException instanceof ServerAccessDataApiException) {
                // E04
                return new ServerAccessDataApiError();
            } else if (serverApiException instanceof ServerUserNotExistApiException) {
                // E05
                return new ServerUserNotExistApiError();
            } else if (serverApiException instanceof ServerUploadFileApiException) {
                // E10
                return new ServerUploadFileApiError();
            } else if (serverApiException instanceof ServerExpiredPromoCodeApiException) {
                // E15
                return new ServerExpiredPromoCodeApiError();
            } else if (serverApiException instanceof ServerIncorrectPromoCodeApiException) {
                // E16
                return new ServerIncorrectPromoCodeApiError();
            } else if (serverApiException instanceof ServerAlreadyExchangedPromoCodeApiException) {
                // E17
                return new ServerAlreadyExchangedPromoCodeApiError();
            } else if (serverApiException instanceof ServerUsernamePatternApiException) {
                // E24
                return new ServerUsernamePatternApiError();
            } else if (serverApiException instanceof ServerInvalidPromoCodeApiException) {
                // E26
                return new ServerInvalidPromoCodeApiError();
            } else if (serverApiException instanceof ServerUnknownApiException) {
                return new UnknownError();
            }
        }

        // FIXME: Sometimes when we back from all footprints & users ranking screen while
        // the peding request is not load yet throws this error. We hide the message
        return new SilentError();
    }

    private boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
