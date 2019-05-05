package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;

public class MyUserRankingViewModel implements MyUserRankingViewModelContract {

    private final String key;
    private final String username;
    private final String image;
    private final String audio;
    private final String range;
    private final String totalScopeZone;
    private final String totalFootprintsZone;
    private final String level;
    private final int userRelationship;
    private final long totalLikesZone;
    private final long totalDislikesZone;
    private final long totalLikes;
    private final long totalDislikes;
    private final String totalFootprintsDead;

    public MyUserRankingViewModel(String key, String username, String image, String audio, String range,
            String totalScopeZone, String totalFootprintsZone, String level, int userRelationship,
            long totalLikesZone, long totalDislikesZone, long totalLikes, long totalDislikes, String totalFootprintsDead) {
        this.key = key;
        this.username = username;
        this.image = image;
        this.audio = audio;
        this.range = range;
        this.totalScopeZone = totalScopeZone;
        this.totalFootprintsZone = totalFootprintsZone;
        this.level = level;
        this.userRelationship = userRelationship;
        this.totalLikesZone = totalLikesZone;
        this.totalDislikesZone = totalDislikesZone;
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

    public String getRange() {
        return range;
    }

    public String getTotalScopeZone() {
        return totalScopeZone;
    }

    public String getTotalFootprintsZone() {
        return totalFootprintsZone;
    }

    public String getLevel() {
        return level;
    }

    public int getUserRelationship() {
        return userRelationship;
    }

    public long getTotalLikesZone() {
        return totalLikesZone;
    }

    public long getTotalDislikesZone() {
        return totalDislikesZone;
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
