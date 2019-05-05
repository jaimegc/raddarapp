package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdatePasswordProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.UpdatePasswordProfileApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdatePasswordProfileWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.UpdatePasswordProfileFakeWriteableDataSource;

import javax.inject.Inject;

public class UpdatePasswordProfileDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public UpdatePasswordProfileDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public UpdatePasswordProfileWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new UpdatePasswordProfileFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            UpdatePasswordProfileApiClient updatePasswordProfileApiClient = new UpdatePasswordProfileApiClient(serverApiConfig);
            return new UpdatePasswordProfileApiWriteableDataSource(updatePasswordProfileApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
