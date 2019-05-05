package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

public class UserFootprintViewModel implements UserFootprintViewModelContract {

    private final String key;
    private final String title;
    private final String media;
    private final String creationMoment;
    private final int mediaType;
    private final int visibility;
    private final int aspectRatio;
    private final int sponsored;
    private long comments;
    private final long likes;
    private final long dislikes;
    private final long scope;
    private final int category;
    private final int level;
    private final double latitude;
    private final double longitude;
    private final int type;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;
    private final int status;
    private final long totalLikes;
    private final long totalDislikes;
    private final String totalFootprintsDead;

    public UserFootprintViewModel(String key, String title, String media, String creationMoment,
            int mediaType, int visibility, int aspectRatio, int sponsored, long comments, long likes, long dislikes,
            long scope, int category, int level, double latitude, double longitude, int type, boolean visible,
            String zoneName, String parentZoneName, String countryEmoji, int status, long totalLikes,
            long totalDislikes, String totalFootprintsDead) {
        this.key = key;
        this.title = title;
        this.media = media;
        this.creationMoment = creationMoment;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.aspectRatio = aspectRatio;
        this.sponsored = sponsored;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.scope = scope;
        this.category = category;
        this.level = level;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.visible = visible;
        this.zoneName = zoneName;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
        this.status = status;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
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

    public int getSponsored() {
        return sponsored;
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

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void updateComments(int totalNewComments) {
        this.comments += totalNewComments;
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

    public long getTotalLikes() {
        return totalLikes;
    }

    public long getTotalDislikes() {
        return totalDislikes;
    }

    public String getTotalFootprintsDead() {
        return totalFootprintsDead;
    }
}
