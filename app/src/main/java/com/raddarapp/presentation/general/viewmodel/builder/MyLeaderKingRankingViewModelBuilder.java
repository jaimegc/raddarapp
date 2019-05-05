package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;

public class MyLeaderKingRankingViewModelBuilder {

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

    public MyLeaderKingRankingViewModelBuilder() {
    }

    public MyLeaderKingRankingViewModel build() {
        return new MyLeaderKingRankingViewModel(key, username, image, audio, range, totalScopeZone,
                totalFootprintsZone, level, userRelationship, totalLikesZone, totalDislikesZone);
    }

    public MyLeaderKingRankingViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withRange(String range) {
        this.range = range;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withLevel(String level) {
        this.level = level;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withTotalScopeZone(String totalScopeZone) {
        this.totalScopeZone = totalScopeZone;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withTotalFootprintsZone(String totalFootprintsZone) {
        this.totalFootprintsZone = totalFootprintsZone;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withTotalLikesZone(long totalLikesZone) {
        this.totalLikesZone = totalLikesZone;
        return this;
    }

    public MyLeaderKingRankingViewModelBuilder withTotalDislikesZone(long totalDislikesZone) {
        this.totalDislikesZone = totalDislikesZone;
        return this;
    }
}
