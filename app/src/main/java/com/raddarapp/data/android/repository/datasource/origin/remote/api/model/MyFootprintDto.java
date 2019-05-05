package com.raddarapp.data.android.repository.datasource.origin.remote.api.model;

import com.google.gson.annotations.SerializedName;

public class MyFootprintDto {

    @SerializedName("id")
    private String id;
    @SerializedName("media")
    private String media;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("likes")
    private long likes;
    @SerializedName("dislikes")
    private long dislikes;
    @SerializedName("comments")
    private long comments;
    @SerializedName("views")
    private long views;
    @SerializedName("mediaType")
    private int mediaType;
    @SerializedName("voted")
    private int voted;
    @SerializedName("visibility")
    private int visibility;
    @SerializedName("sponsored")
    private boolean sponsored;
    @SerializedName("scope")
    private long scope;
    @SerializedName("category")
    private int category;
    @SerializedName("captures")
    private long captures;
    @SerializedName("aspectRatio")
    private int aspectRatio;
    @SerializedName("level")
    private int level;
    @SerializedName("type")
    private int type;
    @SerializedName("visible")
    private boolean visible;
    @SerializedName("zoneName")
    private String zoneName;
    @SerializedName("parentZoneName")
    private String parentZoneName;
    @SerializedName("countryEmoji")
    private String countryEmoji;
    @SerializedName("status")
    private int status;

    @SerializedName("raddarLocation")
    private RaddarLocationDto raddarLocationDto;

    public String getId() {
        return id;
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

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public long getComments() {
        return comments;
    }

    public long getViews() {
        return views;
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

    public boolean getSponsored() {
        return sponsored;
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

    public RaddarLocationDto getRaddarLocationDto() {
        return raddarLocationDto;
    }

    public boolean isVisible() {
        return visible;
    }
}
