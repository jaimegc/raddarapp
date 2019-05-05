package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateAudioProfileDto;

public class UpdateAudioProfileDtoBuilder {

    private String id;
    private String mediaName;

    public UpdateAudioProfileDtoBuilder() {}

    public UpdateAudioProfileDto build() {
        return new UpdateAudioProfileDto(mediaName);
    }

    public UpdateAudioProfileDtoBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }
}
