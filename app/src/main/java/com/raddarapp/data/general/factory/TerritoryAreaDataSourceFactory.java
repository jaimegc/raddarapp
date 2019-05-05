package com.raddarapp.data.general.factory;

import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoryAreaReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.TerritoryAreaFakeReadableDataSource;

import javax.inject.Inject;

public class TerritoryAreaDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public TerritoryAreaDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public TerritoryAreaReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new TerritoryAreaFakeReadableDataSource();
        } else {
            /*ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            TerritoryAreaApiClient territoryAreaApiClient = new TerritoryAreaApiClient(serverApiConfig);
            return new TerritoryAreaApiReadableDataSource(territoryAreaApiClient);*/
            return new TerritoryAreaFakeReadableDataSource();
        }
    }
}
