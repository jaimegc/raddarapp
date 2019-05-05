package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CoinMiningDto;

public class CoinMiningDtoBuilder {

    private int rangeMined;
    private int rangeNotMined;
    private long newRange;
    private int level;
    private String miningDate;
    private int newPercentage;

    public CoinMiningDtoBuilder() {}

    public CoinMiningDto build() {
        return new CoinMiningDto(rangeMined, rangeNotMined, newRange, level, miningDate, newPercentage);
    }

    public CoinMiningDtoBuilder withRangeMined(int rangeMined) {
        this.rangeMined = rangeMined;
        return this;
    }

    public CoinMiningDtoBuilder withRangeNotMined(int rangeNotMined) {
        this.rangeNotMined = rangeNotMined;
        return this;
    }

    public CoinMiningDtoBuilder withNewRange(long newRange) {
        this.newRange = newRange;
        return this;
    }

    public CoinMiningDtoBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public CoinMiningDtoBuilder withMiningDate(String miningDate) {
        this.miningDate = miningDate;
        return this;
    }

    public CoinMiningDtoBuilder withNewPercentage(int newPercentage) {
        this.newPercentage = newPercentage;
        return this;
    }
}
