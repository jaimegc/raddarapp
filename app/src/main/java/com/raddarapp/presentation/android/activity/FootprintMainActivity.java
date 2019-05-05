package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.GsonBuilder;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.view.Presenter;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.NotificationUrlDto;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.adapter.FragmentAdapter;
import com.raddarapp.presentation.android.di.module.FootprintMainModule;
import com.raddarapp.presentation.android.fragment.FootprintMainFragment;
import com.raddarapp.presentation.android.fragment.MyFriendsFragment;
import com.raddarapp.presentation.android.fragment.MyUsersRankingFragment;
import com.raddarapp.presentation.android.fragment.MyProfileFragment;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.view.BottomNavigationViewEx;
import com.raddarapp.presentation.android.view.ViewPagerCustom;
import com.raddarapp.presentation.general.presenter.NotificationsPresenter;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnLongClick;

public class FootprintMainActivity extends BaseNormalActivity implements NotificationsPresenter.View {

    private static final String NOTIFICATION_TOPIC_URL = "url";
    private static final String FOOTPRINT_MAIN_CLEAR_HISTORY = "FootprintMain.FootprintClearHistory";

    private static final int REQUEST_NEW_COMMENTS_FROM_FOOTPRINT_MAIN_DETAILS = 200;
    private static final int REQUEST_NEW_COMMENTS_FROM_MY_FOOTPRINT_DETAILS = 201;
    private static final int REQUEST_USER_PROFILE_CHANGES = 202;
    private static final int UPDATE_MY_USERS_RANKING_AFTER_VOTE_MY_OWN_FOOTPRINT = 10000;
    private static final int MY_PROFILE_ICON_POSITION = 4;
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    private static final int REQUEST_STORAGE_PERMISSION = 102;
    private static final String COMMENTS_SCREEN_LOADED_FROM_NOTIFICATION_EXTRA = "FootprintDetails.ScreenLoadedFromNotifictionExtra";

    // Notifications
    private static final String NOTIFICATION_TOPIC = "notification_topic";
    private static final String NOTIFICATION_CONTENT = "content";

    private String notificationTopic;
    private String notificationContent;

    @BindView(R.id.navigation) BottomNavigationViewEx navigation;
    @BindView(R.id.create_footprint) View createFootprint;
    @BindView(R.id.viewpager) ViewPagerCustom viewPager;
    @BindView(R.id.force_refresh_my_footprints) View forceRefreshMyFootprints;
    @BindView(R.id.footprint_image) ImageView footprintImage;

    private static final float SIZE_RELOAD_BUTTON = 40;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private int actualFragmentPosition = 0;

    private FootprintMainFragment footprintMainFragment;
    private MyUsersRankingFragment myUsersRankingFragment;
    private MyFriendsFragment myFriendsFragment;
    private MyProfileFragment myProfileFragment;

    private List<String> parentTerritoryKeyList = new LinkedList<>();
    private List<String> actualTerritoryKeyList = new LinkedList<>();
    private String firstTerritoryKey = "";

    private boolean showBackground = false;
    private boolean showTutorialMyProfile;
    private boolean showTutorialMyRanking;
    // To return users ranking when we have clicked in show territory from this tab
    private boolean onShowTerritoryFromMap = true;

    @Inject @Presenter
    NotificationsPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new FootprintMainModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();
        initializeNavigation();
        initializeFonts();
        Bundle extras = getIntent().getExtras();
        showBackground = extras.getBoolean(FOOTPRINT_MAIN_CLEAR_HISTORY);
        notificationTopic = extras.getString(NOTIFICATION_TOPIC);
        notificationContent = extras.getString(NOTIFICATION_CONTENT);
        RaddarApplication.setFootprintMainActivity(this);
        PreferencesUtils preferencesUtils = new PreferencesUtils(this);
        showTutorialMyProfile = preferencesUtils.showTutorialMyProfile();
        showTutorialMyRanking = preferencesUtils.showTutorialMyRanking();

