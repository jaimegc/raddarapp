package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.CoinMining;

public interface CoinMiningWriteableDataSourceContract extends WriteableDataSource<String, CoinMining> {

    CoinMining coinMining(CoinMining coinMining) throws Exception;
}
