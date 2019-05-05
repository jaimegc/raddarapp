package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.MyUserPassword;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.MyUserPasswordBuilder;
import com.raddarapp.domain.usecase.Login;
import com.raddarapp.presentation.android.error.local.ErrorLocalCode;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.validation.ValidationEnterLogin;
import com.raddarapp.presentation.general.validation.view.ValidationEnterLoginView;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class EnterLoginPresenter extends BasePresenterRefreshWithLoading<EnterLoginPresenter.View> {

    private final Login login;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private final ValidationEnterLogin validationEnterLogin;
    private MyUserProfile userProfilePreferences = null;

    @Inject
    public EnterLoginPresenter(UseCaseHandler useCaseHandler, Login login, MyUserProfileToUserProfileViewModelMapper mapperUserProfile,
            ValidationEnterLogin validationEnterLogin) {
        super(useCaseHandler);
        this.login = login;
        this.mapperUserProfile = mapperUserProfile;
        this.validationEnterLogin = validationEnterLogin;
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();
    }

    // If we minimized the app while the login is in process
    private void loadUserProfile() {
        if (userProfilePreferences != null) {
            showLoginSuccessful(userProfilePreferences);
        }
    }

    public void login(String user, String password) {
        showLoading();
        getView().disableLoginButton();

        ErrorLocalCode code = validationEnterLogin.validateEnterLogin(user, password);

        if (code == ErrorLocalCode.SUCCESS) {
            MyUserPassword userPassword = new MyUserPasswordBuilder()
                    .withUser(user)
                    .withPassword(password)
                    .build();

            createUseCaseCall(login)
                    .args(userPassword)
                    .useCaseName(Login.USE_CASE_LOGIN)
                    .onSuccess(new OnSuccessCallback() {
                        @Success
                        public void login(MyUserProfile userProfile) {
                            userProfilePreferences = userProfile;
                            showLoginSuccessful(userProfile);
                        }

                    })
                    .onError(error -> {
                        showError();
                        return false;
                    }).execute();
        } else {
            showErrorLocal(code);
        }

    }

    public void logout() {
        createUseCaseCall(login)
                .useCaseName(Login.USE_CASE_LOGIN_LOGOUT)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void logout(boolean isLogout) {
                        // No implemented
                    }

                })
                .onError(error -> false).execute();
    }

    private void showLoginSuccessful(MyUserProfile userProfilePreferences) {
        MyUserProfileViewModel myUserProfileViewModel = mapperUserProfile.map(userProfilePreferences);

        try {
            hideLoading();
            getView().loginSuccessful(myUserProfileViewModel);
        } catch (Exception e) {}
    }

    private void showError() {
        hideLoading();
        getView().enableLoginButton();
    }

    private void showErrorLocal(ErrorLocalCode code) {
        try {

            switch (code) {
                case EMPTY_USERNAME:
                    getView().showErrorLocalUsernameEmpty();
                    break;

                case EMPTY_PASSWORD:
                    getView().showErrorLocalPasswordEmpty();
                    break;
            }

            hideLoading();
            getView().enableLoginButton();
        } catch (Exception e) {}
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View, ValidationEnterLoginView {
        void loginSuccessful(MyUserProfileViewModel userProfileViewModel);

        void disableLoginButton();

        void enableLoginButton();
    }
}
