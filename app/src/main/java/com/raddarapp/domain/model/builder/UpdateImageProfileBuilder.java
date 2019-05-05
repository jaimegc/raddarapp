package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateImageProfile;

public class UpdateImageProfileBuilder {

    private String key;
    private String mediaName;

    public UpdateImageProfileBuilder() {}

    public UpdateImageProfile build() {
        return new UpdateImageProfile(key, mediaName);
    }

    public UpdateImageProfileBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateImageProfileBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }
}
