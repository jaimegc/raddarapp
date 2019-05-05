package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateAudioProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.UpdateAudioProfileApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateAudioProfileWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.UpdateAudioProfileFakeWriteableDataSource;

import javax.inject.Inject;

public class UpdateAudioProfileDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public UpdateAudioProfileDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public UpdateAudioProfileWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new UpdateAudioProfileFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            UpdateAudioProfileApiClient updateAudioProfileApiClient = new UpdateAudioProfileApiClient(serverApiConfig);
            return new UpdateAudioProfileApiWriteableDataSource(updateAudioProfileApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
