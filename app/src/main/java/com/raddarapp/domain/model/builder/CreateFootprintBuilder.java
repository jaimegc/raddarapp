package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.RaddarLocation;

public class CreateFootprintBuilder {

    private String key;
    private String mediaName;
    private String title;
    private String description;
    private int mediaType;
    private int visibility;
    private int aspectRatio;
    private int sponsored;
    private int category;
    private String currentZoneName;
    private String currentZoneParentName;
    private String countryEmoji;
    private int type;
    private boolean visible;

    private RaddarLocation raddarLocation;

    public CreateFootprintBuilder() {}

    public CreateFootprint build() {
        return new CreateFootprint(key, mediaName, title, description, mediaType, visibility, aspectRatio,
            sponsored, category, currentZoneName, currentZoneParentName, countryEmoji, type, visible, raddarLocation);
    }

    public CreateFootprintBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public CreateFootprintBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }

    public CreateFootprintBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateFootprintBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateFootprintBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public CreateFootprintBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public CreateFootprintBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public CreateFootprintBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public CreateFootprintBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public CreateFootprintBuilder withCurrentZoneName(String currentZoneName) {
        this.currentZoneName = currentZoneName;
        return this;
    }

    public CreateFootprintBuilder withCurrentZoneParentName(String currentZoneParentName) {
        this.currentZoneParentName = currentZoneParentName;
        return this;
    }

    public CreateFootprintBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public CreateFootprintBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public CreateFootprintBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public CreateFootprintBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }
}
