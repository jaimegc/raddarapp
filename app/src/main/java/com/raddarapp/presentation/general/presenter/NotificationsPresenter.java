package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.view.RosiePresenter;
import com.raddarapp.data.general.MyFootprintsRepository;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.usecase.AddMyFootprintInCache;

import javax.inject.Inject;

public class NotificationsPresenter extends RosiePresenter<NotificationsPresenter.View> {

    private static final String NOTIFICATION_TOPIC_URL = "url";
    private final AddMyFootprintInCache addMyFootprintInCache;
    private MyFootprint myFootprint;
    private Long comments = null;
    private String notificationContent;

    @Inject
    MyFootprintsRepository myFootprintsRepository;

    @Inject
    public NotificationsPresenter(UseCaseHandler useCaseHandler, AddMyFootprintInCache addMyFootprintInCache) {
        super(useCaseHandler);

        this.addMyFootprintInCache = addMyFootprintInCache;
    }

    public void handleNotifications(String notificationTopic, String notificationContent) {
        if (notificationTopic != null && notificationContent != null) {
            if (!notificationTopic.equals(NOTIFICATION_TOPIC_URL)) {
                executeNotification(notificationTopic, notificationContent);
            } else {
                // This crash :[
                try {
                    getView().openWebFromNotification(notificationContent);
                } catch (Exception e) {}
            }
        }
    }

    private void executeNotification(String notificationTopic, String notificationContent) {
        loadMyFootprintDetails(notificationContent);
    }

    private void loadMyFootprintDetails(String notificationContent) {
        createUseCaseCall(addMyFootprintInCache)
                .args(notificationContent)
                .useCaseName(AddMyFootprintInCache.USE_CASE_ADD_MY_FOOTPRINT_IN_CACHE_FROM_NOTIFICATION)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintDetailsFromNotification(MyFootprint myFootprint) {
                        NotificationsPresenter.this.myFootprint = myFootprint;

                        showMyFootprintDetails(myFootprint);
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void showMyFootprintDetails(MyFootprint myFootprint) {
        try {
            getView().openMyFootprintFromNotification(myFootprint.getKey(), myFootprint.getComments());
        } catch (Exception e) {}
    }

    public void updateComments()  {
        myFootprint.updateComments();
    }

    public void updateComments(long total)  {
        myFootprint.updateComments(total);
        comments = total;
    }

    public interface View extends RosiePresenter.View {

        void openMyFootprintFromNotification(String myFootprintKey, long comments);

        void openWebFromNotification(String notificationContent);
    }
}
