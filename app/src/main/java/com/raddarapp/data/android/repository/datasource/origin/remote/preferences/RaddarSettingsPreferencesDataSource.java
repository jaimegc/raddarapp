package com.raddarapp.data.android.repository.datasource.origin.remote.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.raddarapp.data.general.datasource.base.contract.preferences.RaddarSettingsPreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.preferences.base.DefaultValuesPrefererences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RaddarSettingsPreferencesDataSource extends DefaultValuesPrefererences implements RaddarSettingsPreferencesDataSourceContract {

    private static final String PREFERENCE_NAME = "PREFERENCE_NAME_RADDAR_SETTINGS";

    private static final String PREF_KEY_VERSION_CODE = "PREF_KEY_VERSION_CODE";
    private static final String PREF_KEY_SHOW_WELCOME_SCREEN = "PREF_KEY_SHOW_WELCOME_SCREEN";
    private static final String PREF_KEY_SHOW_WELCOME_IN_APP_SCREEN = "PREF_KEY_SHOW_WELCOME_IN_APP_SCREEN";
    private static final String PREF_KEY_SHOW_TUTORIAL_MAP = "PREF_KEY_SHOW_TUTORIAL_MAP";
    private static final String PREF_KEY_SHOW_TUTORIAL_MY_PROFILE = "PREF_KEY_SHOW_TUTORIAL_MY_PROFILE";
    private static final String PREF_KEY_SHOW_TUTORIAL_CREATE_FOOTPRINT = "PREF_KEY_SHOW_TUTORIAL_CREATE_FOOTPRINT";
    private static final String PREF_KEY_SHOW_TUTORIAL_MY_RANKING = "PREF_KEY_SHOW_TUTORIAL_MY_RANKING";

    private final SharedPreferences preferences;

    @Inject
    public RaddarSettingsPreferencesDataSource(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Integer getVersionCode() {
        return preferences.getInt(PREF_KEY_VERSION_CODE, DEFAULT_VALUE_INT);
    }

    @Override
    public void setVersionCode(Integer versionCode) {
        preferences.edit().putInt(PREF_KEY_VERSION_CODE, versionCode).apply();
    }

    @Override
    public boolean showWelcomeScreen() {
        return preferences.getBoolean(PREF_KEY_SHOW_WELCOME_SCREEN, true);
    }

    @Override
    public void setShowWelcomeScreen(boolean showWelcomeScreen) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_WELCOME_SCREEN, showWelcomeScreen).apply();
    }

    @Override
    public boolean showWelcomeInAppScreen() {
        // TODO: Is false because this screen is removed
        return preferences.getBoolean(PREF_KEY_SHOW_WELCOME_IN_APP_SCREEN, false);
    }

    @Override
    public void setShowWelcomeInAppScreen(boolean showWelcomeInAppScreen) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_WELCOME_IN_APP_SCREEN, showWelcomeInAppScreen).apply();
    }

    @Override
    public boolean showTutorialMap() {
        return preferences.getBoolean(PREF_KEY_SHOW_TUTORIAL_MAP, true);
    }

    @Override
    public void setShowTutorialMap(boolean showTutorialMap) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_TUTORIAL_MAP, showTutorialMap).apply();
    }

    @Override
    public boolean showTutorialMyProfile() {
        return preferences.getBoolean(PREF_KEY_SHOW_TUTORIAL_MY_PROFILE, true);
    }

    @Override
    public void setShowTutorialMyProfile(boolean showTutorialMyProfile) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_TUTORIAL_MY_PROFILE, showTutorialMyProfile).apply();
    }

    @Override
    public boolean showTutorialCreateFootprint() {
        return preferences.getBoolean(PREF_KEY_SHOW_TUTORIAL_CREATE_FOOTPRINT, true);
    }

    @Override
    public void setShowTutorialCreateFootprint(boolean showTutorialCreateFootprint) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_TUTORIAL_CREATE_FOOTPRINT, showTutorialCreateFootprint).apply();
    }

    @Override
    public boolean showTutorialMyRanking() {
        return preferences.getBoolean(PREF_KEY_SHOW_TUTORIAL_MY_RANKING, true);
    }

    @Override
    public void setShowTutorialMyRanking(boolean showTutorialMyRanking) {
        preferences.edit().putBoolean(PREF_KEY_SHOW_TUTORIAL_MY_RANKING, showTutorialMyRanking).apply();
    }
}
