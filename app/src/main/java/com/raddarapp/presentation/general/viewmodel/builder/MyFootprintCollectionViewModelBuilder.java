package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionViewModel;

public class MyFootprintCollectionViewModelBuilder {

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
    private int status;

    private boolean powerSelected;

    public MyFootprintCollectionViewModelBuilder() {}

    public MyFootprintCollectionViewModel build() {
        return new MyFootprintCollectionViewModel(key, title, media, userKey, username, userImage, userLevel,
            userRelationship, creationMoment, mediaType, visibility, sponsored, comments, scope, category,
            level, latitude, longitude, audio, likes, dislikes, type, visible, zoneName, parentZoneName, countryEmoji,
            status, powerSelected);
    }

    public MyFootprintCollectionViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withPowerSelected(boolean powerSelected) {
        this.powerSelected = powerSelected;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public MyFootprintCollectionViewModelBuilder withStatus(int status) {
        this.status = status;
        return this;
    }
}
