package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateNotificationTopicWriteableDataSourceContract;
import com.raddarapp.domain.model.UpdateNotificationToken;

import javax.inject.Inject;

public class UpdateNotificationTopicFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateNotificationToken>
        implements UpdateNotificationTopicWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public UpdateNotificationTopicFakeWriteableDataSource() {
    }

    @Override
    public boolean updateNotificationTopic(String notificationTopicKey) throws Exception {
        fakeDelay();

        return true;
    }

    @Override
    public boolean updateNotificationToken(String updateNotificationToken) throws Exception {
        fakeDelay();

        return true;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
