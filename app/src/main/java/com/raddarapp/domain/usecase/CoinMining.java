package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.CoinMiningRepository;

import javax.inject.Inject;

public class CoinMining extends RosieUseCase {

    public static final String USE_CASE_COIN_MINING = "coinMining";

    private CoinMiningRepository coinMiningRepository;

    @Inject
    public CoinMining(CoinMiningRepository coinMiningRepository) {
        this.coinMiningRepository = coinMiningRepository;
    }

    @UseCase(name = USE_CASE_COIN_MINING)
    public void coinMining(com.raddarapp.domain.model.CoinMining coinMining) throws Exception {
        com.raddarapp.domain.model.CoinMining coinMined = coinMiningRepository.coinMining(coinMining);
        notifySuccess(coinMined);
    }
}
