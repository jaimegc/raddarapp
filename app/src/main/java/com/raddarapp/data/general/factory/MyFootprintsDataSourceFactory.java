package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.MyFootprintsApiReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.MyFootprintApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.MyFootprintsFakeReadableDataSource;
import com.raddarapp.data.general.datasource.origin.fake.writeable.MyFootprintsFakeWriteableDataSource;

import javax.inject.Inject;

public class MyFootprintsDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public MyFootprintsDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public MyFootprintReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyFootprintsFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyFootprintApiClient footprintMainApiClient = new MyFootprintApiClient(serverApiConfig);
            return new MyFootprintsApiReadableDataSource(footprintMainApiClient);
        }
    }

    public MyFootprintsWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyFootprintsFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyFootprintApiClient myFootprintApiClient = new MyFootprintApiClient(serverApiConfig);
            return new MyFootprintApiWriteableDataSource(myFootprintApiClient);
        }
    }
}
