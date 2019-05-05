package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.MyUserProfileRepository;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.MyUserPassword;
import com.raddarapp.domain.model.MyUserProfile;

import javax.inject.Inject;

public class Login extends RosieUseCase {

    public static final String USE_CASE_LOGIN_FACEBOOK = "loginFacebook";
    public static final String USE_CASE_LOGIN_GOOGLE = "loginGoogle";
    public static final String USE_CASE_LOGIN = "coinMining";
    public static final String USE_CASE_LOGIN_LOGOUT = "logout";
    public static final String USE_CASE_LOGIN_IS_LOGGED_IN = "isLoggedIn";

    private MyUserProfileRepository userProfileRepository;

    @Inject
    public Login(MyUserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @UseCase(name = USE_CASE_LOGIN_FACEBOOK)
    public void loginFacebook(MyUserLoginSocialToken userFacebookToken) throws Exception {
        MyUserProfile userProfile = userProfileRepository.loginFacebook(userFacebookToken);
        notifySuccess(userProfile);
    }

    @UseCase(name = USE_CASE_LOGIN_GOOGLE)
    public void loginGoogle(MyUserLoginSocialToken userGoogleToken) throws Exception {
        MyUserProfile userProfile = userProfileRepository.loginGoogle(userGoogleToken);
        notifySuccess(userProfile);
    }

    @UseCase(name = USE_CASE_LOGIN)
    public void login(MyUserPassword userPassword) throws Exception {
        MyUserProfile userProfile = userProfileRepository.login(userPassword);
        notifySuccess(userProfile);
    }

    @UseCase(name = USE_CASE_LOGIN_LOGOUT)
    public void logout() throws Exception {
        boolean isLogout = userProfileRepository.logout();
        notifySuccess(isLogout);
    }

    @UseCase(name = USE_CASE_LOGIN_IS_LOGGED_IN)
    public void isLoggedIn() throws Exception {
        boolean isLoggedIn = userProfileRepository.isLoggedIn();
        notifySuccess(isLoggedIn);
    }
}
