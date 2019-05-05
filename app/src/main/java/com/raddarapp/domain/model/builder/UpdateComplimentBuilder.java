package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.UpdateCompliment;

public class UpdateComplimentBuilder {

    private String key;
    private long compliments;

    public UpdateComplimentBuilder() {}

    public UpdateCompliment build() {
        return new UpdateCompliment(key, compliments);
    }

    public UpdateComplimentBuilder withUserKey(String key) {
        this.key = key;
        return this;
    }

    public UpdateComplimentBuilder withCompliments(long compliments) {
        this.compliments = compliments;
        return this;
    }
}
