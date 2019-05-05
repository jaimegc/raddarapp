package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;

public class CreateFootprintDtoBuilder {

    private String id;
    private String mediaName;
    private String title;
    private String description;
    private int mediaType;
    private int visibility;
    private int aspectRatio;
    private boolean sponsored;
    private int category;
    private String currentZoneName;
    private String currentZoneParentName;
    private String countryEmoji;
    private int type;
    private boolean visible;
    private RaddarLocationDto raddarLocationDto;

    public CreateFootprintDtoBuilder() {}

    public CreateFootprintDto build() {
        return new CreateFootprintDto(id, mediaName, title, description, mediaType, visibility, aspectRatio,
            sponsored, category, currentZoneName, currentZoneParentName, countryEmoji, type, visible, raddarLocationDto);
    }

    public CreateFootprintDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CreateFootprintDtoBuilder withMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }

    public CreateFootprintDtoBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateFootprintDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CreateFootprintDtoBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public CreateFootprintDtoBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public CreateFootprintDtoBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public CreateFootprintDtoBuilder withSponsored(boolean sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public CreateFootprintDtoBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public CreateFootprintDtoBuilder withCurrentZoneName(String currentZoneName) {
        this.currentZoneName = currentZoneName;
        return this;
    }

    public CreateFootprintDtoBuilder withCurrentZoneParentName(String currentZoneParentName) {
        this.currentZoneParentName = currentZoneParentName;
        return this;
    }

    public CreateFootprintDtoBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public CreateFootprintDtoBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public CreateFootprintDtoBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public CreateFootprintDtoBuilder withRaddarLocationDto(RaddarLocationDto raddarLocationDto) {
        this.raddarLocationDto = raddarLocationDto;
        return this;
    }
}
