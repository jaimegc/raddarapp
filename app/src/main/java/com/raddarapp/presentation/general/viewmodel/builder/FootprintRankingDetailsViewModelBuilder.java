package com.raddarapp.presentation.general.viewmodel.builder;

import com.raddarapp.presentation.general.viewmodel.FootprintRankingDetailsViewModel;

public class FootprintRankingDetailsViewModelBuilder {

    private String key;
    private String media;
    private String title;
    private String description;
    private String views;
    private long comments;
    private long likes;
    private long dislikes;
    private String userKey;
    private String username;
    private String userImage;
    private long userRange;
    private String userFollowers;
    private String userLevel;
    private String userTotalFootprints;
    private int userRelationship;
    private String creationMoment;
    private int aspectRatio;
    private long scope;
    private double latitude;
    private double longitude;
    private String audio;
    private int category;
    private boolean visible;
    private String zoneName;
    private String parentZoneName;
    private String countryEmoji;

    public FootprintRankingDetailsViewModelBuilder() {}

    public FootprintRankingDetailsViewModel build() {
        return new FootprintRankingDetailsViewModel(key, media, title, description, views, comments, likes, dislikes,
                userKey, username, userImage, userRange, userFollowers, userLevel, userTotalFootprints,
                userRelationship, creationMoment, aspectRatio, scope, latitude, longitude, audio, category, visible,
                zoneName, parentZoneName, countryEmoji);
    }

    public FootprintRankingDetailsViewModelBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withMedia(String media) {
        this.media = media;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserKey(String userKey) {
        this.userKey = userKey;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserImage(String userImage) {
        this.userImage = userImage;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserRange(long userRange) {
        this.userRange = userRange;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserFollowers(String userFollowers) {
        this.userFollowers = userFollowers;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserLevel(String userLevel) {
        this.userLevel = userLevel;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserTotalFootprints(String userTotalFootprints) {
        this.userTotalFootprints = userTotalFootprints;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withCreationMoment(String creationMoment) {
        this.creationMoment = creationMoment;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withLikes(long likes) {
        this.likes = likes;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withDislikes(long dislikes) {
        this.dislikes = dislikes;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withViews(String views) {
        this.views = views;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withUserRelationship(int userRelationship) {
        this.userRelationship = userRelationship;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withComments(long comments) {
        this.comments = comments;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withAspectRatio(int aspectRatio) {
        this.aspectRatio = aspectRatio;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withScope(long scope) {
        this.scope = scope;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withAudio(String audio) {
        this.audio = audio;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withCategory(int category) {
        this.category = category;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withParentZoneName(String parentZoneName) {
        this.parentZoneName = parentZoneName;
        return this;
    }

    public FootprintRankingDetailsViewModelBuilder withCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
        return this;
    }
}
