package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.FootprintMainApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.FootprintsMainApiReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.FootprintMainApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsMainReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.FootprintsMainWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.FootprintsMainFakeReadableDataSource;
import com.raddarapp.data.general.datasource.origin.fake.writeable.FootprintsMainFakeWriteableDataSource;

import javax.inject.Inject;

public class FootprintsMainDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public FootprintsMainDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public FootprintsMainReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new FootprintsMainFakeReadableDataSource(userProfilePreferencesDataSource);
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            FootprintMainApiClient footprintMainApiClient = new FootprintMainApiClient(serverApiConfig);
            return new FootprintsMainApiReadableDataSource(footprintMainApiClient);
        }
    }

    public FootprintsMainWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new FootprintsMainFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            FootprintMainApiClient footprintMainApiClient = new FootprintMainApiClient(serverApiConfig);
            return new FootprintMainApiWriteableDataSource(footprintMainApiClient);
        }
    }
}
