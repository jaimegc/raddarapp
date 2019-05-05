package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainDetailsViewModelContract;

public class FootprintMainDetailsViewModel implements FootprintMainDetailsViewModelContract {

    private final String key;
    private final String title;
    private final String media;
    private final String description;
    private final String views;
    private long comments;
    private final long likes;
    private final long dislikes;
    private final String userKey;
    private final String username;
    private final String userImage;
    private final long userRange;
    private final String userFollowers;
    private final String userLevel;
    private final String userTotalFootprints;
    private final int userRelationship;
    private final String creationMoment;
    private final int aspectRatio;
    private final long scope;
    private final double latitude;
    private final double longitude;
    private final String audio;
    private final int category;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;

    public FootprintMainDetailsViewModel(String key, String media, String title, String description, String views,
            long comments, long likes, long dislikes, String userKey, String username, String userImage,
            long userRange, String userFollowers, String userLevel, String userTotalFootprints,
            int userRelationship, String creationMoment, int aspectRatio, long scope, double latitude, double longitude,
            String audio, int category, boolean visible, String zoneName, String parentZoneName, String countryEmoji) {
        this.key = key;
        this.media = media;
        this.title = title;
        this.description = description;
        this.views = views;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.userKey = userKey;
        this.username = username;
        this.userImage = userImage;
        this.userRange = userRange;
        this.userFollowers = userFollowers;
        this.userTotalFootprints = userTotalFootprints;
        this.userLevel = userLevel;
        this.userRelationship = userRelationship;
        this.creationMoment = creationMoment;
        this.aspectRatio = aspectRatio;
        this.scope = scope;
        this.latitude = latitude;
        this.longitude = longitude;
        this.audio = audio;
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

    public String getUserKey() {
        return userKey;
    }

    public String getUsername() {
        return username;
    }

    public long getUserRange() {
        return userRange;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getUserTotalFootprints() {
        return userTotalFootprints;
    }

    public String getUserImage() {
        return userImage;
    }

    public int getUserRelationship() {
        return userRelationship;
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

    public String getAudio() {
        return audio;
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
