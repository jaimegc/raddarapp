package com.raddarapp.data.android.repository.datasource.origin.remote.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.preferences.base.DefaultValuesPrefererences;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.builder.MyUserProfileBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyUserProfilePreferencesDataSource extends DefaultValuesPrefererences implements MyUserProfilePreferencesDataSourceContract {

    private static final String PREFERENCE_NAME = "PREFERENCE_NAME_USER_PROFILE";

    private static final String PREF_KEY_USER_KEY = "PREF_KEY_USER_KEY";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
    private static final String PREF_KEY_USER_SURNAME = "PREF_KEY_USER_SURNAME";
    private static final String PREF_KEY_USER_USERNAME = "PREF_KEY_USER_USERNAME";

    private static final String PREF_KEY_USER_IMAGE = "PREF_KEY_USER_IMAGE";
    private static final String PREF_KEY_USER_IMAGE_CACHE = "PREF_KEY_USER_IMAGE_CACHE";
    private static final String PREF_KEY_USER_AUDIO = "PREF_KEY_USER_AUDIO";
    private static final String PREF_KEY_USER_AUDIO_CACHE = "PREF_KEY_USER_AUDIO_CACHE";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL";
    private static final String PREF_KEY_USER_RANGE = "PREF_KEY_USER_RANGE";
    private static final String PREF_KEY_USER_FOLLOWERS = "PREF_KEY_USER_FOLLOWERS";
    private static final String PREF_KEY_USER_FOLLOWING = "PREF_KEY_USER_FOLLOWING";
    private static final String PREF_KEY_USER_TOTAL_FOOTPRINTS = "PREF_KEY_USER_TOTAL_FOOTPRINTS";
    private static final String PREF_KEY_USER_USER_COMPLIMENTS = "PREF_KEY_USER_USER_COMPLIMENTS";
    private static final String PREF_KEY_USER_LEVEL = "PREF_KEY_USER_LEVEL";
    private static final String PREF_KEY_USER_PERCENTAGE = "PREF_KEY_USER_PERCENTAGE";
    private static final String PREF_KEY_USER_ACCESS_TOKEN = "PREF_KEY_USER_ACCESS_TOKEN";
    private static final String PREF_KEY_USER_REFRESH_TOKEN = "PREF_KEY_USER_REFRESH_TOKEN";
    private static final String PREF_KEY_USER_REFRESH_TOKEN_UPDATED = "PREF_KEY_USER_REFRESH_TOKEN_UPDATED";
    private static final String PREF_KEY_USER_SOUNDS = "PREF_KEY_USER_SOUNDS";
    private static final String PREF_KEY_USER_RANGE_MINED = "PREF_KEY_USER_RANGE_MINED";
    private static final String PREF_KEY_USER_RANGE_NOT_MINED = "PREF_KEY_USER_RANGE_NOT_MINED";
    private static final String PREF_KEY_USER_RANGE_MINING_DATE = "PREF_KEY_USER_RANGE_MINING_DATE";
    private static final String PREF_KEY_USER_RANGE_MINED_LOCAL_ACCUMULATED = "PREF_KEY_USER_RANGE_MINED_LOCAL_ACCUMULATED";
    private static final String PREF_KEY_USER_GENDER = "PREF_KEY_USER_GENDER";
    private static final String PREF_KEY_USER_BIRTHDATE = "PREF_KEY_USER_BIRTHDATE";
    private static final String PREF_KEY_USER_MY_PROMO_CODE = "PREF_KEY_USER_MY_PROMO_CODE";
    private static final String PREF_KEY_USER_ROLE = "PREF_KEY_USER_ROLE";
    private static final String PREF_KEY_USER_STATUS = "PREF_KEY_USER_STATUS";
    private static final String PREF_KEY_USER_NOTIFICATION_TOPICS = "PREF_KEY_USER_NOTIFICATION_TOPICS";
    private static final String PREF_KEY_USER_TOTAL_LIKES = "PREF_KEY_USER_TOTAL_LIKES";
    private static final String PREF_KEY_USER_TOTAL_DISLIKES = "PREF_KEY_USER_TOTAL_DISLIKES";
    private static final String PREF_KEY_USER_TOTAL_FOOTPRINTS_DEAD = "PREF_KEY_USER_TOTAL_FOOTPRINTS_DEAD";
    private static final String PREF_KEY_USER_MOBILE_LANGUAGE = "PREF_KEY_USER_MOBILE_LANGUAGE";

    private final SharedPreferences preferences;

    @Inject
    public MyUserProfilePreferencesDataSource(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getUserKey() {
        return preferences.getString(PREF_KEY_USER_KEY, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setUserKey(String userKey) {
        preferences.edit().putString(PREF_KEY_USER_KEY, userKey).apply();
    }

    @Override
    public String getName() {
        return preferences.getString(PREF_KEY_USER_NAME, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setName(String name) {
        preferences.edit().putString(PREF_KEY_USER_NAME, name).apply();
    }

    @Override
    public String getSurname() {
        return preferences.getString(PREF_KEY_USER_SURNAME, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setSurname(String surname) {
        preferences.edit().putString(PREF_KEY_USER_SURNAME, surname).apply();
    }

    @Override
    public String getUsername() {
        return preferences.getString(PREF_KEY_USER_USERNAME, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setUsername(String username) {
        preferences.edit().putString(PREF_KEY_USER_USERNAME, username).apply();
    }

    @Override
    public String getImage() {
        return preferences.getString(PREF_KEY_USER_IMAGE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setImage(String image) {
        preferences.edit().putString(PREF_KEY_USER_IMAGE, image).apply();
    }

    @Override
    public String getImageCache() {
        return preferences.getString(PREF_KEY_USER_IMAGE_CACHE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setImageCache(String imageCache) {
        preferences.edit().putString(PREF_KEY_USER_IMAGE_CACHE, imageCache).apply();
    }

    @Override
    public String getAudio() {
        return preferences.getString(PREF_KEY_USER_AUDIO, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setAudio(String audio) {
        preferences.edit().putString(PREF_KEY_USER_AUDIO, audio).apply();
    }

    @Override
    public String getAudioCache() {
        return preferences.getString(PREF_KEY_USER_AUDIO_CACHE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setAudioCache(String audioCache) {
        preferences.edit().putString(PREF_KEY_USER_AUDIO_CACHE, audioCache).apply();
    }

    @Override
    public String getEmail() {
        return preferences.getString(PREF_KEY_USER_EMAIL, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setEmail(String email) {
        preferences.edit().putString(PREF_KEY_USER_EMAIL, email).apply();
    }

    @Override
    public long getRange() {
        return preferences.getLong(PREF_KEY_USER_RANGE, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setRange(long range) {
        preferences.edit().putLong(PREF_KEY_USER_RANGE, range).apply();
    }

    @Override
    public long getFollowers() {
        return preferences.getLong(PREF_KEY_USER_FOLLOWERS, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setFollowers(long followers) {
        preferences.edit().putLong(PREF_KEY_USER_FOLLOWERS, followers).apply();
    }

    @Override
    public long getFollowing() {
        return preferences.getLong(PREF_KEY_USER_FOLLOWING, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setFollowing(long following) {
        preferences.edit().putLong(PREF_KEY_USER_FOLLOWING, following).apply();
    }

    @Override
    public long getTotalFootprints() {
        return preferences.getLong(PREF_KEY_USER_TOTAL_FOOTPRINTS, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setTotalFootprints(long totalFootprints) {
        preferences.edit().putLong(PREF_KEY_USER_TOTAL_FOOTPRINTS, totalFootprints).apply();
    }

    @Override
    public long getUserCompliments() {
        return preferences.getLong(PREF_KEY_USER_USER_COMPLIMENTS, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setUserCompliments(long userCompliments) {
        preferences.edit().putLong(PREF_KEY_USER_USER_COMPLIMENTS, userCompliments).apply();
    }

    @Override
    public int getLevel() {
        return preferences.getInt(PREF_KEY_USER_LEVEL, DEFAULT_VALUE_INT);
    }

    @Override
    public void setLevel(int level) {
        preferences.edit().putInt(PREF_KEY_USER_LEVEL, level).apply();
    }

    @Override
    public int getPercentage() {
        return preferences.getInt(PREF_KEY_USER_PERCENTAGE, DEFAULT_VALUE_INT);
    }

    @Override
    public void setPercentage(int percentage) {
        preferences.edit().putInt(PREF_KEY_USER_PERCENTAGE, percentage).apply();
    }

    @Override
    public String getAccessToken() {
        return preferences.getString(PREF_KEY_USER_ACCESS_TOKEN, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setAccessToken(String accessToken) {
        preferences.edit().putString(PREF_KEY_USER_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getRefreshToken() {
        return preferences.getString(PREF_KEY_USER_REFRESH_TOKEN, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        preferences.edit().putString(PREF_KEY_USER_REFRESH_TOKEN, refreshToken).apply();
    }

    @Override
    public void setRefreshTokenUpdated(long refreshTokenUpdated) {
        preferences.edit().putLong(PREF_KEY_USER_REFRESH_TOKEN_UPDATED, refreshTokenUpdated).apply();
    }

    @Override
    public long getRefreshTokenUpdated() {
        return preferences.getLong(PREF_KEY_USER_REFRESH_TOKEN_UPDATED, DEFAULT_VALUE_LONG);
    }

    @Override
    public boolean hasSounds() {
        return preferences.getBoolean(PREF_KEY_USER_SOUNDS, true);
    }

    @Override
    public void setHasSounds(boolean soundsEnabled) {
        preferences.edit().putBoolean(PREF_KEY_USER_SOUNDS, soundsEnabled).apply();
    }

    @Override
    public int getRangeMined() {
        return preferences.getInt(PREF_KEY_USER_RANGE_MINED, DEFAULT_VALUE_INT);

    }

    @Override
    public void setRangeMined(int rangeMined) {
        preferences.edit().putInt(PREF_KEY_USER_RANGE_MINED, rangeMined).apply();
    }

    @Override
    public int getRangeNotMined() {
        return preferences.getInt(PREF_KEY_USER_RANGE_NOT_MINED, DEFAULT_VALUE_INT);
    }

    @Override
    public void setRangeNotMined(int rangeNotMined) {
        preferences.edit().putInt(PREF_KEY_USER_RANGE_NOT_MINED, rangeNotMined).apply();
    }

    @Override
    public String getMiningDate() {
        return preferences.getString(PREF_KEY_USER_RANGE_MINING_DATE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setMiningDate(String miningDate) {
        preferences.edit().putString(PREF_KEY_USER_RANGE_MINING_DATE, miningDate).apply();
    }

    @Override
    public int getRangeMinedLocalAccumulated() {
        return preferences.getInt(PREF_KEY_USER_RANGE_MINED_LOCAL_ACCUMULATED, DEFAULT_VALUE_INT);
    }

    @Override
    public void setRangeMinedLocalAccumulated(int rangeMinedLocalAccumulated) {
        preferences.edit().putInt(PREF_KEY_USER_RANGE_MINED_LOCAL_ACCUMULATED, rangeMinedLocalAccumulated).apply();
    }

    @Override
    public int getGender() {
        return preferences.getInt(PREF_KEY_USER_GENDER, DEFAULT_VALUE_INT_TWO);
    }

    @Override
    public void setGender(int gender) {
        preferences.edit().putInt(PREF_KEY_USER_GENDER, gender).apply();
    }

    @Override
    public String getBirthdate() {
        return preferences.getString(PREF_KEY_USER_BIRTHDATE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setBirthdate(String birthdate) {
        preferences.edit().putString(PREF_KEY_USER_BIRTHDATE, birthdate).apply();
    }

    @Override
    public String getMyPromoCode() {
        return preferences.getString(PREF_KEY_USER_MY_PROMO_CODE, DEFAULT_VALUE_STRING);
    }

    // We set this value only in getMyPromoCode method. This is not included in MyUserProfile entity
    @Override
    public void setMyPromoCode(String myPromoCode) {
        preferences.edit().putString(PREF_KEY_USER_MY_PROMO_CODE, myPromoCode).apply();
    }

    @Override
    public int getRole() {
        return preferences.getInt(PREF_KEY_USER_ROLE, DEFAULT_VALUE_INT_ROLE);
    }

    @Override
    public void setRole(int role) {
        preferences.edit().putInt(PREF_KEY_USER_ROLE, role).apply();
    }

    @Override
    public int getStatus() {
        return preferences.getInt(PREF_KEY_USER_STATUS, DEFAULT_VALUE_INT_STATUS);
    }

    @Override
    public void setStatus(int status) {
        preferences.edit().putInt(PREF_KEY_USER_STATUS, status).apply();
    }

    @Override
    public Set<String> getNotificationTopics() {
        return preferences.getStringSet(PREF_KEY_USER_NOTIFICATION_TOPICS, DEFAULT_NOTIFICATION_TOPICS);
    }

    @Override
    public void setNotificationTopics(Set<String> notificationTopics) {
        preferences.edit().putStringSet(PREF_KEY_USER_NOTIFICATION_TOPICS, notificationTopics).apply();
    }

    @Override
    public long getTotalLikes() {
        return preferences.getLong(PREF_KEY_USER_TOTAL_LIKES, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setTotalLikes(long totalLikes) {
        preferences.edit().putLong(PREF_KEY_USER_TOTAL_LIKES, totalLikes).apply();
    }

    @Override
    public long getTotalDislikes() {
        return preferences.getLong(PREF_KEY_USER_TOTAL_DISLIKES, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setTotalDislikes(long totalDislikes) {
        preferences.edit().putLong(PREF_KEY_USER_TOTAL_DISLIKES, totalDislikes).apply();
    }

    @Override
    public long getTotalFootprintsDead() {
        return preferences.getLong(PREF_KEY_USER_TOTAL_FOOTPRINTS_DEAD, DEFAULT_VALUE_LONG);
    }

    @Override
    public void setTotalFootprintsDead(long totalFootprintsDead) {
        preferences.edit().putLong(PREF_KEY_USER_TOTAL_FOOTPRINTS_DEAD, totalFootprintsDead).apply();
    }

    @Override
    public String getMobileLanguage() {
        return preferences.getString(PREF_KEY_USER_MOBILE_LANGUAGE, DEFAULT_VALUE_STRING);
    }

    @Override
    public void setMobileLanguage(String language) {
        preferences.edit().putString(PREF_KEY_USER_MOBILE_LANGUAGE, language).apply();
    }

    @Override
    public void setUserProfilePreferencesFull(MyUserProfile userProfile) {
        this.setUserKey(userProfile.getKey());
        this.setName(userProfile.getName());
        this.setSurname(userProfile.getSurname());
        this.setUsername(userProfile.getUsername());
        this.setImage(userProfile.getImage());
        this.setImageCache(userProfile.getImageCache());
        this.setAudio(userProfile.getAudio());
        this.setAudioCache(userProfile.getAudioCache());
        this.setEmail(userProfile.getEmail());
        this.setRange(userProfile.getRange());
        this.setFollowers(userProfile.getFollowers());
        this.setFollowing(userProfile.getFollowing());
        this.setTotalFootprints(userProfile.getTotalFootprints());
        this.setUserCompliments(userProfile.getUserCompliments());
        this.setLevel(userProfile.getLevel());
        this.setPercentage(userProfile.getPercentage());
        this.setAccessToken(userProfile.getAccessToken());
        this.setRefreshToken(userProfile.getRefreshToken());
        this.setRangeMined(userProfile.getRangeMined());
        this.setRangeNotMined(userProfile.getRangeNotMined());
        this.setMiningDate(userProfile.getMiningDate());
        this.setRangeMinedLocalAccumulated(userProfile.getRangeMinedLocalAccumulated());
        this.setGender(userProfile.getGender());
        this.setBirthdate(userProfile.getBirthdate());
        this.setRole(userProfile.getRole());
        this.setStatus(userProfile.getStatus());
        this.setNotificationTopics(userProfile.getNotificationTopics());
        this.setTotalLikes(userProfile.getTotalLikes());
        this.setTotalDislikes(userProfile.getTotalDislikes());
        this.setTotalFootprintsDead(userProfile.getTotalFootprintsDead());
    }

    @Override
    public void setUserProfilePreferences(MyUserProfile userProfile) {
        this.setUserKey(userProfile.getKey());
        this.setName(userProfile.getName());
        this.setSurname(userProfile.getSurname());
        this.setUsername(userProfile.getUsername());

        if (userProfile.getImage() != null && !userProfile.getImage().isEmpty()) {
            this.setImage(userProfile.getImage());
        } else {
            this.setImage("");
            this.setImageCache("");
        }

        if (userProfile.getAudio() != null && !userProfile.getAudio().isEmpty()) {
            this.setAudio(userProfile.getAudio());
        } else {
            this.setAudio("");
            this.setAudioCache("");
        }

        this.setEmail(userProfile.getEmail());
        this.setRange(userProfile.getRange());
        this.setFollowers(userProfile.getFollowers());
        this.setFollowing(userProfile.getFollowing());
        this.setTotalFootprints(userProfile.getTotalFootprints());
        this.setUserCompliments(userProfile.getUserCompliments());
        this.setLevel(userProfile.getLevel());
        this.setPercentage(userProfile.getPercentage());
        this.setRangeMined(userProfile.getRangeMined());
        this.setRangeNotMined(userProfile.getRangeNotMined());
        this.setMiningDate(userProfile.getMiningDate());
        this.setRangeMinedLocalAccumulated(userProfile.getRangeMinedLocalAccumulated());
        this.setGender(userProfile.getGender());
        this.setBirthdate(userProfile.getBirthdate());
        this.setRole(userProfile.getRole());
        this.setStatus(userProfile.getStatus());
        this.setNotificationTopics(userProfile.getNotificationTopics());
        this.setTotalLikes(userProfile.getTotalLikes());
        this.setTotalDislikes(userProfile.getTotalDislikes());
        this.setTotalFootprintsDead(userProfile.getTotalFootprintsDead());
    }

    @Override
    public MyUserProfile getUserProfilePreferences() {
        final MyUserProfile userProfile = new MyUserProfileBuilder()
                .withKey(this.getUserKey())
                .withName(this.getName())
                .withSurname(this.getSurname())
                .withUsername(this.getUsername())
                .withImage(this.getImage())
                .withImageCache(this.getImageCache())
                .withAudio(this.getAudio())
                .withAudioCache(this.getAudioCache())
                .withEmail(this.getEmail())
                .withRange(this.getRange())
                .withFollowers(this.getFollowers())
                .withFollowing(this.getFollowing())
                .withTotalFootprints(this.getTotalFootprints())
                .withUserCompliments(this.getUserCompliments())
                .withLevel(this.getLevel())
                .withPercentage(this.getPercentage())
                .withAccessToken(this.getAccessToken())
                .withRefreshToken(this.getRefreshToken())
                .withRefreshTokenUpdated(this.getRefreshTokenUpdated())
                .withRangeMined(this.getRangeMined())
                .withRangeNotMined(this.getRangeNotMined())
                .withMiningDate(this.getMiningDate())
                .withRangeMinedLocalAccumulated(this.getRangeMinedLocalAccumulated())
                .withGender(this.getGender())
                .withBirthdate(this.getBirthdate())
                .withRole(this.getRole())
                .withStatus(this.getStatus())
                .withNotificationTopics(this.getNotificationTopics())
                .withTotalLikes(this.getTotalLikes())
                .withTotalDislikes(this.getTotalDislikes())
                .withTotalFootprintsDead(this.getTotalFootprintsDead())
                .withMobileLanguage(this.getMobileLanguage())
                .build();

        return userProfile;
    }

    @Override
    public void logout() {
        preferences.edit().clear().commit();
    }

    @Override
    public boolean isLoggedIn() {
        return !getAccessToken().isEmpty() && !getRefreshToken().isEmpty();
    }

    @Override
    public User userProfileToUserMapper() {
        MyUserProfile userProfile = getUserProfilePreferences();

        User user = new UserBuilder()
                .withKey(userProfile.getKey())
                .withUsername(userProfile.getUsername())
                .withImage(userProfile.getImage())
                .withAudio(userProfile.getAudio())
                .withRange(userProfile.getRange())
                .withFollowers(userProfile.getFollowers())
                .withFollowing(userProfile.getFollowing())
                .withUserRelationship(UserRelationshipType.ME.getValue())
                .withUserCompliments(userProfile.getUserCompliments())
                .withLevel(userProfile.getLevel())
                .withTotalFootprints(userProfile.getTotalFootprints())
                .withTotalLikes(userProfile.getTotalLikes())
                .withTotalDislikes(userProfile.getTotalDislikes())
                .withTotalFootprintsDead(userProfile.getTotalFootprintsDead())
                .build();

        return user;
    }
}
