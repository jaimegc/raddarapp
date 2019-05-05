package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.CoinMiningViewModelContract;

public class CoinMiningViewModel implements CoinMiningViewModelContract {

    private final int rangeMined;
    private final int rangeNotMined;
    private final int level;
    private final long newRange;
    private final String miningDate;
    private final int newPercentage;

    public CoinMiningViewModel(int rangeMined, int rangeNotMined, int level, long newRange, String miningDate, int newPercentage) {
        this.rangeMined = rangeMined;
        this.rangeNotMined = rangeNotMined;
        this.level = level;
        this.newRange = newRange;
        this.miningDate = miningDate;
        this.newPercentage = newPercentage;
    }

    public int getRangeMined() {
        return rangeMined;
    }

    public int getRangeNotMined() {
        return rangeNotMined;
    }

    public int getLevel() {
        return level;
    }

    public long getNewRange() {
        return newRange;
    }

    public String getMiningDate() {
        return miningDate;
    }

    public int getNewPercentage() {
        return newPercentage;
    }
}
