package com.raddarapp.presentation.general.viewmodel;

import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintDetailsViewModelContract;

public class MyFootprintDetailsViewModel implements MyFootprintDetailsViewModelContract {

    private final String key;
    private final String title;
    private final String description;
    private final String media;
    private final String userKey;
    private final String username;
    private final String userImage;
    private final long userRange;
    private final String userFollowers;
    private final String userFollowing;
    private final String userLevel;
    private final String userTotalFootprints;
    private final String creationMoment;
    private final int mediaType;
    private final int visibility;
    private final int aspectRatio;
    private final int sponsored;
    private final String views;
    private long comments;
    private final long likes;
    private final long dislikes;
    private final long scope;
    private final int category;
    private final String captures;
    private final double latitude;
    private final double longitude;
    private final int type;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;

    public MyFootprintDetailsViewModel(String key, String title, String description, String media, String userKey, String username, String userImage,
            long userRange, String userFollowers, String userFollowing, String userLevel, String userTotalFootprints, String creationMoment,
            int mediaType, int visibility, int aspectRatio, int sponsored, String views, long comments, long likes, long dislikes,
            long scope, int category, String captures, double latitude, double longitude, int type, boolean visible, String zoneName,
            String parentZoneName, String countryEmoji) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.media = media;
        this.userKey = userKey;
        this.username = username;
        this.userImage = userImage;
        this.userRange = userRange;
        this.userFollowers = userFollowers;
        this.userFollowing = userFollowing;
        this.userLevel = userLevel;
        this.userTotalFootprints = userTotalFootprints;
        this.creationMoment = creationMoment;
        this.mediaType = mediaType;
        this.visibility = visibility;
        this.aspectRatio = aspectRatio;
        this.sponsored = sponsored;
        this.views = views;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.scope = scope;
        this.category = category;
        this.captures = captures;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.visible = visible;
        this.zoneName = zoneName;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public long getUserRange() {
        return userRange;
    }

    public String getUserFollowers() {
        return userFollowers;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getUserTotalFootprints() {
        return userTotalFootprints;
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

    public long getScope() {
        return scope;
    }

    public int getCategory() {
        return category;
    }

    public String getCaptures() {
        return captures;
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
