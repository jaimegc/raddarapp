package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.UpdatePasswordProfile;
import com.raddarapp.domain.model.builder.UpdatePasswordProfileBuilder;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;
import com.raddarapp.presentation.general.validation.ValidationUpdatePasswordProfile;
import com.raddarapp.presentation.general.validation.view.ValidationUpdatePasswordProfileView;

import javax.inject.Inject;

public class UpdatePasswordProfilePresenter extends BasePresenterNormal<UpdatePasswordProfilePresenter.View> {

    private final com.raddarapp.domain.usecase.UpdatePasswordProfile updatePasswordProfile;
    private final ValidationUpdatePasswordProfile validationUpdatePasswordProfile;

    @Inject
    public UpdatePasswordProfilePresenter(UseCaseHandler useCaseHandler,
            com.raddarapp.domain.usecase.UpdatePasswordProfile updatePasswordProfile,
            ValidationUpdatePasswordProfile validationUpdatePasswordProfile) {
        super(useCaseHandler);
        this.updatePasswordProfile = updatePasswordProfile;
        this.validationUpdatePasswordProfile = validationUpdatePasswordProfile;
    }

    private void updatePasswordProfile(UpdatePasswordProfile updatePasswordProfile) {
        getView().showUpdatePasswordProfileLoading();

        createUseCaseCall(this.updatePasswordProfile)
                .args(updatePasswordProfile)
                .useCaseName(com.raddarapp.domain.usecase.UpdatePasswordProfile.USE_CASE_UPDATE_PASSWORD_PROFILE)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updatePasswordProfile(boolean updatedPasswordProfile) {
                        if (updatedPasswordProfile) {
                            showUpdatedPasswordProfile();
                        } else {
                            showError();
                        }
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    private void showUpdatedPasswordProfile() {
        getView().showUpdatedPasswordProfile();
        getView().hideUpdatePasswordProfileLoading();
    }

    public void onUpdatePasswordProfileClicked(String newPassword, String newPasswordRepeated, String oldPassword) {
        getView().showUpdatePasswordProfileLoading();

        if (isValidPassword(newPassword, newPasswordRepeated,oldPassword)) {
            UpdatePasswordProfile updatePasswordProfile = new UpdatePasswordProfileBuilder()
                    .withPassword(newPassword)
                    .withOldPassword(oldPassword)
                    .build();

            updatePasswordProfile(updatePasswordProfile);
        }
    }

    public boolean isValidPassword(String newPassword, String newPasswordRepeated, String oldPassword) {
        ErrorLocalCode code = validationUpdatePasswordProfile.validateUpdatePasswordProfile(newPassword, newPasswordRepeated, oldPassword);

        if (code == ErrorLocalCode.SUCCESS) {
            return true;
        } else {
            showErrorLocal(code);
            return false;
        }
    }

    private void showError() {
        getView().hideUpdatePasswordProfileLoading();
        getView().showUpdatePasswordProfileError();
    }

    private void showErrorLocal(ErrorLocalCode code) {
        switch (code) {
            case FILL_ALL_PASSWORDS:
                getView().showErrorLocalFillAllPasswords();
                break;
            case ALL_EQUALS_PASSWORDS:
                getView().showErrorLocalAllEqualsPasswords();
                break;
            case NEW_DIFFERENT_PASSWORDS:
                getView().showErrorLocalNewDifferentPasswords();
                break;
        }

        getView().hideUpdatePasswordProfileLoading();
    }

    public interface View extends BasePresenterNormal.View, ValidationUpdatePasswordProfileView {

        void showUpdatedPasswordProfile();

        void showUpdatePasswordProfileError();

        void showUpdatePasswordProfileLoading();

        void hideUpdatePasswordProfileLoading();
    }
}
