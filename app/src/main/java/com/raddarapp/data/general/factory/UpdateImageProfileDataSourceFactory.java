package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateImageProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.UpdateImageProfileApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateImageProfileWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.UpdateImageProfileFakeWriteableDataSource;

import javax.inject.Inject;

public class UpdateImageProfileDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public UpdateImageProfileDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public UpdateImageProfileWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new UpdateImageProfileFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            UpdateImageProfileApiClient updateImageProfileApiClient = new UpdateImageProfileApiClient(serverApiConfig);
            return new UpdateImageProfileApiWriteableDataSource(updateImageProfileApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
