package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdateAudioProfile implements Identifiable<String> {

    private final String key;
    private final String mediaName;

    public UpdateAudioProfile(String key, String mediaName) {
        this.key = key;
        this.mediaName = mediaName;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getMediaName() {
        return mediaName;
    }
}
