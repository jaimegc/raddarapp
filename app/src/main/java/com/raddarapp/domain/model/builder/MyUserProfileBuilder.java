package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyUserProfile;

import java.util.Set;

public class MyUserProfileBuilder {

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
    private long followers;
    private long following;
    private long totalFootprints;
    private long userCompliments;
    private int level;
    private int percentage;
    private String accessToken;
    private String refreshToken;
    private long refreshTokenUpdated;
    private int rangeMined;
    private int rangeNotMined;
    private String miningDate;
    private int rangeMinedLocalAccumulated;
    private int gender;
    private String birthdate;
    private String myPromoCode;
    private int role;
    private int status;
    private Set<String> notificationTopics;
    private String notificationToken;
    private long totalLikes;
    private long totalDislikes;
    private long totalFootprintsDead;
    private String mobileLanguage;

    public MyUserProfileBuilder() {
    }

    public MyUserProfile build() {
        return new MyUserProfile(key, name, surname, username, image, imageCache, audio, audioCache, email, range,
                followers, following, totalFootprints, userCompliments, level, percentage, accessToken, refreshToken,
                refreshTokenUpdated, rangeMined, rangeNotMined, miningDate, rangeMinedLocalAccumulated, gender, birthdate,
                myPromoCode, role, status, notificationTopics, notificationToken, totalLikes, totalDislikes, totalFootprintsDead,
                mobileLanguage);
    }

    public MyUserProfileBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyUserProfileBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MyUserProfileBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public MyUserProfileBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserProfileBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public MyUserProfileBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyUserProfileBuilder withImageCache(String imageCache) {
        this.imageCache = imageCache;
        return this;
    }

    public MyUserProfileBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyUserProfileBuilder withAudioCache(String audioCache) {
        this.audioCache = audioCache;
        return this;
    }

    public MyUserProfileBuilder withRange(long range) {
        this.range = range;
        return this;
    }

    public MyUserProfileBuilder withFollowers(long followers) {
        this.followers = followers;
        return this;
    }

    public MyUserProfileBuilder withFollowing(long following) {
        this.following = following;
        return this;
    }

    public MyUserProfileBuilder withUserCompliments(long userCompliments) {
        this.userCompliments = userCompliments;
        return this;
    }

    public MyUserProfileBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyUserProfileBuilder withPercentage(int percentage) {
        this.percentage = percentage;
        return this;
    }

    public MyUserProfileBuilder withTotalFootprints(long totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public MyUserProfileBuilder withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public MyUserProfileBuilder withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public MyUserProfileBuilder withRefreshTokenUpdated(long refreshTokenUpdated) {
        this.refreshTokenUpdated = refreshTokenUpdated;
        return this;
    }

    public MyUserProfileBuilder withRangeMined(int rangeMined) {
        this.rangeMined = rangeMined;
        return this;
    }

    public MyUserProfileBuilder withRangeNotMined(int rangeNotMined) {
        this.rangeNotMined = rangeNotMined;
        return this;
    }

    public MyUserProfileBuilder withMiningDate(String miningDate) {
        this.miningDate = miningDate;
        return this;
    }

    public MyUserProfileBuilder withRangeMinedLocalAccumulated(int rangeMinedLocalAccumulated) {
        this.rangeMinedLocalAccumulated = rangeMinedLocalAccumulated;
        return this;
    }

    public MyUserProfileBuilder withGender(int gender) {
        this.gender = gender;
        return this;
    }

    public MyUserProfileBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public MyUserProfileBuilder withMyPromoCode(String myPromoCode) {
        this.myPromoCode = myPromoCode;
        return this;
    }

    public MyUserProfileBuilder withRole(int role) {
        this.role = role;
        return this;
    }

    public MyUserProfileBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public MyUserProfileBuilder withNotificationTopics(Set<String> notificationTopics) {
        this.notificationTopics = notificationTopics;
        return this;
    }

    public MyUserProfileBuilder withNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
        return this;
    }

    public MyUserProfileBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public MyUserProfileBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public MyUserProfileBuilder withTotalFootprintsDead(long totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }

    public MyUserProfileBuilder withMobileLanguage(String mobileLanguage) {
        this.mobileLanguage = mobileLanguage;
        return this;
    }
}
