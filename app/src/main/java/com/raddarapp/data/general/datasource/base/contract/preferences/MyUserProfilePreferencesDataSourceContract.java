package com.raddarapp.data.general.datasource.base.contract.preferences;

import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.User;

import java.util.Set;

public interface MyUserProfilePreferencesDataSourceContract {

    String getUserKey();

    void setUserKey(String key);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getUsername();

    void setUsername(String username);

    String getImage();

    void setImage(String image);

    String getImageCache();

    void setImageCache(String imageCache);

    String getAudio();

    void setAudio(String audio);

    String getAudioCache();

    void setAudioCache(String audioCache);

    String getEmail();

    void setEmail(String email);

    long getRange();

    void setRange(long range);

    long getFollowers();

    void setFollowers(long followers);

    long getFollowing();

    void setFollowing(long following);

    long getTotalFootprints();

    void setTotalFootprints(long totalFootprints);

    long getUserCompliments();

    void setUserCompliments(long userCompliments);

    int getLevel();

    void setLevel(int level);

    int getPercentage();

    void setPercentage(int percentage);

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getRefreshToken();

    void setRefreshToken(String refreshToken);

    void setUserProfilePreferencesFull(MyUserProfile userProfile);

    void setUserProfilePreferences(MyUserProfile userProfile);

    MyUserProfile getUserProfilePreferences();

    void logout();

    boolean isLoggedIn();

    User userProfileToUserMapper();

    void setRefreshTokenUpdated(long refreshTokenUpdated);

    long getRefreshTokenUpdated();

    boolean hasSounds();

    void setHasSounds(boolean hasSounds);

    int getRangeMined();

    void setRangeMined(int rangeMined);

    int getRangeNotMined();

    void setRangeNotMined(int rangeNotMined);

    String getMiningDate();

    void setMiningDate(String miningDate);

    int getRangeMinedLocalAccumulated();

    void setRangeMinedLocalAccumulated(int rangeMinedLocalAccumulated);

    int getGender();

    void setGender(int gender);

    String getBirthdate();

    void setBirthdate(String birthdate);

    String getMyPromoCode();

    void setMyPromoCode(String myPromoCode);

    int getRole();

    void setRole(int role);

    int getStatus();

    void setStatus(int status);

    Set<String> getNotificationTopics();

    void setNotificationTopics(Set<String> hasNotificationTopics);

    long getTotalLikes();

    void setTotalLikes(long totalLikes);

    long getTotalDislikes();

    void setTotalDislikes(long totalDislikes);

    long getTotalFootprintsDead();

    void setTotalFootprintsDead(long totalFootprintsDead);

    String getMobileLanguage();

    void setMobileLanguage(String mobileLanguage);
}
