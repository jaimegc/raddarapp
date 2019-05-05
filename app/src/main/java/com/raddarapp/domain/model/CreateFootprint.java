package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class CreateFootprint implements Identifiable<String> {

    private final String key;
    private final String mediaName;
    private final String title;
    private final String description;
    private final int mediaType;
    private final int visibility;
    private final int aspectRatio;
    private final int sponsored;
    private final int category;
    private final String currentZoneName;
    private final String currentZoneParentName;
    private final String countryEmoji;
    private final int type;
    private final boolean visible;

    private final RaddarLocation raddarLocation;

    public CreateFootprint(String key, String mediaName, String title, String description, int mediaType,
            int visibility, int aspectRatio, int sponsored, int category, String currentZoneName,
            String currentZoneParentName, String countryEmoji, int type, boolean visible, RaddarLocation raddarLocation) {
        this.key = key;
        this.mediaName = mediaName;
        this.title = title;
        this.description = description;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.aspectRatio = aspectRatio;
        this.sponsored = sponsored;
        this.category = category;
        this.currentZoneName = currentZoneName;
        this.currentZoneParentName = currentZoneParentName;
        this.countryEmoji = countryEmoji;
        this.type = type;
        this.visible = visible;
        this.raddarLocation = raddarLocation;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getMediaName() {
        return mediaName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getMediaType() {
        return mediaType;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public int getSponsored() {
        return sponsored;
    }

    public int getCategory() {
        return category;
    }

    public String getCurrentZoneName() {
        return currentZoneName;
    }

    public String getCurrentZoneParentName() {
        return currentZoneParentName;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public RaddarLocation getRaddarLocation() {
        return raddarLocation;
    }
}
