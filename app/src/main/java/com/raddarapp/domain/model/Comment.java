package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class Comment implements Identifiable<String> {

    private final String key;
    private final String body;
    private final String creationMoment;
    private final User owner;


    @Override
    public String getKey() {
        return key;
    }

    public Comment(String key, String body, String creationMoment, User owner) {
        this.key = key;
        this.body = body;
        this.creationMoment = creationMoment;
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }

    public User getOwner() {
        return owner;
    }
}
