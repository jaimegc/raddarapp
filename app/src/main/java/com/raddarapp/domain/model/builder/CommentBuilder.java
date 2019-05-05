package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.User;

public class CommentBuilder {

    private String key;
    private String body;
    private String creationMoment;

    private User owner;

    public CommentBuilder() {}

    public Comment build() {
        return new Comment(key, body, creationMoment, owner);
    }

    public CommentBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CommentBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CommentBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public CommentBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
