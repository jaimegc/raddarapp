package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;

import java.util.Set;

public class MyUserProfileViewModelBuilder {

    private String key;
    private String name;
    private String surname;
    private String username;
    private String image;
    private String imageCache;
    private String audio;
    private String audioCache;
    private String email;
    private long range;
    private String followers;
    private String following;
    private String totalFootprints;
    private long userCompliments;
    private String level;
    private int percentage;
    private int rangeMined;
    private int rangeNotMined;
    private String miningDate;
    private int rangeMinedLocalAccumulated;
    private int gender;
    private String birthdate;
    private int role;
    private int status;
    private Set<String> notificationTopics;
    private String notificationToken;
    private long totalLikes;
    private long totalDislikes;
    private String totalFootprintsDead;
    private String mobileLanguage;

    public MyUserProfileViewModelBuilder() {
    }

    public MyUserProfileViewModel build() {
        return new MyUserProfileViewModel(key, name, surname, username, image, imageCache, audio, audioCache, email, range, followers,
            following, totalFootprints, userCompliments, level, percentage, rangeMined, rangeNotMined, miningDate, rangeMinedLocalAccumulated,
            gender, birthdate, role, status, notificationTopics, notificationToken, totalLikes, totalDislikes, totalFootprintsDead, mobileLanguage);
    }

    public MyUserProfileViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyUserProfileViewModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MyUserProfileViewModelBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public MyUserProfileViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserProfileViewModelBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public MyUserProfileViewModelBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyUserProfileViewModelBuilder withImageCache(String imageCache) {
        this.imageCache = imageCache;
        return this;
    }

    public MyUserProfileViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyUserProfileViewModelBuilder withAudioCache(String audioCache) {
        this.audioCache = audioCache;
        return this;
    }

    public MyUserProfileViewModelBuilder withRange(long range) {
        this.range = range;
        return this;
    }

    public MyUserProfileViewModelBuilder withFollowers(String followers) {
        this.followers = followers;
        return this;
    }

    public MyUserProfileViewModelBuilder withFollowing(String following) {
        this.following = following;
        return this;
    }

    public MyUserProfileViewModelBuilder withTotalFootprints(String totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public MyUserProfileViewModelBuilder withUserCompliments(long userCompliments) {
        this.userCompliments = userCompliments;
        return this;
    }

    public MyUserProfileViewModelBuilder withLevel(String level) {
        this.level = level;
        return this;
    }

    public MyUserProfileViewModelBuilder withPercentage(int percentage) {
        this.percentage = percentage;
        return this;
    }

    public MyUserProfileViewModelBuilder withRangeMined(int rangeMined) {
        this.rangeMined = rangeMined;
        return this;
    }

    public MyUserProfileViewModelBuilder withRangeNotMined(int rangeNotMined) {
        this.rangeNotMined = rangeNotMined;
        return this;
    }

    public MyUserProfileViewModelBuilder withMiningDate(String miningDate) {
        this.miningDate = miningDate;
        return this;
    }

    public MyUserProfileViewModelBuilder withRangeMinedLocalAccumulated(int rangeMinedLocalAccumulated) {
        this.rangeMinedLocalAccumulated = rangeMinedLocalAccumulated;
        return this;
    }

    public MyUserProfileViewModelBuilder withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public MyUserProfileViewModelBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public MyUserProfileViewModelBuilder withRole(int role) {
        this.role = role;
        return this;
    }

    public MyUserProfileViewModelBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public MyUserProfileViewModelBuilder withNotificationTopics(Set<String> notificationTopics) {
        this.notificationTopics = notificationTopics;
        return this;
    }

    public MyUserProfileViewModelBuilder withNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
        return this;
    }

    public MyUserProfileViewModelBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public MyUserProfileViewModelBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public MyUserProfileViewModelBuilder withTotalFootprintsDead(String totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }

    public MyUserProfileViewModelBuilder withMobileLanguage(String mobileLanguage) {
        this.mobileLanguage = mobileLanguage;
        return this;
    }
}
