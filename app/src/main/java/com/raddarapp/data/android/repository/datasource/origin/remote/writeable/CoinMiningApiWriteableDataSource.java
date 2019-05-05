package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CoinMiningApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.CoinMiningToCoinMiningDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.CoinMiningWriteableDataSourceContract;
import com.raddarapp.domain.model.CoinMining;

import javax.inject.Inject;

public class CoinMiningApiWriteableDataSource extends EmptyWriteableDataSource<String, CoinMining>
        implements CoinMiningWriteableDataSourceContract {

    private final CoinMiningApiClient coinMiningApiClient;
    private final CoinMiningToCoinMiningDtoMapper mapper = new CoinMiningToCoinMiningDtoMapper();

    @Inject
    public CoinMiningApiWriteableDataSource(CoinMiningApiClient coinMiningApiClient) {
        this.coinMiningApiClient = coinMiningApiClient;
    }

    @Override
    public CoinMining coinMining(CoinMining coinMining) throws Exception {
        CoinMining coinMined = null;
        CoinMiningDto coinMinedDto = null;

        coinMinedDto = coinMiningApiClient.coinMining(mapper.map(coinMining));
        coinMined = mapper.reverseMap(coinMinedDto);

        return coinMined;
    }
}