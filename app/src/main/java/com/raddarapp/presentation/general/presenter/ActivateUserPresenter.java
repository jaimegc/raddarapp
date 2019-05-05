package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.UserStatusType;
import com.raddarapp.domain.usecase.ActivateUser;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;

import javax.inject.Inject;

public class ActivateUserPresenter extends BasePresenterNormal<ActivateUserPresenter.View> {

    private final ActivateUser activateUser;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public ActivateUserPresenter(UseCaseHandler useCaseHandler, ActivateUser activateUser,
            MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        super(useCaseHandler);
        this.activateUser = activateUser;
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public void activateUser(String userKey) {
        createUseCaseCall(this.activateUser)
                .args(userKey)
                .useCaseName(activateUser.USE_CASE_ACTIVATE_USER)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void activateUser(boolean activatedUser) {

                        if (activatedUser) {
                            userProfilePreferencesDataSource.setRole(UserRoleType.USER.getValue());
                            userProfilePreferencesDataSource.setStatus(UserStatusType.ACTIVE.getValue());
                            getView().activateUserSuccess();
                        } else {
                            getView().showActivateUserError();
                        }
                    }
                })
                .onError(error -> {
                    getView().showActivateUserError();
                    return false;
                }).execute();
    }

    public interface View extends BasePresenterNormal.View {

        void activateUserSuccess();

        void showActivateUserError();
    }
}
