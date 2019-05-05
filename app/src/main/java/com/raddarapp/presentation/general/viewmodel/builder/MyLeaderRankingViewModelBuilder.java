package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;

public class MyLeaderRankingViewModelBuilder {

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

    public MyLeaderRankingViewModelBuilder() {
    }

    public MyLeaderRankingViewModel build() {
        return new MyLeaderRankingViewModel(key, username, image, audio, range, totalScopeZone,
                totalFootprintsZone, level, userRelationship, totalLikesZone, totalDislikesZone);
    }

    public MyLeaderRankingViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withRange(String range) {
        this.range = range;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withLevel(String level) {
        this.level = level;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withTotalScopeZone(String totalScopeZone) {
        this.totalScopeZone = totalScopeZone;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withTotalFootprintsZone(String totalFootprintsZone) {
        this.totalFootprintsZone = totalFootprintsZone;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withTotalLikesZone(long totalLikesZone) {
        this.totalLikesZone = totalLikesZone;
        return this;
    }

    public MyLeaderRankingViewModelBuilder withTotalDislikesZone(long totalDislikesZone) {
        this.totalDislikesZone = totalDislikesZone;
        return this;
    }
}
