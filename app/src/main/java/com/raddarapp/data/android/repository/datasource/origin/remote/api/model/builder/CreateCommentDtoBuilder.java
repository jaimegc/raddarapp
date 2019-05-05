package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;

public class CreateCommentDtoBuilder {

    private String id;
    private String body;
    private String creationMoment;

    public CreateCommentDtoBuilder() {}

    public CreateCommentDto build() {
        return new CreateCommentDto(id, body, creationMoment);
    }

    public CreateCommentDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CreateCommentDtoBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CreateCommentDtoBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

}
