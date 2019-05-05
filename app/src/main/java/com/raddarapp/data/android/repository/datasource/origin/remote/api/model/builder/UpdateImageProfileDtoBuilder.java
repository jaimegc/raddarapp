package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateImageProfileDto;

public class UpdateImageProfileDtoBuilder {

    private String id;
    private String mediaName;

    public UpdateImageProfileDtoBuilder() {}

    public UpdateImageProfileDto build() {
        return new UpdateImageProfileDto(mediaName);
    }

    public UpdateImageProfileDtoBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }
}
