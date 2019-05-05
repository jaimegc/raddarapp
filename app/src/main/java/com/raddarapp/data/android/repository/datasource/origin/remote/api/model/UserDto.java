package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class UserDto {

    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("profileImage")
    private String image;
    @SerializedName("profileAudio")
    private String audio;
    @SerializedName("range")
    private long range;
    @SerializedName("followers")
    private long followers;
    @SerializedName("following")
    private long following;
    @SerializedName("userRelationship")
    private int userRelationship;
    @SerializedName("level")
    private int level;
    @SerializedName("totalFootPrints")
    private long totalFootprints;
    @SerializedName("userCompliments")
    private long userCompliments;
    @SerializedName("totalLikes")
    private long totalLikes;
    @SerializedName("totalDislikes")
    private long totalDislikes;
    @SerializedName("totalFootprintsDead")
    private long totalFootprintsDead;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public String getAudio() {
        return audio;
    }

    public long getRange() {
        return range;
    }

    public long getFollowers() {
        return followers;
    }

    public long getFollowing() {
        return following;
    }

    public int getUserRelationship() {
        return userRelationship;
    }

    public int getLevel() {
        return level;
    }

    public long getTotalFootprints() {
        return totalFootprints;
    }

    public long getUserCompliments() {
        return userCompliments;
    }

    public long getTotalLikes() {
        return totalLikes;
    }

    public long getTotalDislikes() {
        return totalDislikes;
    }

    public long getTotalFootprintsDead() {
        return totalFootprintsDead;
    }
}
