package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.MyUserRankingApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.MyUsersRankingApiReadableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserRankingReadableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.MyUsersRankingFakeReadableDataSource;

import javax.inject.Inject;

public class MyUsersRankingDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public MyUsersRankingDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public MyUserRankingReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new MyUsersRankingFakeReadableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            MyUserRankingApiClient footprintMainApiClient = new MyUserRankingApiClient(serverApiConfig);
            return new MyUsersRankingApiReadableDataSource(footprintMainApiClient);
        }
    }
}
