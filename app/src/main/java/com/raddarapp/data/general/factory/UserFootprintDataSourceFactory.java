package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UserFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.UserFootprintsApiReadableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.UserFootprintReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.UserFootprintsFakeReadableDataSource;

import javax.inject.Inject;

public class UserFootprintDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public UserFootprintDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public UserFootprintReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new UserFootprintsFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            UserFootprintApiClient userProfileApiClient = new UserFootprintApiClient(serverApiConfig);
            return new UserFootprintsApiReadableDataSource(userProfileApiClient);
        }
    }
}
