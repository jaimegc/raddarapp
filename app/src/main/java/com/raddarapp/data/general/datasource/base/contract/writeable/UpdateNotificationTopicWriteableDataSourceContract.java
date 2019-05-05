package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdateNotificationToken;

public interface UpdateNotificationTopicWriteableDataSourceContract extends WriteableDataSource<String, UpdateNotificationToken> {

    boolean updateNotificationTopic(String updateNotificationKey) throws Exception;

    boolean updateNotificationToken(String updateNotificationToken) throws Exception;
}
