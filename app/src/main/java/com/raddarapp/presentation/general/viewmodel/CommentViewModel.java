package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;

public class CommentViewModel implements CommentViewModelContract {

    private final String key;
    private final String body;
    private final String creationMoment;
    private final String userKey;
    private final String name;
    private final String surname;
    private final String username;
    private final String userLevel;
    private final String photo;
    private final int userRelationship;

    public CommentViewModel(String key, String body, String creationMoment, String userKey, String name,
            String surname, String username, String userLevel, String photo, int userRelationship) {
        this.key = key;
        this.body = body;
        this.creationMoment = creationMoment;
        this.userKey = userKey;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.userLevel = userLevel;
        this.photo = photo;
        this.userRelationship = userRelationship;
    }

    public String getKey() {
        return key;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoto() {
        return photo;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public int getUserRelationship() {
        return userRelationship;
    }
}
