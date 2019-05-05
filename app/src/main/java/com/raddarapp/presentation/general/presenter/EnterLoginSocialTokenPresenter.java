package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.builder.MyUserLoginSocialTokenBuilder;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.domain.usecase.Login;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.MyUserProfileToUserProfileViewModelMapper;

import javax.inject.Inject;

public class EnterLoginSocialTokenPresenter extends BasePresenterRefreshWithLoading<EnterLoginSocialTokenPresenter.View> {

    private static final int INDEX_FACEBOOK_LOGIN = 0;
    private static final int INDEX_GOOGLE_LOGIN = 1;
    private final Login login;
    private final MyUserProfileToUserProfileViewModelMapper mapperUserProfile;
    private String loginToken;
    private MyUserProfile userProfilePreferences = null;
    private int indexLogin;

    @Inject
    public EnterLoginSocialTokenPresenter(UseCaseHandler useCaseHandler, Login login,
            MyUserProfileToUserProfileViewModelMapper mapperUserProfile) {
        super(useCaseHandler);
        this.login = login;
        this.mapperUserProfile = mapperUserProfile;
    }

    @Override
    public void update() {
        super.update();
        loadUserProfile();
    }

    @Override
    public void initialize() {
        super.initialize();

        if (indexLogin == INDEX_FACEBOOK_LOGIN) {
            loginFacebook(loginToken);
        } else if (indexLogin == INDEX_GOOGLE_LOGIN) {
            loginGoogle(loginToken);
        }
    }

    // If we minimized the app while the login is in process
    private void loadUserProfile() {
        if (userProfilePreferences != null) {
            showLoginSuccessful(userProfilePreferences);
        }
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public void setIndexLogin(int indexLogin) {
        this.indexLogin = indexLogin;
    }

    public void loginFacebookRetry() {
        loginFacebook(loginToken);
    }

    public void loginGoogleRetry() {
        loginGoogle(loginToken);
    }

    private void loginFacebook(String token) {
        showLoading();

        MyUserLoginSocialToken userFacebookToken = new MyUserLoginSocialTokenBuilder()
                .withToken(token)
                .build();

        createUseCaseCall(login)
                .args(userFacebookToken)
                .useCaseName(Login.USE_CASE_LOGIN_FACEBOOK)
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
    }

    private void loginGoogle(String token) {
        showLoading();

        MyUserLoginSocialToken userGoogleToken = new MyUserLoginSocialTokenBuilder()
                .withToken(token)
                .build();

        createUseCaseCall(login)
                .args(userGoogleToken)
                .useCaseName(Login.USE_CASE_LOGIN_GOOGLE)
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
            getView().loginSuccessful(myUserProfileViewModel, userProfilePreferences.getGender() != UserGenderType.OTHER.getValue());
        } catch (Exception e) {}
    }

    private void showError() {
        hideLoading();
        getView().showLoginError();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void loginSuccessful(MyUserProfileViewModel userProfileViewModel, boolean isComplete);

        void showLoginError();
    }
}
