package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class CreationMomentDto {

    @SerializedName("creationMoment")
    private String creationMoment;

    public CreationMomentDto(String creationMoment) {
        this.creationMoment = creationMoment;
    }

    public String getCreationMoment() {
        return creationMoment;
    }
}
