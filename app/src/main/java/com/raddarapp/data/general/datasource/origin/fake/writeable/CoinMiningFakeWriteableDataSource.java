package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.CoinMiningWriteableDataSourceContract;
import com.raddarapp.domain.model.CoinMining;
import com.raddarapp.domain.model.builder.CoinMiningBuilder;
import com.raddarapp.presentation.android.utils.DateUtils;

import javax.inject.Inject;

public class CoinMiningFakeWriteableDataSource extends EmptyWriteableDataSource<String, CoinMining>
        implements CoinMiningWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private final MyUserProfilePreferencesDataSourceContract userProfilePreferences;

    @Inject
    public CoinMiningFakeWriteableDataSource(MyUserProfilePreferencesDataSourceContract userProfilePreferences) {
        this.userProfilePreferences = userProfilePreferences;
    }

    @Override
    public CoinMining coinMining(CoinMining coinMining) throws Exception {
        fakeDelay();

        final CoinMining coinMined = new CoinMiningBuilder()
                .withMiningDate(new DateUtils().millisecondsToString(System.currentTimeMillis()))
                .withLevel(userProfilePreferences.getLevel() + 1)
                .withNewRange(userProfilePreferences.getRange() + 100)
                .withMiningDate(new DateUtils().millisecondsToString(System.currentTimeMillis()))
                .withNewPercentage(userProfilePreferences.getPercentage())
                .build();

        return coinMined;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
