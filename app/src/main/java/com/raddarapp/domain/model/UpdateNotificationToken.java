package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdateNotificationToken implements Identifiable<String> {

    private final String key;
    private final String notificationValue;

    public UpdateNotificationToken(String key, String notificationValue) {
        this.key = key;
        this.notificationValue = notificationValue;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getNotificationValue() {
        return notificationValue;
    }
}
