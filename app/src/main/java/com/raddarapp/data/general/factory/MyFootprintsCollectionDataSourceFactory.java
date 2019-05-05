package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyFootprintCollectionApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.MyFootprintsCollectionApiReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.MyFootprintCollectionApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintsCollectionReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyFootprintsCollectionWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.MyFootprintsCollectionFakeReadableDataSource;
import com.raddarapp.data.general.datasource.origin.fake.writeable.MyFootprintsCollectionFakeWriteableDataSource;

import javax.inject.Inject;

public class MyFootprintsCollectionDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public MyFootprintsCollectionDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public MyFootprintsCollectionReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyFootprintsCollectionFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyFootprintCollectionApiClient myFootprintCollectionApiClient = new MyFootprintCollectionApiClient(serverApiConfig);
            return new MyFootprintsCollectionApiReadableDataSource(myFootprintCollectionApiClient);
        }
    }

    public MyFootprintsCollectionWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyFootprintsCollectionFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyFootprintCollectionApiClient myFootprintCollectionApiClient = new MyFootprintCollectionApiClient(serverApiConfig);
            return new MyFootprintCollectionApiWriteableDataSource(myFootprintCollectionApiClient);
        }
    }
}
