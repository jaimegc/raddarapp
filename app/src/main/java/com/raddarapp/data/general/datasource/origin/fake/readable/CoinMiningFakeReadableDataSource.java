package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.CoinMiningReadableDataSourceContract;
import com.raddarapp.domain.model.CoinMining;
import com.raddarapp.domain.model.builder.CoinMiningBuilder;
import com.raddarapp.presentation.android.utils.DateUtils;

import javax.inject.Inject;

public class CoinMiningFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, CoinMining>
    implements CoinMiningReadableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private MyUserProfilePreferencesDataSourceContract userProfilePreferences;

    @Inject
    public CoinMiningFakeReadableDataSource(MyUserProfilePreferencesDataSourceContract userProfilePreferences) {
        this.userProfilePreferences = userProfilePreferences;
    }

    @Override
    public CoinMining getCoinMining() throws Exception {

        fakeDelay();

        CoinMining coinMining = new CoinMiningBuilder()
                .withRangeMined(9000)
                .withRangeNotMined(0)
                .withLevel(userProfilePreferences.getLevel())
                .withNewRange(userProfilePreferences.getRange())
                .withMiningDate(new DateUtils().millisecondsToString(System.currentTimeMillis()))
                .withNewPercentage(userProfilePreferences.getPercentage())
                .build();

        return coinMining;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
