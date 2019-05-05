package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.TerritoryViewModelContract;

public class TerritoryViewModel implements TerritoryViewModelContract {

    private final String key;
    private final String name;
    private final String parentKey;
    private final String parentName;
    private final String area;
    private final String totalFootprints;
    private final String emojiCountry;
    private final boolean hasSons;

    private final String leaderKey;
    private final String leadername;
    private final String leaderImage;
    private final String leaderFollowers;
    private final String leaderRange;
    private final String leaderLevel;
    private final String leaderAudio;
    private final int leaderRelationship;

    public TerritoryViewModel(String key, String name, String parentKey, String parentName, String area, String totalFootprints,
            String emojiCountry, boolean hasSons, String leaderKey, String leadername, String leaderImage, String leaderFollowers,
            String leaderRange, String leaderLevel, String leaderAudio, int leaderRelationship) {
        this.key = key;
        this.name = name;
        this.parentKey = parentKey;
        this.parentName = parentName;
        this.area = area;
        this.totalFootprints = totalFootprints;
        this.emojiCountry = emojiCountry;
        this.hasSons = hasSons;
        this.leaderKey = leaderKey;
        this.leadername = leadername;
        this.leaderImage = leaderImage;
        this.leaderFollowers = leaderFollowers;
        this.leaderRange = leaderRange;
        this.leaderLevel = leaderLevel;
        this.leaderAudio = leaderAudio;
        this.leaderRelationship = leaderRelationship;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getParentKey() {
        return parentKey;
    }

    public String getParentName() {
        return parentName;
    }

    public String getArea() {
        return area;
    }

    public String getTotalFootprints() {
        return totalFootprints;
    }

    public String getEmojiCountry() {
        return emojiCountry;
    }

    public boolean hasSons() {
        return hasSons;
    }

    public String getLeaderKey() {
        return leaderKey;
    }

    public String getLeadername() {
        return leadername;
    }

    public String getLeaderImage() {
        return leaderImage;
    }

    public String getLeaderFollowers() {
        return leaderFollowers;
    }

    public String getLeaderRange() {
        return leaderRange;
    }

    public String getLeaderLevel() {
        return leaderLevel;
    }

    public String getLeaderAudio() {
        return leaderAudio;
    }

    public int getLeaderRelationship() {
        return leaderRelationship;
    }
}
