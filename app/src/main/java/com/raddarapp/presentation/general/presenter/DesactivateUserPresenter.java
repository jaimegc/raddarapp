package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.UserStatusType;
import com.raddarapp.domain.usecase.DesactivateUser;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;

import javax.inject.Inject;

public class DesactivateUserPresenter extends BasePresenterNormal<DesactivateUserPresenter.View> {

    private final DesactivateUser desactivateUser;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public DesactivateUserPresenter(UseCaseHandler useCaseHandler, DesactivateUser desactivateUser,
            MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        super(useCaseHandler);
        this.desactivateUser = desactivateUser;
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public void desactivateUser(String userKey) {
        createUseCaseCall(this.desactivateUser)
                .args(userKey)
                .useCaseName(desactivateUser.USE_CASE_DESACTIVATE_USER)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void desactivateUser(boolean desactivatedUser) {

                        if (desactivatedUser) {
                            userProfilePreferencesDataSource.setRole(UserRoleType.USER_INACTIVE.getValue());
                            userProfilePreferencesDataSource.setStatus(UserStatusType.INACTIVE.getValue());
                            getView().desactivateUserSuccess();
                        } else {
                            getView().showDesactivateUserError();
                        }
                    }
                })
                .onError(error -> {
                    getView().showDesactivateUserError();
                    return false;
                }).execute();
    }

    public interface View extends BasePresenterNormal.View {

        void desactivateUserSuccess();

        void showDesactivateUserError();
    }
}
