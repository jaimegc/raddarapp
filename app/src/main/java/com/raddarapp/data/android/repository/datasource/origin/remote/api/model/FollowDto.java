package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class FollowDto {

    @SerializedName("userRelationship")
    private int userRelationship;

    public int getUserRelationship() {
        return userRelationship;
    }
}
