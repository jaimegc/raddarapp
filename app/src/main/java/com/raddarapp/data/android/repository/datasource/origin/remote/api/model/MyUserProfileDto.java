package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class MyUserProfileDto {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("username")
    private String username;
    @SerializedName("profileImage")
    private String image;
    @SerializedName("profileAudio")
    private String audio;
    @SerializedName("email")
    private String email;
    @SerializedName("range")
    private long range;
    @SerializedName("followers")
    private long followers;
    @SerializedName("followings")
    private long following;
    @SerializedName("totalFootPrints")
    private long totalFootprints;
    @SerializedName("userCompliments")
    private long userCompliments;
    @SerializedName("level")
    private int level;
    @SerializedName("percentage")
    private int percentage;
    @SerializedName("accessToken")
    private String accessToken;
    @SerializedName("refreshToken")
    private String refreshToken;
    @SerializedName("rangeMined")
    private int rangeMined;
    @SerializedName("rangeNotMined")
    private int rangeNotMined;
    @SerializedName("miningDate")
    private String miningDate;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birthday")
    private String birthdate;
    @SerializedName("role")
    private int role;
    @SerializedName("status")
    private int status;
    @SerializedName("notificationTopics")
    private Set<String> notificationTopics;
    @SerializedName("notificationToken")
    private String notificationToken;
    @SerializedName("totalLikes")
    private long totalLikes;
    @SerializedName("totalDislikes")
    private long totalDislikes;
    @SerializedName("totalFootprintsDead")
    private long totalFootprintsDead;
    @SerializedName("mobileLanguage")
    private String mobileLanguage;

    public String getId() {
        return id;
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

    public String getAudio() {
        return audio;
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

    public int getRangeMined() {
        return rangeMined;
    }

    public int getRangeNotMined() {
        return rangeNotMined;
    }

    public String getMiningDate() {
        return miningDate;
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

    public long getTotalFootprintsDead() {
        return totalFootprintsDead;
    }

    public String getMobileLanguage() {
        return mobileLanguage;
    }
}
