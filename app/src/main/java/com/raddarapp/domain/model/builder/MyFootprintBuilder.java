package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;

public class MyFootprintBuilder {

    private String key;
    private String media;
    private String title;
    private String description;
    private long comments;
    private long views;
    private long likes;
    private long dislikes;
    private int mediaType;
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

    private RaddarLocation raddarLocation;
    private User user;

    public MyFootprintBuilder() {}

    public MyFootprint build() {
        return new MyFootprint(key, media, title, description, comments, views, likes, dislikes,
            mediaType, visibility, sponsored, scope, category, captures, aspectRatio, level, type,
            visible, zoneName, parentZoneName, countryEmoji, status, raddarLocation, user);
    }

    public MyFootprintBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MyFootprintBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintBuilder withViews(long views) {
        this.views = views;
        return this;
    }

    public MyFootprintBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MyFootprintBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public MyFootprintBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public MyFootprintBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintBuilder withCaptures(long captures) {
        this.captures = captures;
        return this;
    }

    public MyFootprintBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public MyFootprintBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyFootprintBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public MyFootprintBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public MyFootprintBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public MyFootprintBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }

    public MyFootprintBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
