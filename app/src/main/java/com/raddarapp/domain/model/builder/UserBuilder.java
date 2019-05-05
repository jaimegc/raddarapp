package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.User;

public class UserBuilder {

    private String key;
    private String username;
    private String image;
    private String audio;
    private long range;
    private long followers;
    private long following;
    private int userRelationship;
    private int level;
    private long totalFootprints;
    private long userCompliments;
    private long totalLikes;
    private long totalDislikes;
    private long totalFootprintsDead;

    public UserBuilder() {}

    public User build() {
        return new User(key, username, image, audio, range, followers, following, userRelationship,
            level, totalFootprints, userCompliments, totalLikes, totalDislikes, totalFootprintsDead);
    }

    public UserBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public UserBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public UserBuilder withRange(long range) {
        this.range = range;
        return this;
    }

    public UserBuilder withFollowers(long followers) {
        this.followers = followers;
        return this;
    }

    public UserBuilder withFollowing(long following) {
        this.following = following;
        return this;
    }

    public UserBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public UserBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public UserBuilder withTotalFootprints(long totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public UserBuilder withUserCompliments(long userCompliments) {
        this.userCompliments = userCompliments;
        return this;
    }

    public UserBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public UserBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public UserBuilder withTotalFootprintsDead(long totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
