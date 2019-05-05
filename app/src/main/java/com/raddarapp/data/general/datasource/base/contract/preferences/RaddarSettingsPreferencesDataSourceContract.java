package com.raddarapp.data.general.datasource.base.contract.preferences;

public interface RaddarSettingsPreferencesDataSourceContract {

    Integer getVersionCode();

    void setVersionCode(Integer versionCode);

    boolean showWelcomeScreen();

    void setShowWelcomeScreen(boolean showWelcomeScreen);

    boolean showWelcomeInAppScreen();

    void setShowWelcomeInAppScreen(boolean showWelcomeInAppScreen);

    boolean showTutorialMap();

    void setShowTutorialMap(boolean showTutorialMap);

    boolean showTutorialMyProfile();

    void setShowTutorialMyProfile(boolean showTutorialMyProfile);

    boolean showTutorialCreateFootprint();

    void setShowTutorialCreateFootprint(boolean showTutorialMyRanking);

    boolean showTutorialMyRanking();

    void setShowTutorialMyRanking(boolean showTutorialMyRanking);
}
