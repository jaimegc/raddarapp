package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.CreateComment;

public class CreateCommentBuilder {

    private String key;
    private String body;
    private String creationMoment;

    public CreateCommentBuilder() {}

    public CreateComment build() {
        return new CreateComment(key, body, creationMoment);
    }

    public CreateCommentBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CreateCommentBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CreateCommentBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }
}
