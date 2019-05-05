package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CoinMiningApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.CoinMiningToCoinMiningDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;
import com.raddarapp.data.general.datasource.base.contract.readable.CoinMiningReadableDataSourceContract;
import com.raddarapp.domain.model.CoinMining;

import javax.inject.Inject;

public class CoinMiningApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, CoinMining>
        implements CoinMiningReadableDataSourceContract {

    private final CoinMiningApiClient coinMiningApiClient;
    private final CoinMiningToCoinMiningDtoMapper mapper = new CoinMiningToCoinMiningDtoMapper();

    @Inject
    public CoinMiningApiReadableDataSource(CoinMiningApiClient coinMiningApiClient) {
        this.coinMiningApiClient = coinMiningApiClient;
    }

    @Override
    public CoinMining getCoinMining() throws Exception {
        CoinMining coinMining;

        CoinMiningDto coinMiningDto = coinMiningApiClient.getCoinMining();

        coinMining = mapper.reverseMap(coinMiningDto);

        return coinMining;
    }
}
