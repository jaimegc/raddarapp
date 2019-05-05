package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CoinMiningApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.readable.CoinMiningApiReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.CoinMiningApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.CoinMiningReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.CoinMiningWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.readable.CoinMiningFakeReadableDataSource;
import com.raddarapp.data.general.datasource.origin.fake.writeable.CoinMiningFakeWriteableDataSource;

import javax.inject.Inject;

public class CoinMiningDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public CoinMiningDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public CoinMiningWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new CoinMiningFakeWriteableDataSource(userProfilePreferencesDataSource);
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            CoinMiningApiClient coinMiningApiClient = new CoinMiningApiClient(serverApiConfig);
            return new CoinMiningApiWriteableDataSource(coinMiningApiClient);
        }
    }

    public CoinMiningReadableDataSourceContract createReadableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new CoinMiningFakeReadableDataSource(userProfilePreferencesDataSource);
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            CoinMiningApiClient coinMiningApiClient = new CoinMiningApiClient(serverApiConfig);
            return new CoinMiningApiReadableDataSource(coinMiningApiClient);
        }
    }
}
