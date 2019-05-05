package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateMyUserProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.UpdateMyUserProfileApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMyUserProfileWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.UpdateMyUserProfileFakeWriteableDataSource;

import javax.inject.Inject;

public class UpdateMyUserProfileDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public UpdateMyUserProfileDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public UpdateMyUserProfileWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new UpdateMyUserProfileFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            UpdateMyUserProfileApiClient udpateComplimentApiClient = new UpdateMyUserProfileApiClient(serverApiConfig);
            return new UpdateMyUserProfileApiWriteableDataSource(udpateComplimentApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
