package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateMobileLanguage;

public class UpdateMobileLanguageBuilder {

    private String key;
    private String mobileLanguage;

    public UpdateMobileLanguageBuilder() {}

    public UpdateMobileLanguage build() {
        return new UpdateMobileLanguage(key, mobileLanguage);
    }

    public UpdateMobileLanguageBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateMobileLanguageBuilder withMobileLanguage(String mobileLanguage) {
        this.mobileLanguage = mobileLanguage;
        return this;
    }
}
