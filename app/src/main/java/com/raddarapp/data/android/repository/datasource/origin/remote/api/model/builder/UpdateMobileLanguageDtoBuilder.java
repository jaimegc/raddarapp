package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateMobileLanguageDto;

public class UpdateMobileLanguageDtoBuilder {

    private String language;

    public UpdateMobileLanguageDtoBuilder() {}

    public UpdateMobileLanguageDto build() {
        return new UpdateMobileLanguageDto(language);
    }

    public UpdateMobileLanguageDtoBuilder withMobileLanguage(String password) {
        this.language = password;
        return this;
    }
}
