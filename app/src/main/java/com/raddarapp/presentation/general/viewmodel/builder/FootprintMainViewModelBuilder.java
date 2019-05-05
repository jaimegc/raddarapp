package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;

public class FootprintMainViewModelBuilder {

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

    public FootprintMainViewModelBuilder() {}

    public FootprintMainViewModel build() {
        return new FootprintMainViewModel(key, title, media, userKey, username, userImage, userLevel,
            userRelationship, creationMoment, mediaType, visibility, sponsored, comments, scope, category,
            level, latitude, longitude, audio, likes, dislikes, type, visible, zoneName, parentZoneName,
            countryEmoji, powerSelected);
    }

    public FootprintMainViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintMainViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintMainViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintMainViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public FootprintMainViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public FootprintMainViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public FootprintMainViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public FootprintMainViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public FootprintMainViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public FootprintMainViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FootprintMainViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public FootprintMainViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public FootprintMainViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintMainViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintMainViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintMainViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public FootprintMainViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FootprintMainViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public FootprintMainViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public FootprintMainViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintMainViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintMainViewModelBuilder withPowerSelected(boolean powerSelected) {
        this.powerSelected = powerSelected;
        return this;
    }

    public FootprintMainViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public FootprintMainViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintMainViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintMainViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintMainViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
