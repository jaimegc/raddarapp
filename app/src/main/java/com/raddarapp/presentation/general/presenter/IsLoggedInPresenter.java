package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.domain.usecase.Login;

import javax.inject.Inject;

public class IsLoggedInPresenter extends RosiePresenter<IsLoggedInPresenter.View> {

    private final Login login;

    @Inject
    public IsLoggedInPresenter(UseCaseHandler useCaseHandler, Login login) {
        super(useCaseHandler);
        this.login = login;
    }

    public void isLoggedIn() {
        createUseCaseCall(login)
                .useCaseName(Login.USE_CASE_LOGIN_IS_LOGGED_IN)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void isLoggedIn(boolean isLoggedIn) {
                        if (isLoggedIn) {
                            showGoMain();
                        }
                    }

                })
                .onError(error -> false).execute();
    }

    private void showGoMain() {
        try {
            getView().goMain();
        } catch (Exception e) {}
    }

    public interface View extends RosiePresenter.View {
        void goLogin();

        void goMain();
    }
}
