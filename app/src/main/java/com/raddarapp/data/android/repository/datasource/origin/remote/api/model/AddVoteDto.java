package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class AddVoteDto {

    @SerializedName("actualScope")
    private double actualScope;

    public double getActualScope() {
        return actualScope;
    }
}
