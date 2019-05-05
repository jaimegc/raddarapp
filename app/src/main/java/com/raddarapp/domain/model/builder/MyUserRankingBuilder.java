package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyUserRanking;

public class MyUserRankingBuilder {

    private String key;
    private String username;
    private String image;
    private String audio;
    private long range;
    private long followers;
    private long following;
    private int userRelationship;
    private int level;
    private long totalScopeZone;
    private long totalFootprints;
    private long userCompliments;
    private long totalFootprintsZone;
    private long totalLikesZone;
    private long totalDislikesZone;
    private long totalLikes;
    private long totalDislikes;
    private long totalFootprintsDead;

    public MyUserRankingBuilder() {}

    public MyUserRanking build() {
        return new MyUserRanking(key, username, image, audio, range, followers, following, userRelationship,
            level, totalScopeZone, totalFootprints, userCompliments, totalFootprintsZone, totalLikesZone,
            totalDislikesZone, totalLikes, totalDislikes, totalFootprintsDead);
    }

    public MyUserRankingBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyUserRankingBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserRankingBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyUserRankingBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyUserRankingBuilder withRange(long range) {
        this.range = range;
        return this;
    }

    public MyUserRankingBuilder withFollowers(long followers) {
        this.followers = followers;
        return this;
    }

    public MyUserRankingBuilder withFollowing(long following) {
        this.following = following;
        return this;
    }

    public MyUserRankingBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyUserRankingBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyUserRankingBuilder withTotalScopeZone(long totalScope) {
        this.totalScopeZone = totalScope;
        return this;
    }

    public MyUserRankingBuilder withTotalFootprints(long totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public MyUserRankingBuilder withUserCompliments(long userCompliments) {
        this.userCompliments = userCompliments;
        return this;
    }

    public MyUserRankingBuilder withTotalFootprintsZone(long totalFootprintsZone) {
        this.totalFootprintsZone = totalFootprintsZone;
        return this;
    }

    public MyUserRankingBuilder withTotalLikesZone(long totalLikesZone) {
        this.totalLikesZone = totalLikesZone;
        return this;
    }

    public MyUserRankingBuilder withTotalDislikesZone(long totalDislikesZone) {
        this.totalDislikesZone = totalDislikesZone;
        return this;
    }

    public MyUserRankingBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public MyUserRankingBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public MyUserRankingBuilder withTotalFootprintsDead(long totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
