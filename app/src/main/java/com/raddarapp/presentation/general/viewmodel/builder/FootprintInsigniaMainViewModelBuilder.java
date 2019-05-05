package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;

public class FootprintInsigniaMainViewModelBuilder {

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

    public FootprintInsigniaMainViewModelBuilder() {}

    public FootprintInsigniaMainViewModel build() {
        return new FootprintInsigniaMainViewModel(key, title, media, userKey, username, userImage, userLevel,
            userRelationship, creationMoment, mediaType, visibility, sponsored, comments, scope, category,
            level, latitude, longitude, audio, likes, dislikes, type, visible, zoneName, parentZoneName, countryEmoji,
            powerSelected);
    }

    public FootprintInsigniaMainViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withPowerSelected(boolean powerSelected) {
        this.powerSelected = powerSelected;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintInsigniaMainViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
