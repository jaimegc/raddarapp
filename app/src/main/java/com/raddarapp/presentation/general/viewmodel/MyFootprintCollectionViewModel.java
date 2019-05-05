package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;

public class MyFootprintCollectionViewModel implements MyFootprintCollectionViewModelContract {

    private final String key;
    private final String title;
    private final String media;
    private final String userKey;
    private final String username;
    private final String userImage;
    private final String userLevel;
    private final int userRelationship;
    private final String creationMoment;
    private final int mediaType;
    private final int visibility;
    private final int sponsored;
    private long comments;
    private final long scope;
    private final int category;
    private final int level;
    private final double latitude;
    private final double longitude;
    private final String audio;
    private final long likes;
    private final long dislikes;
    private final int type;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;
    private final int status;

    // Presentation Model
    private boolean powerSelected;

    public MyFootprintCollectionViewModel(String key, String title, String media, String userKey, String username, String userImage,
            String userLevel, int userRelationship, String creationMoment, int mediaType, int visibility, int sponsored,
            long comments, long scope, int category, int level, double latitude, double longitude, String audio,
            long likes, long dislikes, int type, boolean visible, String zoneName, String parentZoneName,
            String countryEmoji, int status, boolean powerSelected) {
        this.key = key;
        this.title = title;
        this.media = media;
        this.userKey = userKey;
        this.username = username;
        this.userImage = userImage;
        this.userRelationship = userRelationship;
        this.userLevel = userLevel;
        this.creationMoment = creationMoment;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.sponsored = sponsored;
        this.comments = comments;
        this.scope = scope;
        this.category = category;
        this.level = level;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powerSelected = powerSelected;
        this.audio = audio;
        this.likes = likes;
        this.dislikes = dislikes;
        this.type = type;
        this.visible = visible;
        this.zoneName = zoneName;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
        this.status = status;
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

    public String getUserKey() {
        return userKey;
    }

    public String getUsername() {
        return username;
    }

    public String getUserImage() {
        return userImage;
    }

    public int getUserRelationship() {
        return userRelationship;
    }

    public String getUserLevel() {
        return userLevel;
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

    public int getSponsored() {
        return sponsored;
    }

    public long getComments() {
        return comments;
    }

    public long getScope() {
        return scope;
    }

    public int getCategory() {
        return category;
    }

    public int getLevel() {
        return level;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAudio() {
        return audio;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isPowerSelected() {
        return powerSelected;
    }

    public void updatePowerSelected(boolean powerSelected) {
        this.powerSelected = powerSelected;
    }

    public void updateComments(long totalComments) {
        this.comments = totalComments;
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

    public int getStatus() {
        return status;
    }
}
