package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel. CoinMiningViewModel;

public class CoinMiningViewModelBuilder {

    private int rangeMined;
    private int rangeNotMined;
    private int level;
    private long newRange;
    private String miningDate;
    private int newPercentage;

    public CoinMiningViewModelBuilder() {}

    public  CoinMiningViewModel build() {
        return new  CoinMiningViewModel(rangeMined, rangeNotMined, level, newRange, miningDate, newPercentage);
    }

    public CoinMiningViewModelBuilder withRangeMined(int rangeMined) {
        this.rangeMined = rangeMined;
        return this;
    }

    public CoinMiningViewModelBuilder withRangeNotMined(int rangeNotMined) {
        this.rangeNotMined = rangeNotMined;
        return this;
    }

    public CoinMiningViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public CoinMiningViewModelBuilder withNewRange(long newRange) {
        this.newRange = newRange;
        return this;
    }

    public CoinMiningViewModelBuilder withMiningDateName(String miningDate) {
        this.miningDate = miningDate;
        return this;
    }

    public CoinMiningViewModelBuilder withNewPercentage(int percentage) {
        this.newPercentage = percentage;
        return this;
    }
}
