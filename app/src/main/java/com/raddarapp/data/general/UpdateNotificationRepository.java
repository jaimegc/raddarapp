package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateNotificationTopicWriteableDataSourceContract;
import com.raddarapp.data.general.factory.UpdateNotificationTopicDataSourceFactory;
import com.raddarapp.domain.model.UpdateNotificationToken;

import javax.inject.Inject;

public class UpdateNotificationRepository extends RosieRepository<String, UpdateNotificationToken> {

    private final UpdateNotificationTopicWriteableDataSourceContract updateNotificationTopicWriteableDataSource;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    UpdateNotificationRepository(UpdateNotificationTopicDataSourceFactory updateNotificationTopicDataSourceFactory) {

        updateNotificationTopicWriteableDataSource = updateNotificationTopicDataSourceFactory.createWriteableDataSource();
        userProfilePreferencesDataSource = updateNotificationTopicDataSourceFactory.createPreferencesDataSource();

        addWriteableDataSources(updateNotificationTopicWriteableDataSource);
    }

    public boolean updateNotificationTopic(String notificationTopicKey) throws Exception {
        boolean updatedNotificationTopic;

        updatedNotificationTopic = updateNotificationTopicWriteableDataSource.updateNotificationTopic(notificationTopicKey);

        return updatedNotificationTopic;
    }

    public boolean updateNotificationToken(String notificationToken) throws Exception {
        boolean updatedNotificationToken;

        updatedNotificationToken = updateNotificationTopicWriteableDataSource.updateNotificationToken(notificationToken);

        return updatedNotificationToken;
    }
}
