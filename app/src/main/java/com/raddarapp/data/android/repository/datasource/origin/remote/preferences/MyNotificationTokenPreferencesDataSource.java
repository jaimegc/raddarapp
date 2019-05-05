package com.raddarapp.data.android.repository.datasource.origin.remote.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.raddarapp.data.general.datasource.base.contract.preferences.MyNotificationTokenPreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.preferences.base.DefaultValuesPrefererences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyNotificationTokenPreferencesDataSource extends DefaultValuesPrefererences implements MyNotificationTokenPreferencesDataSourceContract {

    private static final String PREFERENCE_NAME = "PREFERENCE_NAME_MY_NOTIFICATION_TOKEN";

    private static final String PREF_KEY_MY_NOTIFICATION_TOKEN = "PREF_KEY_MY_NOTIFICATION_TOKEN";

    private final SharedPreferences preferences;

    @Inject
    public MyNotificationTokenPreferencesDataSource(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getMyNotificationToken() {
        return preferences.getString(PREF_KEY_MY_NOTIFICATION_TOKEN, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setMyNotificationToken(String myNotificationToken) {
        preferences.edit().putString(PREF_KEY_MY_NOTIFICATION_TOKEN, myNotificationToken).apply();
    }
}
