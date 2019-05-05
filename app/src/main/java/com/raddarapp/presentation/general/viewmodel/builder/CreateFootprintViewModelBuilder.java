package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.CreateFootprintViewModel;

public class CreateFootprintViewModelBuilder {

    private String key;
    private String title;
    private String media;
    private String description;
    private int mediaType;
    private int visibility;
    private int aspectRatio;
    private double latitude;
    private double longitude;
    private String currentZoneName;
    private String currentZoneParentName;
    private String creationMoment;
    private String countryEmoji;
    private int category;
    private int type;
    private boolean visible;

    public CreateFootprintViewModelBuilder() {}

    public CreateFootprintViewModel build() {
        return new CreateFootprintViewModel(key, title, media, description, mediaType, visibility, aspectRatio,
                latitude, longitude, currentZoneName, currentZoneParentName, creationMoment, countryEmoji, category,
                type, visible);
    }

    public CreateFootprintViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CreateFootprintViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateFootprintViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public CreateFootprintViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateFootprintViewModelBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public CreateFootprintViewModelBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public CreateFootprintViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public CreateFootprintViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public CreateFootprintViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public CreateFootprintViewModelBuilder withCurrentZoneName(String currentZoneName) {
        this.currentZoneName = currentZoneName;
        return this;
    }

    public CreateFootprintViewModelBuilder withCurrentZoneParentName(String currentZoneParentName) {
        this.currentZoneParentName = currentZoneParentName;
        return this;
    }

    public CreateFootprintViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public CreateFootprintViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public CreateFootprintViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public CreateFootprintViewModelBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public CreateFootprintViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }
}
