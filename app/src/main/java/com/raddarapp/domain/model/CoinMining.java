package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class CoinMining implements Identifiable<String> {

    private final String key;
    private final int rangeMined;
    private final int rangeNotMined;
    private final long newRange;
    private final int level;
    private final String miningDate;
    private final int newPercentage;

    public CoinMining(String key, int rangeMined, int rangeNotMined, long newRange, int level, String miningDate, int newPercentage) {
        this.key = key;
        this.rangeMined = rangeMined;
        this.rangeNotMined = rangeNotMined;
        this.newRange = newRange;
        this.level = level;
        this.miningDate = miningDate;
        this.newPercentage = newPercentage;
    }

    @Override
    public String getKey() {
        return key;
    }

    public int getRangeMined() {
        return rangeMined;
    }

    public int getRangeNotMined() {
        return rangeNotMined;
    }

    public long getNewRange() {
        return newRange;
    }

    public int getLevel() {
        return level;
    }

    public String getMiningDate() {
        return miningDate;
    }

    public int getNewPercentage() {
        return newPercentage;
    }
}
