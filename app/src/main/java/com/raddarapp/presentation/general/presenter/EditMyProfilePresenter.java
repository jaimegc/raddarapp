package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.domain.usecase.UpdateAudioProfile;
import com.raddarapp.domain.usecase.UpdateImageProfile;
import com.raddarapp.domain.usecase.UpdateNotification;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class EditMyProfilePresenter extends BasePresenterRefreshWithLoading<EditMyProfilePresenter.View> {

    private final GetMyUserProfile getUserProfile;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final UpdateImageProfile updateImageProfileUseCase;
    private final UpdateAudioProfile updateAudioProfileUseCase;
    private final UpdateNotification updateNotificationTopicUseCase;
    private MyUserProfile userProfile = null;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public EditMyProfilePresenter(UseCaseHandler useCaseHandler, MyUserProfileToUserProfileViewModelMapper mapperUserProfile,
            GetMyUserProfile getUserProfile, UpdateImageProfile updateImageProfileUseCase, UpdateAudioProfile updateAudioProfileUseCase,
            UpdateNotification updateNotificationTopicUseCase, MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        super(useCaseHandler);
        this.mapperUserProfile = mapperUserProfile;
        this.getUserProfile = getUserProfile;
        this.updateImageProfileUseCase = updateImageProfileUseCase;
        this.updateAudioProfileUseCase = updateAudioProfileUseCase;
        this.updateNotificationTopicUseCase = updateNotificationTopicUseCase;
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public void initialize() {
        super.initialize();
        loadUserProfile();
    }

    @Override
    public void forceRefreshing() {}

    private void loadUserProfile() {

        createUseCaseCall(getUserProfile)
                .useCaseName(getUserProfile.USE_CASE_GET_USER_PROFILE_PREFERENCES)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getUserProfilePreferences(MyUserProfile userProfilePreferences) {
                        userProfile = userProfilePreferences;
                        showUserProfilePreferences(userProfile);
                    }
                })
                .onError(error -> {
                    showError();

                    return false;
                }).execute();
    }

    private void showUserProfilePreferences(MyUserProfile userProfilePreferences) {
        MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);

        try {
            getView().showUserProfilePreferences(myUserProfileViewModel);
        } catch (Exception e) {}
    }

    public void updateImageProfile(String imageUri) {
        createUseCaseCall(updateImageProfileUseCase)
                .args(imageUri)
                .useCaseName(updateImageProfileUseCase.USE_CASE_UPDATE_IMAGE_PROFILE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateImageProfile(com.raddarapp.domain.model.UpdateImageProfile updatedImageProfile) {
                        if (updatedImageProfile.getMediaName() == null || updatedImageProfile.getMediaName().isEmpty()) {
                            showUploadImageProfileError();
                        }
                    }
                })
                .onError(error -> {
                    showUploadImageProfileError();

                    return false;
                }).execute();
    }

    public void updateAudioProfile(String audioUri) {
        createUseCaseCall(updateAudioProfileUseCase)
                .args(audioUri)
                .useCaseName(updateAudioProfileUseCase.USE_CASE_UPDATE_AUDIO_PROFILE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateAudioProfile(com.raddarapp.domain.model.UpdateAudioProfile updatedAudioProfile) {
                        if (updatedAudioProfile.getMediaName() == null || updatedAudioProfile.getMediaName().isEmpty()) {
                            showUploadAudioProfileError();
                        }
                    }
                })
                .onError(error -> {
                    showUploadAudioProfileError();

                    return false;
                }).execute();
    }

    public void updateNotificationTopic(String notificationTopicKey) {
        createUseCaseCall(updateNotificationTopicUseCase)
                .args(notificationTopicKey)
                .useCaseName(updateNotificationTopicUseCase.USE_CASE_UPDATE_NOTIFICATION_TOPIC)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateNotificationTopic(boolean updatedNotificationTopic) { }
                })
                .onError(error -> {
                    showUploadNotificationTopicError();

                    return false;
                }).execute();
    }

    private void showError() {
        hideLoading();
    }

    private void showUploadImageProfileError() {
        try {
            hideLoading();
            getView().showUploadImageProfileError();
        } catch (Exception e) {
            userProfilePreferencesDataSource.setImage("");
            userProfilePreferencesDataSource.setImageCache("");
        }
    }

    private void showUploadAudioProfileError() {
        try {
            hideLoading();
            getView().showUploadAudioProfileError();
        } catch (Exception e) {
            userProfilePreferencesDataSource.setAudio("");
            userProfilePreferencesDataSource.setAudioCache("");
        }
    }

    private void showUploadNotificationTopicError() {
        try {
            hideLoading();
            getView().showUploadNotificationTopicError();
        } catch (Exception e) { }
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel);

        void showUploadImageProfileError();

        void showUploadAudioProfileError();

        void showUploadNotificationTopicError();
    }
}
