package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.UserFootprintDetailsViewModel;

public class UserFootprintDetailsViewModelBuilder {

    private String key;
    private String media;
    private String title;
    private String description;
    private String views;
    private long comments;
    private long likes;
    private long dislikes;
    private String creationMoment;
    private int aspectRatio;
    private long scope;
    private double latitude;
    private double longitude;
    private int category;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;

    public UserFootprintDetailsViewModelBuilder() {}

    public UserFootprintDetailsViewModel build() {
        return new UserFootprintDetailsViewModel(key, media, title, description, views, comments,
                likes, dislikes, creationMoment, aspectRatio, scope, latitude, longitude, category, visible,
                zoneName, parentZoneName, countryEmoji);
    }

    public UserFootprintDetailsViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withViews(String views) {
        this.views = views;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public UserFootprintDetailsViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
