package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.CreateFootprintViewModelContract;

public class CreateFootprintViewModel implements CreateFootprintViewModelContract {

    private final String key;
    private final String title;
    private final String media;
    private final String description;
    private final int mediaType;
    private final int visibility;
    private final int aspectRatio;
    private final double latitude;
    private final double longitude;
    private final String currentZoneName;
    private final String currentZoneParentName;
    private final String creationMoment;
    private final String countryEmoji;
    private final int category;
    private final int type;
    private final boolean visible;

    public CreateFootprintViewModel(String key, String title, String media, String description, int mediaType,
            int visibility, int aspectRatio, double latitude, double longitude, String currentZoneName,
            String currentZoneParentName, String creationMoment, String countryEmoji, int category, int type, boolean visible) {
        this.key = key;
        this.title = title;
        this.media = media;
        this.description = description;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.aspectRatio = aspectRatio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentZoneName = currentZoneName;
        this.currentZoneParentName = currentZoneParentName;
        this.creationMoment = creationMoment;
        this.countryEmoji = countryEmoji;
        this.category = category;
        this.type = type;
        this.visible = visible;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getMedia() {
        return media;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationMoment() {
        return creationMoment;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
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

    public int getCategory() {
        return category;
    }

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }
}
