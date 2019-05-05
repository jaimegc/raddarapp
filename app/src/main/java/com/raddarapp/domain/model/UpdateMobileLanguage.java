package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdateMobileLanguage implements Identifiable<String> {

    private final String key;
    private final String language;

    public UpdateMobileLanguage(String key, String language) {
        this.key = key;
        this.language = language;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getMobileLanguage() {
        return language;
    }
}
