package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.raddarapp.domain.usecase.UpdateNotification;
import com.raddarapp.presentation.general.presenter.base.BasePresenterNormal;

import javax.inject.Inject;

public class UpdateNotificationTokenPresenter extends BasePresenterNormal<UpdateNotificationTokenPresenter.View> {

    private final UpdateNotification updateNotification;

    @Inject
    public UpdateNotificationTokenPresenter(UseCaseHandler useCaseHandler, UpdateNotification updateNotification) {
        super(useCaseHandler);
        this.updateNotification = updateNotification;
    }

    public void updateNotificationToken(String notificationToken) {
        createUseCaseCall(this.updateNotification)
                .args(notificationToken)
                .useCaseName(updateNotification.USE_CASE_UPDATE_NOTIFICATION_TOKEN)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void updatedNotificationToken(boolean updatedNotificationToken) { }
                })
                .onError(error -> {
                    return false;
                }).execute();
    }

    public interface View extends BasePresenterNormal.View { }
}
