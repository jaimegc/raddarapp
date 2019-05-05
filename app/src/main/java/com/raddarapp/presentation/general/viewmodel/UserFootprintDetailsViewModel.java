package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintDetailsViewModelContract;

public class UserFootprintDetailsViewModel implements UserFootprintDetailsViewModelContract {

    private final String key;
    private final String title;
    private final String media;
    private final String description;
    private final String views;
    private long comments;
    private final long likes;
    private final long dislikes;
    private final String creationMoment;
    private final int aspectRatio;
    private final long scope;
    private final double latitude;
    private final double longitude;
    private final int category;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;

    public UserFootprintDetailsViewModel(String key, String media, String title, String description, String views,
            long comments, long likes, long dislikes, String creationMoment, int aspectRatio, long scope,
            double latitude, double longitude, int category, boolean visible, String zoneName, String parentZoneName,
            String countryEmoji) {
        this.key = key;
        this.media = media;
        this.title = title;
        this.description = description;
        this.views = views;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creationMoment = creationMoment;
        this.aspectRatio = aspectRatio;
        this.scope = scope;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.visible = visible;
        this.zoneName = zoneName;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
    }

    public String getKey() {
        return key;
    }

    public String getMedia() {
        return media;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getViews() {
        return views;
    }

    public long getComments() {
        return comments;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public String getCreationMoment() {
        return creationMoment;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public long getScope() {
        return scope;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCategory() {
        return category;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getZoneName() {
        return zoneName;
    }

    public String getParentZoneName() {
        return parentZoneName;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }
}
