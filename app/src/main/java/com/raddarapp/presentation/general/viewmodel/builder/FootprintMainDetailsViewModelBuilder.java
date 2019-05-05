package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.FootprintMainDetailsViewModel;

public class FootprintMainDetailsViewModelBuilder {

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

    public FootprintMainDetailsViewModelBuilder() {}

    public FootprintMainDetailsViewModel build() {
        return new FootprintMainDetailsViewModel(key, media, title, description, views, comments, likes, dislikes,
                userKey, username, userImage, userRange, userFollowers, userLevel, userTotalFootprints,
                userRelationship, creationMoment, aspectRatio, scope, latitude, longitude, audio, category, visible,
                zoneName, parentZoneName, countryEmoji);
    }

    public FootprintMainDetailsViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserRange(long userRange) {
        this.userRange = userRange;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserTotalFootprints(String userTotalFootprints) {
        this.userTotalFootprints = userTotalFootprints;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withViews(String views) {
        this.views = views;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintMainDetailsViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
