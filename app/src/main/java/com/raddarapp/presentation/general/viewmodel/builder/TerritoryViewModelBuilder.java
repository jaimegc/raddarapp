package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;

public class TerritoryViewModelBuilder {

    private String key;
    private String name;
    private String parentKey;
    private String parentName;
    private String area;
    private String totalFootprints;
    private String emojiCountry;
    private boolean hasSons;

    private String leaderKey;
    private String leaderUsername;
    private String leaderImage;
    private String leaderFollowers;
    private String leaderRange;
    private String leaderLevel;
    private String leaderAudio;
    private int leaderRelationship;

    public TerritoryViewModelBuilder() {}

    public TerritoryViewModel build() {
        return new TerritoryViewModel(key, name, parentKey, parentName, area, totalFootprints, emojiCountry,
            hasSons, leaderKey, leaderUsername, leaderImage, leaderFollowers, leaderRange, leaderLevel,
            leaderAudio, leaderRelationship);
    }

    public TerritoryViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public TerritoryViewModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TerritoryViewModelBuilder withParentKey(String parentKey) {
        this.parentKey = parentKey;
        return this;
    }

    public TerritoryViewModelBuilder withParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public TerritoryViewModelBuilder withArea(String area) {
        this.area = area;
        return this;
    }

    public TerritoryViewModelBuilder withTotalFootprints(String totalFootprints) {
        this.totalFootprints = totalFootprints;
        return this;
    }

    public TerritoryViewModelBuilder withEmojiCountry(String emojiCountry) {
        this.emojiCountry = emojiCountry;
        return this;
    }

    public TerritoryViewModelBuilder withHasSons(boolean hasSons) {
        this.hasSons = hasSons;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderKey(String leaderKey) {
        this.leaderKey = leaderKey;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderUsername(String leaderUsername) {
        this.leaderUsername = leaderUsername;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderImage(String leaderImage) {
        this.leaderImage = leaderImage;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderFollowers(String leaderFollowers) {
        this.leaderFollowers = leaderFollowers;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderRange(String leaderRange) {
        this.leaderRange = leaderRange;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderLevel(String leaderLevel) {
        this.leaderLevel = leaderLevel;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderAudio(String leaderAudio) {
        this.leaderAudio = leaderAudio;
        return this;
    }

    public TerritoryViewModelBuilder withLeaderRelationship(int leaderRelationship) {
        this.leaderRelationship = leaderRelationship;
        return this;
    }
}
