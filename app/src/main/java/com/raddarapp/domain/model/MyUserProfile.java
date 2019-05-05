package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

import java.util.Set;

public class MyUserProfile implements Identifiable<String> {

    private final String key;
    private final String name;
    private final String surname;
    private final String username;
    private final String image;
    private final String imageCache;
    private final String audio;
    private final String audioCache;
    private final String email;
    private final long range;
    private final long followers;
    private final long following;
    private final long totalFootprints;
    private final long userCompliments;
    private final int level;
    private final int percentage;
    private final String accessToken;
    private final String refreshToken;
    private final long refreshTokenUpdated;
    private final int rangeMined;
    private final int rangeNotMined;
    private final String miningDate;
    // Not final because it's a local param and we need to modify it
    private int rangeMinedLocalAccumulated;
    private final int gender;
    private final String birthdate;
    private final String myPromoCode;
    private final int role;
    private final int status;
    private final Set<String> notificationTopics;
    private final String notificationToken;
    private final long totalLikes;
    private final long totalDislikes;
    private final long totalFootprintsDead;
    private final String mobileLanguage;

    public MyUserProfile(String key, String name, String surname, String username, String image, String imageCache, String audio,
        String audioCache, String email, long range, long followers, long following, long totalFootprints,
        long userCompliments, int level, int percentage, String accessToken, String refreshToken, long refreshTokenUpdated,
        int rangeMined, int rangeNotMined, String miningDate, int rangeMinedLocalAccumulated, int gender, String birthdate,
        String myPromoCode, int role, int status, Set<String> notificationTopics, String notificationToken, long totalLikes,
        long totalDislikes, long totalFootprintsDead, String mobileLanguage) {

        this.key = key;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.image = image;
        this.imageCache = imageCache;
        this.audio = audio;
        this.audioCache = audioCache;
        this.email = email;
        this.range = range;
        this.followers = followers;
        this.following = following;
        this.totalFootprints = totalFootprints;
        this.userCompliments = userCompliments;
        this.level = level;
        this.percentage = percentage;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenUpdated = refreshTokenUpdated;
        this.rangeMined = rangeMined;
        this.rangeNotMined = rangeNotMined;
        this.miningDate = miningDate;
        this.rangeMinedLocalAccumulated = rangeMinedLocalAccumulated;
        this.gender = gender;
        this.birthdate = birthdate;
        this.myPromoCode = myPromoCode;
        this.role = role;
        this.status = status;
        this.notificationTopics = notificationTopics;
        this.notificationToken = notificationToken;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
        this.mobileLanguage = mobileLanguage;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getImage() {
        return image;
    }

    public String getImageCache() {
        return imageCache;
    }

    public String getAudio() {
        return audio;
    }

    public String getAudioCache() {
        return audioCache;
    }

    public String getEmail() {
        return email;
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

    public long getTotalFootprints() {
        return totalFootprints;
    }

    public long getUserCompliments() {
        return userCompliments;
    }

    public int getLevel() {
        return level;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getRefreshTokenUpdated() {
        return refreshTokenUpdated;
    }

    public int getRangeMined() {
        return rangeMined;
    }

    public int getRangeNotMined() {
        return rangeNotMined;
    }

    public String getMiningDate() {
        return miningDate;
    }

    public int getRangeMinedLocalAccumulated() {
        return rangeMinedLocalAccumulated;
    }

    public void setRangeMinedLocalAccumulated(int rangeMinedLocalAccumulated) {
        this.rangeMinedLocalAccumulated = rangeMinedLocalAccumulated;
    }

    public int getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getMyPromoCode() {
        return myPromoCode;
    }

    public int getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }

    public Set<String> getNotificationTopics() {
        return notificationTopics;
    }

    public String getNotificationToken() {
        return notificationToken;
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

    public String getMobileLanguage() {
        return mobileLanguage;
    }
}