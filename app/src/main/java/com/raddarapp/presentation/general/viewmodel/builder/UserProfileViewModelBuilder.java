package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;

public class UserProfileViewModelBuilder {

    private String key;
    private String username;
    private String image;
    private String audio;
    private long range;
    private String followers;
    private String following;
    private String totalFootprints;
    private long userCompliments;
    private String level;
    private long totalLikes;
    private long totalDislikes;
    private String totalFootprintsDead;

    public UserProfileViewModelBuilder() {
    }

    public UserProfileViewModel build() {
        return new UserProfileViewModel(key, username, image, audio, range, followers, following,
            totalFootprints, userCompliments, level, totalLikes, totalDislikes, totalFootprintsDead);
    }

    public UserProfileViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UserProfileViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserProfileViewModelBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public UserProfileViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public UserProfileViewModelBuilder withRange(long range) {
        this.range = range;
        return this;
    }

    public UserProfileViewModelBuilder withFollowers(String followers) {
        this.followers = followers;
        return this;
    }

    public UserProfileViewModelBuilder withFollowing(String following) {
        this.following = following;
        return this;
    }

    public UserProfileViewModelBuilder withTotalFootprints(String totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public UserProfileViewModelBuilder withUserCompliments(long userCompliments) {
        this.userCompliments = userCompliments;
        return this;
    }

    public UserProfileViewModelBuilder withLevel(String level) {
        this.level = level;
        return this;
    }

    public UserProfileViewModelBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public UserProfileViewModelBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public UserProfileViewModelBuilder withTotalFootprintsDead(String totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
