package com.raddarapp.domain.model;

import com.karumi.rosie.repository.datasource.Identifiable;

public class FootprintRanking implements Identifiable<String> {

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

    private final RaddarLocation raddarLocation;
    private User user;

    public FootprintRanking(String key, String media, String title, String description, long comments,
            long views, long likes, long dislikes, int mediaType, int voted, int visibility, int sponsored,
            long scope, int category, long captures, int aspectRatio, int level, int type, boolean visible,
            String zoneName, String parentZoneName, String countryEmoji, RaddarLocation raddarLocation, User user) {
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
        this.user = user;
        this.zoneName = zoneName;
        this.parentZoneName = parentZoneName;
        this.countryEmoji = countryEmoji;
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

    public User getUser() {
        return user;
    }

    public boolean isVisible() {
        return visible;
    }

    public void updateUserRelationship(int userRelationship) {
        user.updateUserRelationship(userRelationship);
    }

    public void updateComments() {
        this.comments++;
    }

    public void updateComments(long totalComments) {
        this.comments = totalComments;
    }

    public void updateCompliments(long totalCompliments) {
        this.getUser().updateCompliments(totalCompliments);
    }

    public void setUser(User user) {
        this.user = user;
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
