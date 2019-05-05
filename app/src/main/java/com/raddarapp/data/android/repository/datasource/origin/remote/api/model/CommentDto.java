package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class CommentDto {

    @SerializedName("id")
    private String id;
    @SerializedName("body")
    private String body;
    @SerializedName("creationMoment")
    private String creationMoment;

    @SerializedName("owner")
    private UserDto ownerDto;

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getCreationMoment() {
        return creationMoment;
    }

    public UserDto getOwnerDto() {
        return ownerDto;
    }
}
