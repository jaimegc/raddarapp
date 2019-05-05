package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.UpdateMyUserProfileBuilder;
import com.raddarapp.domain.usecase.GetMyUserProfile;
import com.raddarapp.domain.usecase.UpdateMyUserProfile;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.validation.ValidationEnterCompleteProfile;
import com.raddarapp.presentation.general.validation.view.ValidationEnterCompleteProfileView;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class EnterCompleteProfilePresenter extends BasePresenterRefreshWithLoading<EnterCompleteProfilePresenter.View> {

    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final ValidationEnterCompleteProfile validationEnterCompleteProfile;
    private MyUserProfile userProfile = null;
    private final GetMyUserProfile getUserProfile;
    private final com.raddarapp.domain.usecase.UpdateMyUserProfile updateMyUserProfileUseCase;
    private boolean isCompletedProfile = false;

    @Inject
    public EnterCompleteProfilePresenter(UseCaseHandler useCaseHandler, MyUserProfileToUserProfileViewModelMapper mapperUserProfile,
            ValidationEnterCompleteProfile validationEnterCompleteProfile, GetMyUserProfile getUserProfile,
            com.raddarapp.domain.usecase.UpdateMyUserProfile updateMyUserProfileUseCase) {
        super(useCaseHandler);
        this.mapperUserProfile = mapperUserProfile;
        this.validationEnterCompleteProfile = validationEnterCompleteProfile;
        this.getUserProfile = getUserProfile;
        this.updateMyUserProfileUseCase = updateMyUserProfileUseCase;
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();

        if (isCompletedProfile) {
            showUpdatedMyUserProfile();
        }
    }

    private void loadUserProfile() {

        if (userProfile != null) {
            showMyUserProfile(userProfile);
        } else {
            createUseCaseCall(getUserProfile)
                    .useCaseName(getUserProfile.USE_CASE_GET_USER_PROFILE_PREFERENCES)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void getUserProfilePreferences(MyUserProfile userProfilePreferences) {
                            userProfile = userProfilePreferences;
                            showMyUserProfile(userProfile);
                        }
                    })
                    .onError(error -> {
                        showError();

                        return false;
                    }).execute();
        }
    }

    public void completeProfile(String username, int gender, String birthdate, boolean termsConditions) {
        ErrorLocalCode code = validationEnterCompleteProfile.validateCompleteProfile(username, birthdate, termsConditions);

        if (code == ErrorLocalCode.SUCCESS) {
            com.raddarapp.domain.model.UpdateMyUserProfile updateMyUserProfile = new UpdateMyUserProfileBuilder()
                    .withUsername(!username.equals(userProfile.getUsername()) ? username : null)
                    .withGender(gender)
                    .withBirthdate(birthdate)
                    .build();

            updateMyUserProfile(updateMyUserProfile);
        } else {
            showErrorLocal(code);
        }
    }

    public void completeProfileWithEmail(String username, String email, int gender, String birthdate, boolean termsConditions) {
        ErrorLocalCode code = validationEnterCompleteProfile.validateCompleteProfileWithEmail(username, email, birthdate, termsConditions);

        if (code == ErrorLocalCode.SUCCESS) {
            com.raddarapp.domain.model.UpdateMyUserProfile updateMyUserProfile = new UpdateMyUserProfileBuilder()
                    .withUsername(!username.equals(userProfile.getUsername()) ? username : null)
                    .withGender(gender)
                    .withBirthdate(birthdate)
                    .withEmail(!email.equals(userProfile.getEmail()) ? email : null)
                    .build();

            updateMyUserProfile(updateMyUserProfile);
        } else {
            showErrorLocal(code);
        }
    }

    private void showMyUserProfile(MyUserProfile userProfilePreferences) {
        MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);

        try {
            getView().hideLoading();
            getView().showMyUserProfile(myUserProfileViewModel);

        } catch (Exception e) {}
    }

    private void updateMyUserProfile(com.raddarapp.domain.model.UpdateMyUserProfile updateMyUserProfile) {

        try {
            getView().showLoading();
        } catch (Exception e) {}

        createUseCaseCall(updateMyUserProfileUseCase)
                .args(updateMyUserProfile)
                .useCaseName(UpdateMyUserProfile.USE_CASE_UPDATE_MY_USER_PROFILE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateMyUserProfile(MyUserProfile myUserProfileUpdated) {
                        if (myUserProfileUpdated != null) {
                            isCompletedProfile = true;
                            showUpdatedMyUserProfile();
                        } else {
                            showError();
                        }
                    }
                })
                .onError(error -> {
                    try {
                        getView().hideLoading();
                    } catch (Exception e) {}
                    return false;
                }).execute();
    }

    private void showUpdatedMyUserProfile() {
        try {
            getView().showCompletedProfile();
        } catch (Exception e) {}
    }

    private void showErrorLocal(ErrorLocalCode code) {
        try {
            switch (code) {
                case TERMS_CONDITIONS:
                    getView().showErrorLocalTermsConditions();
                    break;
                case EMPTY_EMAIL:
                    getView().showErrorLocalEmptyEmail();
                    break;
                case EMAIL_PATTERN:
                    getView().showErrorLocalEmailPattern();
                    break;
                case EMPTY_USERNAME:
                    getView().showErrorLocalEmptyUsername();
                    break;
                case EMPTY_BIRTHDATE:
                    getView().showErrorLocalEmptyBirthdate();
                    break;
                case BIRTHDATE_PATTERN:
                    getView().showErrorLocalBirthdatePattern();
                    break;
                case UNDER_FOURTEEN:
                    getView().showErrorLocalUnderFourteen();
                    break;
                case MAXIMUM_AGE:
                    getView().showErrorLocalMaximumAge();
                    break;
                case AGE_FUTURE:
                    getView().showErrorLocalAgeFuture();
                    break;
                case USERNAME_PATTERN:
                    getView().showErrorLocalUsernamePattern();
                    break;

            }

            getView().hideLoading();
        } catch (Exception e) {}
    }

    private void showError() {
        try {
            getView().hideLoading();
            getView().showCompletedProfileError();
        } catch (Exception e) {}
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View, ValidationEnterCompleteProfileView {
        void showMyUserProfile(MyUserProfileViewModel userProfileViewModel);

        void showCompletedProfileError();

        void showCompletedProfile();
    }
}
