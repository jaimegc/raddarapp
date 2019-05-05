package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.UpdateNotificationRepository;

import javax.inject.Inject;

public class UpdateNotification extends RosieUseCase {

    public static final String USE_CASE_UPDATE_NOTIFICATION_TOPIC = "updateNotificationTopic";
    public static final String USE_CASE_UPDATE_NOTIFICATION_TOKEN = "updateNotificationToken";

    private final UpdateNotificationRepository updateNotificationTopicRepository;

    @Inject
    public UpdateNotification(UpdateNotificationRepository updateNotificationTopicRepository) {
        this.updateNotificationTopicRepository = updateNotificationTopicRepository;
    }

    @UseCase(name = USE_CASE_UPDATE_NOTIFICATION_TOPIC)
    public void updateNotificationTopic(String notificationTopicKey) throws Exception {
        boolean updatedNotificationTopic = updateNotificationTopicRepository.updateNotificationTopic(notificationTopicKey);
        notifySuccess(updatedNotificationTopic);
    }

    @UseCase(name = USE_CASE_UPDATE_NOTIFICATION_TOKEN)
    public void updateNotificationToken(String notificationToken) throws Exception {
        boolean updatedNotificationToken = updateNotificationTopicRepository.updateNotificationToken(notificationToken);
        notifySuccess(updatedNotificationToken);
    }
}
