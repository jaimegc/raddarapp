package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.model.UpdateCompliment;
import com.raddarapp.domain.model.builder.UpdateComplimentBuilder;
import com.raddarapp.domain.usecase.Login;
import com.raddarapp.domain.usecase.RemoveRepositoryCacheLogout;
import com.raddarapp.domain.usecase.UpdateComplimentProfile;

import javax.inject.Inject;

public class LogoutPresenter extends RosiePresenter<LogoutPresenter.View> {

    private final Login login;
    private final RemoveRepositoryCacheLogout removeRepositoryCacheLogout;
    private final UpdateComplimentProfile updateComplimentProfile;

    @Inject
    public LogoutPresenter(UseCaseHandler useCaseHandler, Login login, RemoveRepositoryCacheLogout removeRepositoryCacheLogout,
            UpdateComplimentProfile updateComplimentProfile) {
        super(useCaseHandler);
        this.login = login;
        this.removeRepositoryCacheLogout = removeRepositoryCacheLogout;
        this.updateComplimentProfile = updateComplimentProfile;
    }

    public void logout() {
        createUseCaseCall(removeRepositoryCacheLogout)
                .useCaseName(RemoveRepositoryCacheLogout.USE_CASE_REMOVE_REPOSITORY_CACHE_LOGOUT)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void removeRepositoryCacheLogout(boolean isRepositoryCacheRemoved) {}

                })
                .onError(error -> false).execute();

        createUseCaseCall(login)
                .useCaseName(Login.USE_CASE_LOGIN_LOGOUT)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void logout(boolean isLogout) {
                        if (isLogout) {
                            showGoLogin();
                        }
                    }

                })
                .onError(error -> false).execute();
    }

    public void updateMyCompliments(String myUserKey, long compliments) {

        UpdateCompliment updateCompliment = new UpdateComplimentBuilder()
                .withUserKey(myUserKey)
                .withCompliments(compliments)
                .build();

        createUseCaseCall(updateComplimentProfile)
                .args(updateCompliment)
                .useCaseName(UpdateComplimentProfile.USE_CASE_UPDATE_MY_COMPLIMENTS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updateMyCompliments(boolean isUpdatedCompliment) {}

                })
                .onError(error -> false).execute();
    }

    private void showGoLogin() {
        try {
            getView().goLogin();
        } catch (Exception e) {}
    }

    public interface View extends RosiePresenter.View {
        void goLogin();
    }
}
