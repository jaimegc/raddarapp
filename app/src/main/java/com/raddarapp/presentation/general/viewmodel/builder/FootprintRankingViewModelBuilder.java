package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.FootprintRankingViewModel;

public class FootprintRankingViewModelBuilder {

    private String key;
    private String title;
    private String media;
    private String userKey;
    private String username;
    private String userImage;
    private String userLevel;
    private int userRelationship;
    private String creationMoment;
    private int mediaType;
    private int visibility;
    private int sponsored;
    private long comments;
    private long scope;
    private int category;
    private int level;
    private double latitude;
    private double longitude;
    private String audio;
    private long likes;
    private long dislikes;
    private int type;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;

    private boolean powerSelected;

    public FootprintRankingViewModelBuilder() {}

    public FootprintRankingViewModel build() {
        return new FootprintRankingViewModel(key, title, media, userKey, username, userImage, userLevel,
            userRelationship, creationMoment, mediaType, visibility, sponsored, comments, scope, category,
            level, latitude, longitude, audio, likes, dislikes, type, visible, zoneName, parentZoneName,
            countryEmoji, powerSelected);
    }

    public FootprintRankingViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintRankingViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintRankingViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintRankingViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public FootprintRankingViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public FootprintRankingViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public FootprintRankingViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public FootprintRankingViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public FootprintRankingViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public FootprintRankingViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FootprintRankingViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public FootprintRankingViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public FootprintRankingViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintRankingViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintRankingViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintRankingViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public FootprintRankingViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FootprintRankingViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public FootprintRankingViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public FootprintRankingViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintRankingViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintRankingViewModelBuilder withPowerSelected(boolean powerSelected) {
        this.powerSelected = powerSelected;
        return this;
    }

    public FootprintRankingViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public FootprintRankingViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintRankingViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintRankingViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintRankingViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
