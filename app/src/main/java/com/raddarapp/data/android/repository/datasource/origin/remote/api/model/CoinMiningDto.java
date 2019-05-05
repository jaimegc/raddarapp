package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class CoinMiningDto {

    @SerializedName("rangeMined")
    private int rangeMined;
    @SerializedName("rangeNotMined")
    private int rangeNotMined;
    @SerializedName("newRange")
    private long newRange;
    @SerializedName("level")
    private int level;
    @SerializedName("miningDate")
    private String miningDate;
    @SerializedName("newPercentage")
    private int newPercentage;

    public CoinMiningDto(int rangeMined, int rangeNotMined, long newRange, int level, String miningDate, int newPercentage) {
        this.rangeMined = rangeMined;
        this.rangeNotMined = rangeNotMined;
        this.newRange = newRange;
        this.level = level;
        this.miningDate = miningDate;
        this.newPercentage = newPercentage;
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
