package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.CoinMiningDtoBuilder;
import com.raddarapp.domain.model.CoinMining;
import com.raddarapp.domain.model.builder.CoinMiningBuilder;


public class CoinMiningToCoinMiningDtoMapper extends Mapper<CoinMining, CoinMiningDto> {

    @Override
    public CoinMining reverseMap(CoinMiningDto coinMiningDto) {
        CoinMining coinMining = new CoinMiningBuilder()
                .withRangeMined(coinMiningDto.getRangeMined())
                .withRangeNotMined(coinMiningDto.getRangeNotMined())
                .withLevel(coinMiningDto.getLevel())
                .withNewRange(coinMiningDto.getNewRange())
                .withMiningDate(coinMiningDto.getMiningDate())
                .withNewPercentage(coinMiningDto.getNewPercentage())
                .build();

        return coinMining;
    }

    @Override
    public CoinMiningDto map(CoinMining coinMining) {
        final CoinMiningDto coinMiningDto = new CoinMiningDtoBuilder()
                .withRangeMined(coinMining.getRangeMined())
                .build();

        return coinMiningDto;
    }
}
