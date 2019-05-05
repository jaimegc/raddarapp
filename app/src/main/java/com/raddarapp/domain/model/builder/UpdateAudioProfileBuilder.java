package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateAudioProfile;

public class UpdateAudioProfileBuilder {

    private String key;
    private String mediaName;

    public UpdateAudioProfileBuilder() {}

    public UpdateAudioProfile build() {
        return new UpdateAudioProfile(key, mediaName);
    }

    public UpdateAudioProfileBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateAudioProfileBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }
}
