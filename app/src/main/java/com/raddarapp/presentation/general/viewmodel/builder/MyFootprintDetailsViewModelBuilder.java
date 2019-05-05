package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyFootprintDetailsViewModel;

public class MyFootprintDetailsViewModelBuilder {

    private String key;
    private String title;
    private String description;
    private String media;
    private String userKey;
    private String username;
    private String userImage;
    private long userRange;
    private String userFollowers;
    private String userFollowing;
    private String userLevel;
    private String userTotalFootprints;
    private String creationMoment;
    private int mediaType;
    private int visibility;
    private int aspectRatio;
    private int sponsored;
    private String views;
    private long comments;
    private long likes;
    private long dislikes;
    private long scope;
    private int category;
    private String captures;
    private double latitude;
    private double longitude;
    private int type;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;

    public MyFootprintDetailsViewModelBuilder() {}

    public MyFootprintDetailsViewModel build() {
        return new MyFootprintDetailsViewModel(key, title, description, media, userKey, username, userImage, userRange,
                userFollowers, userFollowing, userLevel, userTotalFootprints, creationMoment, mediaType, visibility,
                aspectRatio, sponsored, views, comments, likes, dislikes, scope, category, captures, latitude, longitude,
                type, visible, zoneName, parentZoneName, countryEmoji);
    }

    public MyFootprintDetailsViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserRange(long userRange) {
        this.userRange = userRange;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserFollowing(String userFollowing) {
        this.userFollowing = userFollowing;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withUserTotalFootprints(String userTotalFootprints) {
        this.userTotalFootprints = userTotalFootprints;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withViews(String views) {
        this.views = views;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withCaptures(String captures) {
        this.captures = captures;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintDetailsViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
