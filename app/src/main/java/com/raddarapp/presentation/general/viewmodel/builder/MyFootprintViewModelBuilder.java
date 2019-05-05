package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.MyFootprintViewModel;

public class MyFootprintViewModelBuilder {

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

    public MyFootprintViewModelBuilder() {}

    public MyFootprintViewModel build() {
        return new MyFootprintViewModel(key, title, media, creationMoment, mediaType, visibility,
                aspectRatio, sponsored, comments, likes, dislikes, scope, category, level, latitude, longitude,
                type, visible, zoneName, parentZoneName, countryEmoji, status);
    }

    public MyFootprintViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public MyFootprintViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MyFootprintViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public MyFootprintViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public MyFootprintViewModelBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public MyFootprintViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintViewModelBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyFootprintViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MyFootprintViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public MyFootprintViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public MyFootprintViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public MyFootprintViewModelBuilder withStatus(int status) {
        this.status = status;
        return this;
    }
}
