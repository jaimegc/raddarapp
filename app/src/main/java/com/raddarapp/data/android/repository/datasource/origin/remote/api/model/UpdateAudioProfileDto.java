package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UpdateAudioProfileDto {

    @SerializedName("mediaName")
    private String mediaName;

    public UpdateAudioProfileDto(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getMediaName() {
        return mediaName;
    }
}
