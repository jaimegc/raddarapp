package com.raddarapp.presentation.general.presenter.base.contract;

public interface BasePresenterErrorsContract {
    void showGenericError();

    void showConnectionError();

    void showServerConnectionError();

    void showGpsDisabledError();

    void showGpsPermissionError();

    void showStoragePermissionError();

    void showStoragePermissionPromoCodeError();

    void showStoragePermissionInviteMyFriendsError();

    void showRecordAudioPermissionError();

    void showSpeedError();

    void showLocationError();

    void showLocationMockError();

    void showUploadImageProfileError();

    void showUploadAudioProfileError();

    void showUploadNotificationTopicError();

    // E00
    void showServerUnauthorizedApiError();

    // E01
    void showServerRepeatedUserApiError();

    // E02
    void showServerRepeatedEmailApiError();

    // E03
    void showServerBannedUserApiError();

    // E04
    void showServerAccessDataApiError();

    // E05
    void showServerUserNotExistApiError();

    // E10
    void showServerUploadFileApiError();

    // E15
    void showServerExpiredPromoCodeApiError();

    // E16
    void showServerIncorrectPromoCodeApiError();

    // E17
    void showServerAlreadyExchangedPromoCodeApiError();

    // E24
    void showServerUsernamePatternApiError();

    // E26
    void showServerInvalidPromoCodeApiError();

    // E99
    void showUnknownError();

    void showSilentError();
}
