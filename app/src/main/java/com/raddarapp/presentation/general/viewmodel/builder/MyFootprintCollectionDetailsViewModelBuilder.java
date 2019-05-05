package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionDetailsViewModel;

public class MyFootprintCollectionDetailsViewModelBuilder {

    private String key;
    private String media;
    private String title;
    private String description;
    private String views;
    private long comments;
    private long likes;
    private long dislikes;
    private String userKey;
    private String username;
    private String userImage;
    private long userRange;
    private String userFollowers;
    private String userLevel;
    private String userTotalFootprints;
    private int userRelationship;
    private String creationMoment;
    private int aspectRatio;
    private long scope;
    private double latitude;
    private double longitude;
    private String audio;
    private int category;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;

    public MyFootprintCollectionDetailsViewModelBuilder() {}

    public MyFootprintCollectionDetailsViewModel build() {
        return new MyFootprintCollectionDetailsViewModel(key, media, title, description, views, comments, likes, dislikes,
                userKey, username, userImage, userRange, userFollowers, userLevel, userTotalFootprints,
                userRelationship, creationMoment, aspectRatio, scope, latitude, longitude, audio, category, visible,
                zoneName, parentZoneName, countryEmoji);
    }

    public MyFootprintCollectionDetailsViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserRange(long userRange) {
        this.userRange = userRange;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserTotalFootprints(String userTotalFootprints) {
        this.userTotalFootprints = userTotalFootprints;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withViews(String views) {
        this.views = views;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintCollectionDetailsViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
