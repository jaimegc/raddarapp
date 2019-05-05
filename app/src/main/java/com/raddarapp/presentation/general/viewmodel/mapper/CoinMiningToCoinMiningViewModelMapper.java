package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.CoinMining;
import com.raddarapp.presentation.general.viewmodel. CoinMiningViewModel;
import com.raddarapp.presentation.general.viewmodel.builder. CoinMiningViewModelBuilder;

import javax.inject.Inject;

public class CoinMiningToCoinMiningViewModelMapper extends Mapper<CoinMining, CoinMiningViewModel> {

    @Inject
    public CoinMiningToCoinMiningViewModelMapper() {}

    @Override
    public CoinMiningViewModel map(CoinMining coinMining) {

         final CoinMiningViewModel coinMiningViewModel =
                new  CoinMiningViewModelBuilder()
                        .withRangeMined(coinMining.getRangeMined())
                        .withRangeNotMined(coinMining.getRangeNotMined())
                        .withLevel(coinMining.getLevel())
                        .withNewRange(coinMining.getNewRange())
                        .withMiningDateName(coinMining.getMiningDate())
                        .withNewPercentage(coinMining.getNewPercentage())
                        .build();

        return coinMiningViewModel;
    }

    @Override
    public CoinMining reverseMap(CoinMiningViewModel coinMiningViewModel) {
        throw new UnsupportedOperationException();
    }
}
