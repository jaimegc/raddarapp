package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateNotificationToken;

public class UpdateNotificationTopicBuilder {

    private String key;
    private String notificationValue;

    public UpdateNotificationTopicBuilder() {}

    public UpdateNotificationToken build() {
        return new UpdateNotificationToken(key, notificationValue);
    }

    public UpdateNotificationTopicBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateNotificationTopicBuilder withNotificationValue(String notificationValue) {
        this.notificationValue = notificationValue;
        return this;
    }
}
