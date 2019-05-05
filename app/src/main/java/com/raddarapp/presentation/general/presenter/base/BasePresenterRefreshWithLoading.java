package com.raddarapp.presentation.general.presenter.base;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.error.OnErrorCallback;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserNotExistApiException;
import com.raddarapp.presentation.android.error.remote.ConnectionError;
import com.raddarapp.presentation.android.error.remote.GenericError;
import com.raddarapp.presentation.android.error.remote.ServerAccessDataApiError;
import com.raddarapp.presentation.android.error.remote.ServerAlreadyExchangedPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerExpiredPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerBannedUserApiError;
import com.raddarapp.presentation.android.error.remote.ServerIncorrectPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerInvalidPromoCodeApiError;
import com.raddarapp.presentation.android.error.remote.ServerRepeatedEmailApiError;
import com.raddarapp.presentation.android.error.remote.ServerRepeatedUserApiError;
import com.raddarapp.presentation.android.error.remote.ServerUploadFileApiError;
import com.raddarapp.presentation.android.error.remote.ServerUserNotExistApiError;
import com.raddarapp.presentation.android.error.remote.ServerUserUnauthorizedApiError;
import com.raddarapp.presentation.android.error.remote.ServerConnectionError;
import com.raddarapp.presentation.android.error.remote.ServerUsernamePatternApiError;
import com.raddarapp.presentation.android.error.remote.UnknownError;
import com.raddarapp.presentation.general.presenter.base.contract.BasePresenterErrorsContract;
import com.raddarapp.presentation.general.presenter.base.contract.BasePresenterRefreshWithLoadingContract;

public abstract class BasePresenterRefreshWithLoading<T extends BasePresenterRefreshWithLoading.View>
        extends RosiePresenterWithLoading<T> implements BasePresenterRefreshWithLoadingContract {

    protected boolean isForceRefreshing = false;

    public BasePresenterRefreshWithLoading(UseCaseHandler useCaseHandler) {
        super(useCaseHandler);
        registerOnErrorCallback(onErrorCallback);
    }

    private final OnErrorCallback onErrorCallback = error -> {
        try {
            if (error instanceof ConnectionError) {
                getView().showConnectionError();
            } else if (error instanceof ServerConnectionError) {
                getView().showServerConnectionError();
            } else if (error instanceof GenericError) {
                getView().showGenericError();
            } else if (error instanceof ServerUploadFileApiError) {
                getView().showServerUploadFileApiError();
            } else if (error instanceof ServerRepeatedUserApiError) {
                getView().showServerRepeatedUserApiError();
            } else if (error instanceof ServerRepeatedEmailApiError) {
                getView().showServerRepeatedEmailApiError();
            } else if (error instanceof ServerExpiredPromoCodeApiError) {
                getView().showServerExpiredPromoCodeApiError();
            } else if (error instanceof ServerIncorrectPromoCodeApiError) {
                getView().showServerIncorrectPromoCodeApiError();
            } else if (error instanceof ServerAlreadyExchangedPromoCodeApiError) {
                getView().showServerAlreadyExchangedPromoCodeApiError();
            } else if (error instanceof ServerUsernamePatternApiError) {
                getView().showServerUsernamePatternApiError();
            } else if (error instanceof ServerInvalidPromoCodeApiError) {
                getView().showServerInvalidPromoCodeApiError();
            } else if (error instanceof ServerUserUnauthorizedApiError) {
                getView().showServerUnauthorizedApiError();
            } else if (error instanceof ServerUserNotExistApiError) {
                getView().showServerUserNotExistApiError();
            } else if (error instanceof ServerBannedUserApiError) {
                getView().showServerBannedUserApiError();
            } else if (error instanceof ServerAccessDataApiError){
                getView().showServerAccessDataApiError();
            } else if (error instanceof UnknownError){
                getView().showUnknownError();
            } else {
                getView().showSilentError();
            }
        } catch (Exception e) {}

        return true;
    };

    @Override
    public boolean isRefreshing() {
        return isForceRefreshing;
    }

    public interface View extends RosiePresenterWithLoading.View, BasePresenterErrorsContract {

    }
}
