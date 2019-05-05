package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.domain.usecase.error.OnErrorCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.usecase.AddMyFootprintInCache;

import javax.inject.Inject;

public class MyFootprintDetailsNotificationsPresenter extends RosiePresenter<MyFootprintDetailsNotificationsPresenter.View> {

    private final AddMyFootprintInCache addMyFootprintInCache;
    private MyFootprint myFootprint;
    private Long comments = null;
    private String notificationContent;

    @Inject
    MyFootprintsRepository myFootprintsRepository;

    @Inject
    public MyFootprintDetailsNotificationsPresenter(UseCaseHandler useCaseHandler, AddMyFootprintInCache addMyFootprintInCache) {
        super(useCaseHandler);

        this.addMyFootprintInCache = addMyFootprintInCache;
    }

    public void handleNotifications(String notificationTopic, String notificationContent) {
        executeNotification(notificationTopic, notificationContent);
    }

    private void executeNotification(String notificationTopic, String notificationContent) {
        loadMyFootprintDetailsForeground(notificationContent);
    }

    private void loadMyFootprintDetailsForeground(String notificationContent) {
        createUseCaseCall(addMyFootprintInCache)
                .args(notificationContent)
                .useCaseName(AddMyFootprintInCache.USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION_FOREGROUND)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintDetailsFromNotificationForeground(MyFootprint myFootprint) {
                        MyFootprintDetailsNotificationsPresenter.this.myFootprint = myFootprint;
                        showMyFootprintDetails(myFootprint, false);
                    }
                })
                .onError(error -> {
                    // FIXME: This is because when we receive a notification in foreground but we close the app
                    // withoutopening the notification, if we tap on notification for Android is en foreground
                    // but this method is for background
                    loadMyFootprintDetails(notificationContent);
                    return false;
                })
                .execute();
    }

    private void loadMyFootprintDetails(String notificationContent) {
        createUseCaseCall(addMyFootprintInCache)
                .args(notificationContent)
                .useCaseName(AddMyFootprintInCache.USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintDetailsFromNotification(MyFootprint myFootprint) {
                        MyFootprintDetailsNotificationsPresenter.this.myFootprint = myFootprint;
                        showMyFootprintDetails(myFootprint, true);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showMyFootprintDetails(MyFootprint myFootprint, boolean openSplashScreen) {
        try {
            getView().initializeFragmentFromNotification(myFootprint.getKey(), myFootprint.getComments(), openSplashScreen);
        } catch (Exception e) { }
    }

    public void updateComments()  {
        myFootprint.updateComments();
    }

    public void updateComments(long total)  {
        myFootprint.updateComments(total);
        comments = total;
    }

    public interface View extends RosiePresenter.View {

        void initializeFragmentFromNotification(String myFootprintKey, long comments, boolean openSplashScreen);
    }
}
