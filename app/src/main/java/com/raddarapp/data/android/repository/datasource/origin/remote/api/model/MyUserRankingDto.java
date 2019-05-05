package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class MyUserRankingDto {

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
    @SerializedName("totalFootprints")
    private long totalFootprints;
    @SerializedName("userCompliments")
    private long userCompliments;
    @SerializedName("totalScopeZone")
    private long totalScopeZone;
    @SerializedName("totalFootprintsZone")
    private long totalFootprintsZone;
    @SerializedName("totalLikesZone")
    private long totalLikesZone;
    @SerializedName("totalDislikesZone")
    private long totalDislikesZone;
    @SerializedName("totalLikesUser")
    private long totalLikes;
    @SerializedName("totalDislikesUser")
    private long totalDislikes;
    @SerializedName("totalFootprintsDeadUser")
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

    public long getTotalScopeZone() {
        return totalScopeZone;
    }

    public long getTotalFootprints() {
        return totalFootprints;
    }

    public long getUserCompliments() {
        return userCompliments;
    }

    public long getTotalFootprintsZone() {
        return totalFootprintsZone;
    }

    public long getTotalLikesZone() {
        return totalLikesZone;
    }

    public long getTotalDislikesZone() {
        return totalDislikesZone;
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
