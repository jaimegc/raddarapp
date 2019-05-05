package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class UserFootprint implements Identifiable<String> {

    private final String key;
    private final String media;
    private final String title;
    private final String description;
    private long comments;
    private final long views;
    private final long likes;
    private final long dislikes;
    private final int mediaType;
    private final int voted;
    private final int visibility;
    private final int sponsored;
    private final long scope;
    private final int category;
    private final long captures;
    private final int aspectRatio;
    private final int level;
    private final int type;
    private final boolean visible;
    private final String zoneName;
    private final String parentZoneName;
    private final String countryEmoji;
    private final int status;
    private final long totalLikes;
    private final long totalDislikes;
    private final long totalFootprintsDead;

    private final RaddarLocation raddarLocation;

    public UserFootprint(String key, String media, String title, String description, long comments,
            long views, long likes, long dislikes, int mediaType, int voted, int visibility, int sponsored,
            long scope, int category, long captures, int aspectRatio, int level, int type, boolean visible,
            String zoneName, String parentZoneName, String countryEmoji, int status, RaddarLocation raddarLocation,
            long totalLikes, long totalDislikes, long totalFootprintsDead) {
        this.key = key;
        this.media = media;
        this.title = title;
        this.description = description;
        this.comments = comments;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.mediaType = mediaType;
        this.voted = voted;
        this.visibility = visibility;
        this.sponsored = sponsored;
        this.scope = scope;
        this.category = category;
        this.captures = captures;
        this.aspectRatio = aspectRatio;
        this.level = level;
        this.type = type;
        this.visible = visible;
        this.raddarLocation = raddarLocation;
        this.zoneName = zoneName;
        this.status = status;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
        this.totalLikes = totalLikes;
        this.totalDislikes = totalDislikes;
        this.totalFootprintsDead = totalFootprintsDead;
    }

    @Override
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

    public long getComments() {
        return comments;
    }

    public long getViews() {
        return views;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public int getMediaType() {
        return mediaType;
    }

    public int getVoted() {
        return voted;
    }

    public int getVisibility() {
        return visibility;
    }

    public int getSponsored() {
        return sponsored;
    }

    public RaddarLocation getRaddarLocation() {
        return raddarLocation;
    }

    public long getScope() {
        return scope;
    }

    public int getCategory() {
        return category;
    }

    public long getCaptures() {
        return captures;
    }

    public int getAspectRatio() {
        return aspectRatio;
    }

    public int getLevel() {
        return level;
    }

    public int getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void updateComments() {
        this.comments++;
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

    public long getTotalLikes() {
        return totalLikes;
    }

    public long getTotalDislikes() {
        return totalDislikes;
    }

    public long getTotalFootprintsDead() {
        return totalFootprintsDead;
    }
}
