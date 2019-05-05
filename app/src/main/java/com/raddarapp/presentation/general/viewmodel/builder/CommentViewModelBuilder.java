package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.CommentViewModel;

public class CommentViewModelBuilder {

    private String key;
    private String body;
    private String creationMoment;
    private String userKey;
    private String name;
    private String surname;
    private String username;
    private String userLevel;
    private String photo;
    private int userRelationship;

    public CommentViewModelBuilder() {}

    public CommentViewModel build() {
        return new CommentViewModel(key, body, creationMoment, userKey, name, surname, username, userLevel, photo, userRelationship);
    }

    public CommentViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CommentViewModelBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CommentViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public CommentViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public CommentViewModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CommentViewModelBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public CommentViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public CommentViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public CommentViewModelBuilder withPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public CommentViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }
}
