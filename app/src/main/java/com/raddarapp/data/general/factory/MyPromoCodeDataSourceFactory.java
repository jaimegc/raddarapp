package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyPromoCodeApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.MyPromoCodeApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.MyPromoCodeWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.MyPromoCodeFakeWriteableDataSource;

import javax.inject.Inject;

public class MyPromoCodeDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public MyPromoCodeDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public MyPromoCodeWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyPromoCodeFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyPromoCodeApiClient myPromoCodeApiClient = new MyPromoCodeApiClient(serverApiConfig);
            return new MyPromoCodeApiWriteableDataSource(myPromoCodeApiClient);
        }
    }

    public MyUserProfilePreferencesDataSourceContract createPreferencesDataSource() {
        return userProfilePreferencesDataSource;
    }
}
