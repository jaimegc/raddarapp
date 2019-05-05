package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.UserProfileDetailsViewModelContract;

public class UserProfileViewModel implements UserProfileDetailsViewModelContract {

    private final String key;
    private final String username;
    private final String image;
    private final String audio;
    private final long range;
    private final String followers;
    private final String following;
    private final String totalFootprints;
    private final long userCompliments;
    private final String level;
    private final long totalLikes;
    private final long totalDislikes;
    private final String totalFootprintsDead;

    public UserProfileViewModel(String key, String username, String image, String audio, long range, String followers,
        String following, String totalFootprints, long userCompliments, String level, long totalLikes, long totalDislikes,
        String totalFootprintsDead) {
        this.key = key;
        this.username = username;
        this.image = image;
        this.range = range;
        this.followers = followers;
        this.following = following;
        this.level = level;
        this.totalFootprints = totalFootprints;
        this.audio = audio;
        this.userCompliments = userCompliments;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
    }


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

    public long getTotalLikes() {
        return totalLikes;
    }

    public long getTotalDislikes() {
        return totalDislikes;
    }

    public String getTotalFootprintsDead() {
        return totalFootprintsDead;
    }
}
