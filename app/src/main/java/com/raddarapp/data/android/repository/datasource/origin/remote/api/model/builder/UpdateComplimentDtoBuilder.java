package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.UpdateComplimentDto;

public class UpdateComplimentDtoBuilder {

    private String userId;
    private long compliments;

    public UpdateComplimentDtoBuilder() {}

    public UpdateComplimentDto build() {
        return new UpdateComplimentDto(userId, compliments);
    }

    public UpdateComplimentDtoBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UpdateComplimentDtoBuilder withCompliments(long compliments) {
        this.compliments = compliments;
        return this;
    }

}
