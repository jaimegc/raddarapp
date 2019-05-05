package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.MyFootprintCollection;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;

public class MyFootprintCollectionBuilder {

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

    private RaddarLocation raddarLocation;
    private User user;

    public MyFootprintCollectionBuilder() {}

    public MyFootprintCollection build() {
        return new MyFootprintCollection(key, media, title, description, comments, views, likes, dislikes,
            mediaType, voted, visibility, sponsored, scope, category, captures, aspectRatio, level, type,
            visible, zoneName, parentZoneName, countryEmoji, status, raddarLocation, user);
    }

    public MyFootprintCollectionBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public MyFootprintCollectionBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public MyFootprintCollectionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MyFootprintCollectionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public MyFootprintCollectionBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public MyFootprintCollectionBuilder withViews(long views) {
        this.views = views;
        return this;
    }

    public MyFootprintCollectionBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public MyFootprintCollectionBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public MyFootprintCollectionBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public MyFootprintCollectionBuilder withVoted(int voted) {
        this.voted = voted;
        return this;
    }

    public MyFootprintCollectionBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public MyFootprintCollectionBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public MyFootprintCollectionBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public MyFootprintCollectionBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public MyFootprintCollectionBuilder withCaptures(long captures) {
        this.captures = captures;
        return this;
    }

    public MyFootprintCollectionBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public MyFootprintCollectionBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public MyFootprintCollectionBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public MyFootprintCollectionBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MyFootprintCollectionBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public MyFootprintCollectionBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public MyFootprintCollectionBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public MyFootprintCollectionBuilder withStatus(int status) {
        this.status = status;
        return this;
    }

    public MyFootprintCollectionBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }

    public MyFootprintCollectionBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
