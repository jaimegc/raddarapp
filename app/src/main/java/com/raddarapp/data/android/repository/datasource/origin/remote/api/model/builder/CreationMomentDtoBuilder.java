package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreationMomentDto;

public class CreationMomentDtoBuilder {

    private String creationMoment;

    public CreationMomentDtoBuilder() {}

    public CreationMomentDto build() {
        return new CreationMomentDto(creationMoment);
    }

    public CreationMomentDtoBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

}
