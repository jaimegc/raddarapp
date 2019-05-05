package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class CreateComment implements Identifiable<String> {

    private final String key;
    private final String body;
    private final String creationMoment;

    public CreateComment(String key, String body, String creationMoment) {
        this.key = key;
        this.body = body;
        this.creationMoment = creationMoment;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }
}
