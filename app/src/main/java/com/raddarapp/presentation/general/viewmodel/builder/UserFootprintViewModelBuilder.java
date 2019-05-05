package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;

public class UserFootprintViewModelBuilder {

    private String key;
    private String title;
    private String media;
    private String creationMoment;
    private int mediaType;
    private int visibility;
    private int aspectRatio;
    private int sponsored;
    private long comments;
    private long likes;
    private long dislikes;
    private long scope;
    private int category;
    private int level;
    private double latitude;
    private double longitude;
    private int type;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;
    private int status;
    private long totalLikes;
    private long totalDislikes;
    private String totalFootprintsDead;

    public UserFootprintViewModelBuilder() {}

    public UserFootprintViewModel build() {
        return new UserFootprintViewModel(key, title, media, creationMoment, mediaType, visibility,
            aspectRatio, sponsored, comments, likes, dislikes, scope, category, level, latitude, longitude, type, visible,
            zoneName, parentZoneName, countryEmoji, status, totalLikes, totalDislikes, totalFootprintsDead);
    }

    public UserFootprintViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UserFootprintViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserFootprintViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public UserFootprintViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public UserFootprintViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public UserFootprintViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public UserFootprintViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public UserFootprintViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public UserFootprintViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public UserFootprintViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public UserFootprintViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public UserFootprintViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public UserFootprintViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public UserFootprintViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public UserFootprintViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserFootprintViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UserFootprintViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public UserFootprintViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public UserFootprintViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public UserFootprintViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public UserFootprintViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public UserFootprintViewModelBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public UserFootprintViewModelBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public UserFootprintViewModelBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public UserFootprintViewModelBuilder withTotalFootprintsDead(String totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
