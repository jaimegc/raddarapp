package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;

public class FootprintMainBuilder {

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

    private RaddarLocation raddarLocation;
    private User user;

    public FootprintMainBuilder() {}

    public FootprintMain build() {
        return new FootprintMain(key, media, title, description, comments, views, likes, dislikes,
                mediaType, voted, visibility, sponsored, scope, category, captures, aspectRatio, level, type,
                visible, zoneName, parentZoneName, countryEmoji, raddarLocation, user);
    }

    public FootprintMainBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintMainBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintMainBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintMainBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FootprintMainBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintMainBuilder withViews(long views) {
        this.views = views;
        return this;
    }

    public FootprintMainBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintMainBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintMainBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FootprintMainBuilder withVoted(int voted) {
        this.voted = voted;
        return this;
    }

    public FootprintMainBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public FootprintMainBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public FootprintMainBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintMainBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintMainBuilder withCaptures(long captures) {
        this.captures = captures;
        return this;
    }

    public FootprintMainBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public FootprintMainBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public FootprintMainBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public FootprintMainBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintMainBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintMainBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintMainBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public FootprintMainBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }

    public FootprintMainBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
