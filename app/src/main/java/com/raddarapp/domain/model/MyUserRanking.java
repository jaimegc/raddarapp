package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class MyUserRanking implements Identifiable<String> {

    private final String key;
    private final String username;
    private final String image;
    private final String audio;
    private final long range;
    private final long followers;
    private final long following;
    private int userRelationship;
    private final int level;
    private final long totalScopeZone;
    private final long totalFootprints;
    private long userCompliments;
    private final long totalFootprintsZone;
    private final long totalLikesZone;
    private final long totalDislikesZone;
    private final long totalLikes;
    private final long totalDislikes;
    private final long totalFootprintsDead;

    public MyUserRanking(String key, String username, String image, String audio, long range, long followers,
            long following, int userRelationship, int level, long totalScopeZone, long totalFootprints,
            long userCompliments, long totalFootprintsZone, long totalLikesZone, long totalDislikesZone,
            long totalLikes, long totalDislikes, long totalFootprintsDead) {
        this.key = key;
        this.username = username;
        this.image = image;
        this.audio = audio;
        this.range = range;
        this.followers = followers;
        this.following = following;
        this.userRelationship = userRelationship;
        this.level = level;
        this.totalScopeZone = totalScopeZone;
        this.totalFootprints = totalFootprints;
        this.userCompliments = userCompliments;
        this.totalFootprintsZone = totalFootprintsZone;
        this.totalLikesZone = totalLikesZone;
        this.totalDislikesZone = totalDislikesZone;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
    }

    @Override
    public String getKey() {
        return key;
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

    protected void updateUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
    }

    public void updateCompliments(long totalCompliments) {
        this.userCompliments += totalCompliments;
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
