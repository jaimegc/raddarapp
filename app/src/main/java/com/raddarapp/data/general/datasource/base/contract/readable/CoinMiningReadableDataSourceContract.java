package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.domain.model.CoinMining;

public interface CoinMiningReadableDataSourceContract extends PaginatedReadableDataSource<String, CoinMining> {

    CoinMining getCoinMining() throws Exception;
}
