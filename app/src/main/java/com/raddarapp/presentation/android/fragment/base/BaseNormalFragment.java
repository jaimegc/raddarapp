package com.raddarapp.presentation.android.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.karumi.rosie.view.RosieFragment;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.general.presenter.base.contract.BasePresenterErrorsContract;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class BaseNormalFragment extends RosieFragment implements BasePresenterErrorsContract {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int CODE_GPS_DISABLED = 1;

    @Inject
    protected Context context;
    private Snackbar snackbarError;

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void showConnectionError() {
        initSnackbarError(R.string.error_connection);
    }

    @Override
    public void showServerConnectionError() {
        initSnackbarError(R.string.error_server_connection);
    }

    @Override
    public void showGenericError() {
        initSnackbarError(R.string.error_generic);
    }

    @Override
    public void showGpsDisabledError() {
        initSnackbarError(R.string.error_gps_disabled, CODE_GPS_DISABLED);
    }

    @Override
    public void showGpsPermissionError() {
        initSnackbarError(R.string.error_gps_permission);
    }

    @Override
    public void showRecordAudioPermissionError() {
        initSnackbarError(R.string.error_record_audio_permission);
    }

    @Override
    public void showStoragePermissionPromoCodeError() {
        initSnackbarLongError(R.string.error_storage_promo_code_permission);
    }

    @Override
    public void showStoragePermissionInviteMyFriendsError() {
        initSnackbarLongError(R.string.error_storage_invite_my_friends_permission);
    }

    @Override
    public void showStoragePermissionError() {
        initSnackbarError(R.string.error_storage_permission);
    }

    @Override
    public void showSpeedError() {
        initSnackbarError(R.string.error_speed);
    }

    @Override
    public void showLocationError() {
        initSnackbarError(R.string.error_location);
    }

    @Override
    public void showLocationMockError() {
        initSnackbarLongError(R.string.error_location_mock);
    }

    @Override
    public void showUploadImageProfileError() {
        initSnackbarError(R.string.error_upload_image_profile);
    }

    @Override
    public void showUploadAudioProfileError() {
        initSnackbarError(R.string.error_upload_audio_profile);
    }

    @Override
    public void showUploadNotificationTopicError() {
        initSnackbarError(R.string.error_upload_notification_topic);
    }

    // E00
    @Override
    public void showServerUnauthorizedApiError() {
        initSnackbarError(R.string.error_server_unauthorized_api);
    }

    // E01
    @Override
    public void showServerRepeatedUserApiError() {
        initSnackbarError(R.string.error_server_repeated_user_api);
    }

    // E02
    @Override
    public void showServerRepeatedEmailApiError() {
        initSnackbarError(R.string.error_server_repeated_email_api);
    }

    // E03
    @Override
    public void showServerBannedUserApiError() {
        initSnackbarError(R.string.error_server_banned_user_api);
    }

    // E04
    @Override
    public void showServerAccessDataApiError() {
        initSnackbarError(R.string.error_server_access_data_api);
    }

    // E05
    @Override
    public void showServerUserNotExistApiError() {
        initSnackbarError(R.string.error_server_user_not_exist_api);
    }

    // E10
    @Override
    public void showServerUploadFileApiError() {
        initSnackbarError(R.string.error_server_upload_file_api);
    }

    // E15
    @Override
    public void showServerExpiredPromoCodeApiError() {
        initSnackbarError(R.string.error_server_promo_code_expired_api);
    }

    // E16
    @Override
    public void showServerIncorrectPromoCodeApiError() {
        initSnackbarError(R.string.error_server_promo_code_incorrect_api);
    }

    // E17
    @Override
    public void showServerAlreadyExchangedPromoCodeApiError() {
        initSnackbarError(R.string.error_server_promo_code_already_exchanged_api);
    }

    @Override
    public void showServerUsernamePatternApiError() {
        initSnackbarError(R.string.error_server_username_pattern_api);
    }

    // E27
    @Override
    public void showServerInvalidPromoCodeApiError() {
        initSnackbarError(R.string.error_server_promo_code_invalid_api);
    }

    // E99
    @Override
    public void showUnknownError() {
        initSnackbarError(R.string.error_server_unknown);
    }

    @Override
    public void showSilentError() {
        // TODO: Silent error. Use Analytics
    }

    protected void initSnackbarError(int messageResource) {

        if (getView() != null && getActivity() != null) {
            FontUtils fontUtils = new FontUtils();
            snackbarError = Snackbar.make(getView(), getString(messageResource), Snackbar.LENGTH_SHORT);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            message.setTextColor(Color.RED);
            snackbarError.show();
        }

    }

    protected void initSnackbar(int messageResource) {

        if (getView() != null && getActivity() != null) {
            FontUtils fontUtils = new FontUtils();
            snackbarError = Snackbar.make(getView(), getString(messageResource), Snackbar.LENGTH_SHORT);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            message.setTextColor(Color.BLACK);
            snackbarError.show();
        }

    }

    protected void initSnackbarLong(int messageResource) {

        if (getView() != null && getActivity() != null) {
            FontUtils fontUtils = new FontUtils();
            snackbarError = Snackbar.make(getView(), getString(messageResource), Snackbar.LENGTH_LONG);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            message.setTextColor(Color.BLACK);
            snackbarError.show();
        }

    }

    protected void initSnackbarLongError(int messageResource) {

        if (getView() != null && getActivity() != null) {
            FontUtils fontUtils = new FontUtils();
            snackbarError = Snackbar.make(getView(), getString(messageResource), Snackbar.LENGTH_LONG);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            message.setTextColor(Color.RED);
            snackbarError.show();
        }

    }

    private void initSnackbarError(int messageResource, int code) {
        if (getView() != null && getActivity() != null) {
            FontUtils fontUtils = new FontUtils();
            snackbarError = Snackbar.make(getView(), getString(messageResource), Snackbar.LENGTH_LONG);
            View snackBarView = snackbarError.getView();
            snackBarView.setBackgroundColor(Color.WHITE);
            TextView message = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
            fontUtils.applyFont(getActivity(), FONT_NAME, message);
            message.setTextColor(Color.RED);

            switch(code) {
                case CODE_GPS_DISABLED:
                    snackBarView.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)));
                    break;
            }

            snackbarError.show();
        }
    }
}
