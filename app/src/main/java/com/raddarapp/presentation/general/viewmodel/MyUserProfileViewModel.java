package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.MyUserProfileViewModelContract;

import java.util.Set;

public class MyUserProfileViewModel implements MyUserProfileViewModelContract {

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
    private final String followers;
    private final String following;
    private final String totalFootprints;
    private final long userCompliments;
    private final String level;
    private final int percentage;
    private final int rangeMined;
    private final int rangeNotMined;
    private final String miningDate;
    private final int rangeMinedLocalAccumulated;
    private final int gender;
    private final String birthdate;
    private final int role;
    private final int status;
    private final Set<String> notificationTopics;
    private final String notificationToken;
    private final long totalLikes;
    private final long totalDislikes;
    private final String totalFootprintsDead;
    private final String mobileLanguage;

    public MyUserProfileViewModel(String key, String name, String surname, String username, String image, String imageCache,
            String audio, String audioCache, String email, long range, String followers, String following, String totalFootprints,
            long userCompliments, String level, int percentage, int rangeMined, int rangeNotMined, String miningDate, int rangeMinedLocalAccumulated,
            int gender, String birthdate, int role, int status, Set<String> notificationTopics, String notificationToken, long totalLikes,
            long totalDislikes, String totalFootprintsDead, String mobileLanguage) {
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
        this.rangeMined = rangeMined;
        this.rangeNotMined = rangeNotMined;
        this.miningDate = miningDate;
        this.rangeMinedLocalAccumulated = rangeMinedLocalAccumulated;
        this.gender = gender;
        this.birthdate = birthdate;
        this.role = role;
        this.status = status;
        this.notificationTopics = notificationTopics;
        this.notificationToken = notificationToken;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
        this.mobileLanguage = mobileLanguage;
    }

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

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
        return following;
    }

    public String getTotalFootprints() {
        return totalFootprints;
    }

    public long getUserCompliments() {
        return userCompliments;
    }

    public String getLevel() {
        return level;
    }

    public int getPercentage() {
        return percentage;
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

    public int getGender() {
        return gender;
    }

    public String getBirthdate() {
        return birthdate;
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

    public String getTotalFootprintsDead() {
        return totalFootprintsDead;
    }

    public String getMobileLanguage() {
        return mobileLanguage;
    }
}
