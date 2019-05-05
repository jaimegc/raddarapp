package com.raddarapp.presentation.android.utils;

import android.content.Context;

import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyNotificationTokenPreferencesDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.RaddarSettingsPreferencesDataSource;

import java.util.Set;

public class PreferencesUtils {

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;
    private RaddarSettingsPreferencesDataSource raddarSettingsPreferencesDataSource;
    private MyNotificationTokenPreferencesDataSource myNotificationTokenPreferencesDataSource;

    public PreferencesUtils(Context context) {
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(context);
        raddarSettingsPreferencesDataSource = new RaddarSettingsPreferencesDataSource(context);
        myNotificationTokenPreferencesDataSource = new MyNotificationTokenPreferencesDataSource(context);
    }

    public boolean isLoggedIn() {
        return userProfilePreferencesDataSource.isLoggedIn();
    }

    public String getMyUserKey() {
        return userProfilePreferencesDataSource.getUserKey();
    }

    public String getMyUserAudio() {
        return userProfilePreferencesDataSource.getAudio();
    }

    public int getMyUserLevel() {
        return userProfilePreferencesDataSource.getLevel();
    }

    public boolean hasSounds() {
        return userProfilePreferencesDataSource.hasSounds();
    }

    public int getGender() {
        return userProfilePreferencesDataSource.getGender();
    }

    public String getBirthdate() {
        return userProfilePreferencesDataSource.getBirthdate();
    }

    public String getEmail() {
        return userProfilePreferencesDataSource.getEmail();
    }

    public String getMyPromoCode() {
        return userProfilePreferencesDataSource.getMyPromoCode();
    }

    public Integer getRole() {
        return userProfilePreferencesDataSource.getRole();
    }

    public Integer getVersionCode() {
        return raddarSettingsPreferencesDataSource.getVersionCode();
    }

    public void setVersionCode(Integer versionCode) {
        raddarSettingsPreferencesDataSource.setVersionCode(versionCode);
    }

    public boolean showWelcomeScreen() {
        return raddarSettingsPreferencesDataSource.showWelcomeScreen();
    }

    public void setShowWelcomeScreen(boolean showWelcomeScreen) {
        raddarSettingsPreferencesDataSource.setShowWelcomeScreen(showWelcomeScreen);
    }

    public boolean showWelcomeInAppScreen() {
        return raddarSettingsPreferencesDataSource.showWelcomeInAppScreen();
    }

    public void setShowWelcomeInAppScreen(boolean showWelcomeInAppScreen) {
        raddarSettingsPreferencesDataSource.setShowWelcomeInAppScreen(showWelcomeInAppScreen);
    }

    public void setShowTutorialMap(boolean showTutorialMap) {
        raddarSettingsPreferencesDataSource.setShowTutorialMap(showTutorialMap);
    }

    public boolean showTutorialMap() {
        return raddarSettingsPreferencesDataSource.showTutorialMap();
    }

    public void setShowTutorialMyProfile(boolean showTutorialMyProfile) {
        raddarSettingsPreferencesDataSource.setShowTutorialMyProfile(showTutorialMyProfile);
    }

    public boolean showTutorialMyProfile() {
        return raddarSettingsPreferencesDataSource.showTutorialMyProfile();
    }

    public void setShowTutorialCreateFootprint(boolean showTutorialCreateFootprint) {
        raddarSettingsPreferencesDataSource.setShowTutorialCreateFootprint(showTutorialCreateFootprint);
    }

    public boolean showTutorialCreateFootprint() {
        return raddarSettingsPreferencesDataSource.showTutorialCreateFootprint();
    }

    public void setShowTutorialMyRanking(boolean showTutorialMyRanking) {
        raddarSettingsPreferencesDataSource.setShowTutorialMyRanking(showTutorialMyRanking);
    }

    public boolean showTutorialMyRanking() {
        return raddarSettingsPreferencesDataSource.showTutorialMyRanking();
    }

    public String getMyNotificationToken() {
        return myNotificationTokenPreferencesDataSource.getMyNotificationToken();
    }

    public void setMyNotificationToken(String notificationToken) {
        myNotificationTokenPreferencesDataSource.setMyNotificationToken(notificationToken);
    }

    public void setMyNotificationTopics(Set<String> notificationTopics) {
        userProfilePreferencesDataSource.setNotificationTopics(notificationTopics);
    }

    public void setMobileLanguage(String mobileLanguage) {
        userProfilePreferencesDataSource.setMobileLanguage(mobileLanguage);
    }

    public String getMobileLanguage() {
        return userProfilePreferencesDataSource.getMobileLanguage();
    }
}
