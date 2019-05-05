package com.raddarapp.domain.model.builder;

import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;

public class FootprintRankingBuilder {

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

    public FootprintRankingBuilder() {}

    public FootprintRanking build() {
        return new FootprintRanking(key, media, title, description, comments, views, likes, dislikes,
            mediaType, voted, visibility, sponsored, scope, category, captures, aspectRatio, level, type,
            visible, zoneName, parentZoneName, countryEmoji, raddarLocation, user);
    }

    public FootprintRankingBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintRankingBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintRankingBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintRankingBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FootprintRankingBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintRankingBuilder withViews(long views) {
        this.views = views;
        return this;
    }

    public FootprintRankingBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintRankingBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintRankingBuilder withMediaType(int mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public FootprintRankingBuilder withVoted(int voted) {
        this.voted = voted;
        return this;
    }

    public FootprintRankingBuilder withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public FootprintRankingBuilder withSponsored(int sponsored) {
        this.sponsored = sponsored;
        return this;
    }

    public FootprintRankingBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintRankingBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintRankingBuilder withCaptures(long captures) {
        this.captures = captures;
        return this;
    }

    public FootprintRankingBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public FootprintRankingBuilder withLevel(int level) {
        this.level = level;
        return this;
    }

    public FootprintRankingBuilder withType(int type) {
        this.type = type;
        return this;
    }

    public FootprintRankingBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintRankingBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintRankingBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintRankingBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }

    public FootprintRankingBuilder withRaddarLocation(RaddarLocation raddarLocation) {
        this.raddarLocation = raddarLocation;
        return this;
    }

    public FootprintRankingBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
