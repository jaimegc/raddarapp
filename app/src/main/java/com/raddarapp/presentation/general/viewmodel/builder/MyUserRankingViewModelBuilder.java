package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;

public class MyUserRankingViewModelBuilder {

    private String key;
    private String username;
    private String image;
    private String audio;
    private String range;
    private String totalScopeZone;
    private String totalFootprintsZone;
    private String level;
    private int userRelationship;
    private long totalLikesZone;
    private long totalDislikesZone;
    private long totalLikes;
    private long totalDislikes;
    private String totalFootprintsDead;

    public MyUserRankingViewModelBuilder() {
    }

    public MyUserRankingViewModel build() {
        return new MyUserRankingViewModel(key, username, image, audio, range, totalScopeZone,
                totalFootprintsZone, level, userRelationship, totalLikesZone, totalDislikesZone,
                totalLikes, totalDislikes, totalFootprintsDead);
    }

    public MyUserRankingViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyUserRankingViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyUserRankingViewModelBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyUserRankingViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyUserRankingViewModelBuilder withRange(String range) {
        this.range = range;
        return this;
    }

    public MyUserRankingViewModelBuilder withLevel(String level) {
        this.level = level;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalScopeZone(String totalScopeZone) {
        this.totalScopeZone = totalScopeZone;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalFootprintsZone(String totalFootprintsZone) {
        this.totalFootprintsZone = totalFootprintsZone;
        return this;
    }

    public MyUserRankingViewModelBuilder withRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalLikesZone(long totalLikesZone) {
        this.totalLikesZone = totalLikesZone;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalDislikesZone(long totalDislikesZone) {
        this.totalDislikesZone = totalDislikesZone;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public MyUserRankingViewModelBuilder withTotalFootprintsDead(String totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
