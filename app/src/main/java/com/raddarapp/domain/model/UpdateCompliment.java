package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UpdateCompliment implements Identifiable<String> {

    private final String key;
    private final long compliments;


    @Override
    public String getKey() {
        return key;
    }

    public UpdateCompliment(String key, long compliments) {
        this.key = key;
        this.compliments = compliments;
    }

    public long getCompliments() {
        return compliments;
    }
}
