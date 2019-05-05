package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.CoinMiningRepository;

import javax.inject.Inject;

public class GetCoinMining extends RosieUseCase {

    public static final String USE_CASE_GET_COIN_MINING = "getCoinMining";

    private CoinMiningRepository coinMiningRepository;

    @Inject
    public GetCoinMining(CoinMiningRepository coinMiningRepository) {
        this.coinMiningRepository = coinMiningRepository;
    }

    @UseCase(name = USE_CASE_GET_COIN_MINING)
    public void getCoinMining() throws Exception {
        com.raddarapp.domain.model.CoinMining coinMined = coinMiningRepository.getCoinMining();
        notifySuccess(coinMined);
    }
}
