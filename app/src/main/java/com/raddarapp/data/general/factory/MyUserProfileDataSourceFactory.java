package com.raddarapp.data.general.factory;

import android.content.Context;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyUserProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.MyUserProfileApiReadableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserProfileReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.MyUserProfileFakeReadableDataSource;

import javax.inject.Inject;

public class MyUserProfileDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public MyUserProfileDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public MyUserProfileReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyUserProfileFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyUserProfileApiClient userProfileApiClient = new MyUserProfileApiClient(serverApiConfig);
            return new MyUserProfileApiReadableDataSource(userProfileApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
