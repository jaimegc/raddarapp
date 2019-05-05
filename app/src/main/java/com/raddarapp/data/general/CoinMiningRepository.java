package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.readable.CoinMiningReadableDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.CoinMiningWriteableDataSourceContract;
import com.raddarapp.data.general.factory.CoinMiningDataSourceFactory;
import com.raddarapp.domain.model.CoinMining;

import javax.inject.Inject;

public class CoinMiningRepository extends RosieRepository<String, CoinMining> {

    private final CoinMiningWriteableDataSourceContract coinMiningWriteableDataSource;
    private final CoinMiningReadableDataSourceContract coinMiningReadableDataSource;

    @Inject
    CoinMiningRepository(CoinMiningDataSourceFactory coinMiningDataSourceFactory) {

        coinMiningWriteableDataSource = coinMiningDataSourceFactory.createWriteableDataSource();
        coinMiningReadableDataSource = coinMiningDataSourceFactory.createReadableDataSource();

        addWriteableDataSources(coinMiningWriteableDataSource);
        addReadableDataSources(coinMiningReadableDataSource);
    }

    public CoinMining coinMining(CoinMining coinMining) throws Exception {
        CoinMining coinMined;

        coinMined = coinMiningWriteableDataSource.coinMining(coinMining);

        return coinMined;
    }

    public CoinMining getCoinMining() throws Exception {
        CoinMining coinMined;

        coinMined = coinMiningReadableDataSource.getCoinMining();

        return coinMined;
    }
}
