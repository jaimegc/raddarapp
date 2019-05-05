package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateNotificationTopicApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateNotificationTopicWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateNotificationToken;

import javax.inject.Inject;

public class UpdateNotificationTopicApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateNotificationToken>
        implements UpdateNotificationTopicWriteableDataSourceContract {

    private final UpdateNotificationTopicApiClient updateNotificationTopicApiClient;

    @Inject
    public UpdateNotificationTopicApiWriteableDataSource(UpdateNotificationTopicApiClient updateNotificationTopicApiClient) {
        this.updateNotificationTopicApiClient = updateNotificationTopicApiClient;
    }

    @Override
    public boolean updateNotificationTopic(String notificationTopicKey) throws Exception {
        boolean udpatedNotificationTopic;
        ResponseDto responseDto = null;

        responseDto = updateNotificationTopicApiClient.updateNotificationTopic(notificationTopicKey);

        udpatedNotificationTopic = responseDto == null || !responseDto.getResponse().isEmpty();

        return udpatedNotificationTopic;
    }

    @Override
    public boolean updateNotificationToken(String updateNotificationToken) throws Exception {
        boolean udpatedNotificationToken;
        ResponseDto responseDto = null;

        responseDto = updateNotificationTopicApiClient.updateNotificationToken(updateNotificationToken);

        udpatedNotificationToken = responseDto == null || !responseDto.getResponse().isEmpty();

        return udpatedNotificationToken;
    }
}