package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class CreateCommentDto {

    @SerializedName("id")
    private String id;
    @SerializedName("body")
    private String body;
    @SerializedName("creationMoment")
    private String creationMoment;

    public CreateCommentDto(String id, String body, String creationMoment) {
        this.id = id;
        this.body = body;
        this.creationMoment = creationMoment;

    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }
}
