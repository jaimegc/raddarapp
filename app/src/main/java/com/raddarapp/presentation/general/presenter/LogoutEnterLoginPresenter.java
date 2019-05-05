package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.usecase.Login;

import javax.inject.Inject;

public class LogoutEnterLoginPresenter extends RosiePresenter<LogoutEnterLoginPresenter.View> {

    private final Login login;

    @Inject
    public LogoutEnterLoginPresenter(UseCaseHandler useCaseHandler, Login login) {
        super(useCaseHandler);
        this.login = login;
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

    public interface View extends RosiePresenter.View {}
}
