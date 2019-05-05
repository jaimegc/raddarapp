package com.raddarapp.data.general.factory;

import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.TerritoriesReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.TerritoriesFakeReadableDataSource;

import javax.inject.Inject;

public class TerritoriesDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public TerritoriesDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public TerritoriesReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            //ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            //FootprintMainApiClient footprintMainApiClient = new FootprintMainApiClient(serverApiConfig);
            //return new FootprintsMainApiReadableDataSource(footprintMainApiClient);
            return new TerritoriesFakeReadableDataSource();
        } else {
            return new TerritoriesFakeReadableDataSource();
        }
    }
}
