package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.CoinMining;

public class CoinMiningBuilder {

    private String key;
    private int rangeMined;
    private int rangeNotMined;
    private long newRange;
    private int level;
    private String miningDate;
    private int newPercentage;

    public CoinMiningBuilder() {}

    public CoinMining build() {
        return new CoinMining(key, rangeMined, rangeNotMined, newRange, level, miningDate, newPercentage);
    }

    public CoinMiningBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CoinMiningBuilder withRangeMined(int rangeMined) {
        this.rangeMined = rangeMined;
        return this;
    }

    public CoinMiningBuilder withRangeNotMined(int rangeNotMined) {
        this.rangeNotMined = rangeNotMined;
        return this;
    }

    public CoinMiningBuilder withNewRange(long newRange) {
        this.newRange = newRange;
        return this;
    }

    public CoinMiningBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public CoinMiningBuilder withMiningDate(String miningDate) {
        this.miningDate = miningDate;
        return this;
    }

    public CoinMiningBuilder withNewPercentage(int percentage) {
        this.newPercentage = percentage;
        return this;
    }
}
