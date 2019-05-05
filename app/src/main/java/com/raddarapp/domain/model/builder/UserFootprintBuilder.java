package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.UserFootprint;

public class UserFootprintBuilder {

    private String key;
    private String media;
    private String title;
    private String description;
    private long comments;
    private long views;
    private long likes;
    private long dislikes;
    private int mediaType;
    private int voted;
    private int visibility;
    private int sponsored;
    private long scope;
    private int category;
    private long captures;
    private int aspectRatio;
    private int level;
    private int type;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;
    private int status;
    private long totalLikes;
    private long totalDislikes;
    private long totalFootprintsDead;

    private RaddarLocation raddarLocation;

    public UserFootprintBuilder() {}

    public UserFootprint build() {
        return new UserFootprint(key, media, title, description, comments, views, likes, dislikes,
                mediaType, voted, visibility, sponsored, scope, category, captures, aspectRatio, level, type, visible,
                zoneName, parentZoneName, countryEmoji, status, raddarLocation, totalLikes, totalDislikes, totalFootprintsDead);
    }

    public UserFootprintBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public UserFootprintBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public UserFootprintBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public UserFootprintBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public UserFootprintBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public UserFootprintBuilder withViews(long views) {
        this.views = views;
        return this;
    }

    public UserFootprintBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public UserFootprintBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public UserFootprintBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public UserFootprintBuilder withVoted(int voted) {
        this.voted = voted;
        return this;
    }

    public UserFootprintBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public UserFootprintBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public UserFootprintBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public UserFootprintBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public UserFootprintBuilder withCaptures(long captures) {
        this.captures = captures;
        return this;
    }

    public UserFootprintBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public UserFootprintBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public UserFootprintBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public UserFootprintBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public UserFootprintBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public UserFootprintBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public UserFootprintBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public UserFootprintBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public UserFootprintBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }

    public UserFootprintBuilder withTotalLikes(long totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }

    public UserFootprintBuilder withTotalDislikes(long totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }

    public UserFootprintBuilder withTotalFootprintsDead(long totalFootprintsDead) {
        this.totalFootprintsDead = totalFootprintsDead;
        return this;
    }
}