        // Here because app crash if we open a web from presenter
        if (notificationTopic != null && notificationContent != null) {
             if (!notificationTopic.equals(NOTIFICATION_TOPIC_URL)) {
                 presenter.handleNotifications(notificationTopic, notificationContent);
             } else {
                 openWebFromNotification(notificationContent);
             }
        }
    }

    private void initializeNavigation() {
        navigation.enableAnimation(false);
        navigation.enableItemShiftingMode(true);
        navigation.enableShiftingMode(false);

        navigation.setIconSizeAt(2, SIZE_RELOAD_BUTTON, SIZE_RELOAD_BUTTON);
        navigation.setIconTintList(2, getResources().getColorStateList(R.color.black));

        footprintMainFragment = FootprintMainFragment.newInstance();
        myUsersRankingFragment = MyUsersRankingFragment.newInstance();
        myFriendsFragment = MyFriendsFragment.newInstance();
        myProfileFragment = MyProfileFragment.newInstance();

        FragmentAdapter adapter = new FragmentAdapter(getFragmentManager());

        adapter.addFragment(footprintMainFragment);
        adapter.addFragment(myUsersRankingFragment);
        adapter.addFragment(myFriendsFragment);
        adapter.addFragment(myProfileFragment);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setOnTouchListener((v, event) -> true);
        viewPager.setPagingEnabled(false);

        tintNavigationIcons(0, 1, 3, 4);

        navigation.setOnNavigationItemSelectedListener(item -> {
            onShowTerritoryFromMap = true;

            if (item.getItemId() != R.id.navigation_create_footprint) {
                switch (item.getItemId()) {
                    case R.id.navigation_vote:
                        viewPager.setPagingEnabled(true);
                        viewPager.setCurrentItem(0, false);
                        viewPager.setPagingEnabled(false);
                        navigation.getMenu().getItem(0).setChecked(true);
                        actualFragmentPosition = 0;
                        disableforceRefreshMyFootprints();
                        tintNavigationIcons(0, 1, 3, 4);
                        break;
                    case R.id.navigation_influencers:
                        viewPager.setPagingEnabled(true);
                        viewPager.setCurrentItem(1, false);
                        viewPager.setPagingEnabled(false);
                        navigation.getMenu().getItem(1).setChecked(true);
                        navigation.getMenu().getItem(0).setChecked(false);
                        actualFragmentPosition = 1;
                        disableforceRefreshMyFootprints();
                        tintNavigationIcons(1, 0, 3, 4);
                        closeMenus();

                        if (showTutorialMyRanking) {
                            if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
                                myUsersRankingFragment.initializeTutorialMyRanking();
                                showTutorialMyRanking = false;
                            }
                        }

                        break;
                    case R.id.navigation_my_friends:
                        viewPager.setPagingEnabled(true);
                        viewPager.setCurrentItem(2, false);
                        viewPager.setPagingEnabled(false);
                        navigation.getMenu().getItem(3).setChecked(true);
                        navigation.getMenu().getItem(0).setChecked(false);
                        actualFragmentPosition = 2;
                        disableforceRefreshMyFootprints();
                        tintNavigationIcons(3, 0, 1, 4);
                        closeMenus();
                        break;
                    case R.id.navigation_my_profile:
                        viewPager.setPagingEnabled(true);
                        viewPager.setCurrentItem(3, false);
                        viewPager.setPagingEnabled(false);
                        navigation.getMenu().getItem(4).setChecked(true);
                        navigation.getMenu().getItem(0).setChecked(false);
                        actualFragmentPosition = 3;
                        enableReloadMyFootprints();
                        tintNavigationIcons(4, 0, 1, 3);
                        closeMenus();

                        if (showTutorialMyProfile) {
                            if (myProfileFragment != null && myProfileFragment.isAdded()) {
                                myProfileFragment.initializeTutorialMyProfile();
                                showTutorialMyProfile = false;
                            }
                        }
                        break;
                }

                return true;
            } else {
                if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
                    footprintMainFragment.cancelRaddarScan();
                }

                CreateFootprintActivity.open(this);

                return false;
            }
        });
    }

    @OnLongClick(R.id.force_refresh_my_footprints)
    public boolean onReloadMyFootprintsClicked() {
        if (myProfileFragment != null && myProfileFragment.isAdded()) {
            myProfileFragment.forceRefreshing();
        }

        return true;
    }

    public boolean onReloadMyUsersRankingClicked() {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            // We execute this method after 10 seconds because the server has delay
            // when updates the ranking
            new Handler().postDelayed(() -> myUsersRankingFragment.onReloadMyUsersRankingClicked(),
                    UPDATE_MY_USERS_RANKING_AFTER_VOTE_MY_OWN_FOOTPRINT);
        }

        return true;
    }

    private void initializeFonts() {
        Menu menuNavigation = navigation.getMenu();
        FontUtils fontUtils = new FontUtils();

        for (int i = 0;i < menuNavigation.size(); i++) {
            MenuItem mi = menuNavigation.getItem(i);
            fontUtils.applyFontMenuItem(this, mi, FONT_NAME);
        }
    }

    @Override
    public void onBackPressed() {
        if (actualFragmentPosition == 0) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded() && footprintMainFragment.isCleanedMap()) {
                footprintMainFragment.closeCleanedMap();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }

        } else {
            goHome();
        }
    }

    private void goHome() {
        viewPager.setPagingEnabled(true);
        viewPager.setCurrentItem(0, false);
        viewPager.setPagingEnabled(false);
        // To avoid false raddar scan position
        navigation.getMenu().getItem(actualFragmentPosition > 1 ? actualFragmentPosition + 1 : actualFragmentPosition).setChecked(false);
        navigation.getMenu().getItem(0).setChecked(true);
        actualFragmentPosition = 0;
        disableforceRefreshMyFootprints();
        tintNavigationIcons(0, 1, 3, 4);
    }

    private void closeMenus() {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            myUsersRankingFragment.closeMenus();
        }
    }

    public static void open(Context context, boolean clearAllHistory, boolean showBackground) {
        Intent intent = new Intent(context, FootprintMainActivity.class);

        if (clearAllHistory) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }

        intent.putExtra(FOOTPRINT_MAIN_CLEAR_HISTORY, showBackground);

        context.startActivity(intent);
    }

    public static void openFromNotification(Context context, Intent intentData) {
        Intent intent = new Intent(context, FootprintMainActivity.class);

        intent.putExtra(FOOTPRINT_MAIN_CLEAR_HISTORY, false);
        intent.putExtra(NOTIFICATION_TOPIC, intentData.getStringExtra(NOTIFICATION_TOPIC));
        intent.putExtra(NOTIFICATION_CONTENT, intentData.getStringExtra(NOTIFICATION_CONTENT));

        context.startActivity(intent);
    }

    private void disableforceRefreshMyFootprints() {
        forceRefreshMyFootprints.setClickable(false);
        forceRefreshMyFootprints.setFocusable(false);
        forceRefreshMyFootprints.setVisibility(View.GONE);
    }

    private void enableReloadMyFootprints() {
        forceRefreshMyFootprints.setClickable(true);
        forceRefreshMyFootprints.setFocusable(true);
        forceRefreshMyFootprints.setVisibility(View.VISIBLE);
    }

    private void tintNavigationIcons(int positionSelected, int positionNoSelected1, int positionNoSelected2, int positionNoSelected3) {
        navigation.getIconAt(positionSelected).setImageTintList(getResources().getColorStateList(R.color.blue_dark_raddar));
        navigation.getIconAt(positionNoSelected1).setImageTintList(getResources().getColorStateList(R.color.grey3));
        navigation.getIconAt(positionNoSelected2).setImageTintList(getResources().getColorStateList(R.color.grey3));
        navigation.getIconAt(positionNoSelected3).setImageTintList(getResources().getColorStateList(R.color.grey3));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_NEW_COMMENTS_FROM_FOOTPRINT_MAIN_DETAILS) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded() && data != null) {
                footprintMainFragment.onActivityResult(requestCode, resultCode, data);
            }
        } else if (resultCode == REQUEST_NEW_COMMENTS_FROM_MY_FOOTPRINT_DETAILS) {
            if (myProfileFragment != null && myProfileFragment.isAdded() && data != null) {

                boolean fromNotification = data.getBooleanExtra(COMMENTS_SCREEN_LOADED_FROM_NOTIFICATION_EXTRA, false);

                // When we open my footprint details from notification (only with the app in background), we use the cache so
                // we must force refreshing to load all my footprints correctly. With the app in foreground fromNotification
                // will be false
                if (fromNotification) {
                    onReloadMyFootprintsClicked();
                }

                myProfileFragment.onActivityResult(requestCode, resultCode, data);
            }
        } else if (resultCode == REQUEST_USER_PROFILE_CHANGES) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded() && data != null) {
                footprintMainFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
                footprintMainFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        } else if (requestCode == REQUEST_STORAGE_PERMISSION) {
            /*if (myProfileFragment != null && myProfileFragment.isAdded()) {
                myProfileFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }*/

            if (myFriendsFragment != null && myFriendsFragment.isAdded()) {
                myFriendsFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public boolean isOnShowTerritoryFromMap() {
        return onShowTerritoryFromMap;
    }

    public void setOnShowTerritoryFromMap(boolean onShowTerritoryFromMap) {
        this.onShowTerritoryFromMap = onShowTerritoryFromMap;
    }

    public void showTerritoryInfluencers(TerritoryViewModel territoryViewModel) {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            myUsersRankingFragment.showTerritoryInfluencers(territoryViewModel);
        }
    }

    public void saveTerritoryAreaInfluencers(TerritoryArea territoryArea) {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            myUsersRankingFragment.saveTerritoryAreaInfluencers(territoryArea);
        }
    }

    public void showTerritoryFromInfluencers(TerritoryViewModel territoryViewModel, TerritoryArea territoryArea) {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            footprintMainFragment.showTerritoryFromInfluencers(territoryViewModel, territoryArea);
            goHome();
        }
    }

    public void updateUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            footprintMainFragment.showUserProfilePreferences(userProfileViewModel);
        }
    }

    public void refreshUbicationFromInfluencers() {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            resetAllTerritories();

            footprintMainFragment.updateTerritoryFromInfluencers("");
        }
    }

    public void updateTerritoryFromInfluencers(String zoneKey) {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            footprintMainFragment.updateTerritoryFromInfluencers(zoneKey);
        }
    }

    public void resetMyUsersRankingAndTerritoryFromMap() {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            myUsersRankingFragment.resetMyUsersRankingAndTerritoryFromMap();
        }
    }

    public void initializeShowTerritoryInfluencers(Location location) {
        if (myUsersRankingFragment != null && myUsersRankingFragment.isAdded()) {
            myUsersRankingFragment.initializeShowTerritoryInfluencers(location);
        }
    }

    public void updateMyFootprints(List<MyFootprintViewModelContract> myFootprintsViewModel) {
        if (myProfileFragment != null && myProfileFragment.isAdded()) {
            myProfileFragment.clearMyFootprints();
            myProfileFragment.showMyFootprints(myFootprintsViewModel);
        }
    }

    public void updateFootprintsMain(PaginatedCollection<FootprintMain> footprintsMainCollection,
            List<FootprintMainViewModelContract> footprintsMainViewModel) {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            footprintMainFragment.updateFootprintsMain(footprintsMainCollection, footprintsMainViewModel);
        }
    }

    public Location getLastLocation() {
        if (footprintMainFragment != null && footprintMainFragment.isAdded()) {
            return footprintMainFragment.getLastLocation();
        }

        return null;
    }

    public void onInfluencersTabClicked() {
        viewPager.setPagingEnabled(true);
        viewPager.setCurrentItem(1, false);
        viewPager.setPagingEnabled(false);
        navigation.getMenu().getItem(1).setChecked(false);
        actualFragmentPosition = 1;
        tintNavigationIcons(1, 0, 3, 4);
        closeMenus();
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(showBackground ? R.style.ActivityBackground : R.style.AppTheme);
    }

    public void updateUserProfilePreferencesFromMap(MyUserProfileViewModel userProfileViewModel) {
        if (myProfileFragment != null && myProfileFragment.isAdded()) {
            myProfileFragment.showUserProfilePreferences(userProfileViewModel);
        }
    }

    public String onShowTerritoryParentClicked() {
        String territoryKey = "";

        if (parentTerritoryKeyList.size() > 0 && !parentTerritoryKeyList.get(parentTerritoryKeyList.size() - 1).isEmpty()) {
            territoryKey = parentTerritoryKeyList.get(parentTerritoryKeyList.size() - 1);
        }

        return territoryKey;
    }

    public String onShowTerritorySonClicked() {
        String territoryKey = "";

        if (parentTerritoryKeyList.size() > 1 && actualTerritoryKeyList.size() > 1) {
            parentTerritoryKeyList.remove(parentTerritoryKeyList.size() - 1);
            actualTerritoryKeyList.remove(actualTerritoryKeyList.size() - 1);
            territoryKey = actualTerritoryKeyList.get(actualTerritoryKeyList.size() - 1);
        }

        return territoryKey;
    }

    public List<String> getParentTerritoryKeyList() {
        return parentTerritoryKeyList;
    }

    public List<String> getActualTerritoryKeyList() {
        return actualTerritoryKeyList;
    }

    public String getFirstTerritoryKey() {
        return firstTerritoryKey;
    }

    public void setFirstTerritoryKey(String firstTerritoryKey) {
        this.firstTerritoryKey = firstTerritoryKey;
    }

    public void resetAllTerritories() {
        parentTerritoryKeyList.clear();
        actualTerritoryKeyList.clear();
        firstTerritoryKey = "";
    }

    public ImageView getFootprintImage() {
        return footprintImage;
    }

    public ImageView getMyProfileIcon() {
        return navigation.getIconAt(MY_PROFILE_ICON_POSITION);
    }

    @Override
    public void openMyFootprintFromNotification(String myFootprintKey, long comments) {
        MyFootprintDetailsActivity.open(this, myFootprintKey, comments, true);
    }

    @Override
    public void openWebFromNotification(String notificationContent) {
        // This dto should be in data layer
        NotificationUrlDto notificationUrlDto = new GsonBuilder().create().fromJson(notificationContent, NotificationUrlDto.class);
        Uri webpage = Uri.parse(notificationUrlDto.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}
