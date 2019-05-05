package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.FootprintRankingApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.FootprintsRankingApiReadableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsRankingReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.FootprintsRankingFakeReadableDataSource;

import javax.inject.Inject;

public class FootprintsRankingDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public FootprintsRankingDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public FootprintsRankingReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new FootprintsRankingFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            FootprintRankingApiClient footprintRankingApiClient = new FootprintRankingApiClient(serverApiConfig);
            return new FootprintsRankingApiReadableDataSource(footprintRankingApiClient);
        }
    }
}
