package com.raddarapp.presentation.android.fragment;

import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.messaging.FirebaseMessaging;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.view.Presenter;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.VotedType;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.activity.FootprintMainDetailsActivity;
import com.raddarapp.presentation.android.activity.UserProfileActivity;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseMapNormalFragment;
import com.raddarapp.presentation.android.renderer.FootprintsMainAdapteeCollection;
import com.raddarapp.presentation.android.renderer.FootprintsMainRendererBuilder;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.view.PreCachingLayoutManager;
import com.raddarapp.presentation.android.view.floating.FloatingAddVote;
import com.raddarapp.presentation.android.view.map.HorizontalRecyclerViewScrollListenerRosie;
import com.raddarapp.presentation.android.view.radarscan.RadarScanView;
import com.raddarapp.presentation.android.view.radarscan.RandomPointsView;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.android.view.swipe.RVRendererAdapterSwipeable;
import com.raddarapp.presentation.android.view.swipe.SimpleItemTouchHelperCallback;
import com.raddarapp.presentation.general.presenter.FootprintsMainTerritoryPresenter;
import com.raddarapp.presentation.general.presenter.FootprintsMainPresenter;
import com.raddarapp.presentation.general.presenter.UpdateMobileLanguagePresenter;
import com.raddarapp.presentation.general.presenter.UpdateNotificationTokenPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FootprintMainFragment extends BaseMapNormalFragment implements
        FootprintsMainTerritoryPresenter.View, FootprintsMainPresenter.View, UpdateMobileLanguagePresenter.View,
        SimpleItemTouchHelperCallback.OnSwipedListener {

    private static final String DEBUG = "Debug";
    private static final int ANIMATION_DELAY = 500;
    private static final int ANIMATION_COIN = 500;
    private static final int ANIMATION_VOTE_DURATION = 1300;
    private static final int VOTE_ONE_POINT = 1;
    private static float TRANSLATE_VOTE_CAPTURE_ANIMATION;
    private MediaPlayer mediaPlayer;
    private static final int SOUND_RESOURCE_SWIPE_UP = R.raw.swipe_up;
    private static final int SOUND_RESOURCE_SWIPE_DOWN = R.raw.swipe_down;
    private static final int SOUND_RESOURCE_WIN_COIN = R.raw.win_coin;
    private int soundResourceSelected = SOUND_RESOURCE_SWIPE_UP;
    private static final long DELAY_ANIMATION_HANDLER_RADAR_SCAN = 300;

    @BindView(R.id.recycler_footprints) RecyclerView footprintsView;
    @BindView(R.id.radar_scan) RadarScanView radarScanView;
    @BindView(R.id.add_vote) ImageView addVoteView;
    @BindView(R.id.view_clickable) View clickableView;
    @BindView(R.id.total_footprints) TextView totalFootprintsView;
    @BindView(R.id.random_points) RandomPointsView randomRadarScanPoints;
    @BindView(R.id.radar_scan_stroke) View radarScanStroke;
    @BindView(R.id.searching_user_image) CircularImageView searchingUserImageView;

    @Inject @Presenter
    FootprintsMainPresenter footprintsMainPresenter;
    @Inject @Presenter
    FootprintsMainTerritoryPresenter territoryPresenter;
    @Inject @Presenter
    UpdateNotificationTokenPresenter updateNotificationPresenter;
    @Inject @Presenter
    UpdateMobileLanguagePresenter updateMobileLanguagePresenter;

    private RVRendererAdapterSwipeable<FootprintMainViewModelContract> footprintsMainAdapter;
    private FootprintsMainAdapteeCollection footprintsMainCollection;
    private HorizontalRecyclerViewScrollListenerRosie horizontalLoadMoreListener;
    private PreCachingLayoutManager layoutManager;
    private ItemTouchHelper itemTouchHelper;

    private Animation animationRaddarEat;
    private Animation animationTotalFootprintsStart;
    private Animation animationTotalFootprintsEnd;

    private AnimationSet animationsTotalFootprints;

    private boolean isNoMoreFootprintsViewShowed = false;

    private boolean footprintsDrawed = true;
    private boolean loadMoreByScrolled = true;
    private String lastFootprintKeyClicked = "";
    private int totalForceRefreshClicked = 0;
    private boolean showTutorialMap;
    private int sizeScreenX;

    private PreferencesUtils preferencesUtils;

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    final Handler handlerRadarScan = new Handler();
    final Handler handlerSounds = new Handler();

    final Runnable runnableRadarScanPoints = new Runnable() {
        public void run() {
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.addKeyWord("");
            randomRadarScanPoints.show();
        }
    };

    private Runnable runnableSounds = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(getActivity(), soundResourceSelected);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSounds.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    public static FootprintMainFragment newInstance() {
        FootprintMainFragment fragment = new FootprintMainFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        preferencesUtils = new PreferencesUtils(getActivity());
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());
        initializeFonts();
        initializeMap();
        initializeViews();
        initializeAnimations();

        if(BuildConfig.VERSION_CODE > preferencesUtils.getVersionCode()) {
            preferencesUtils.setVersionCode(BuildConfig.VERSION_CODE);
            showDialogNews();
        }

        showTutorialMap = new PreferencesUtils(getActivity()).showTutorialMap();

        sizeScreenX = new DimenUtils().calculateScreenWidth(getActivity().getWindowManager());

        if (showTutorialMap) {
            initializeTutorialMap();
            showTutorialMap = false;
        }
    }

    @Override
    protected void initializeMap() {
        super.initializeMap();
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, totalFootprintsView, messageMapTop, messageMapBottom,
                messageLoadingTerritoryAreaView, territoryView, territoryAreaView, territoryTotalFootprintsView,
                territoryTitleView, territoryTotalFootprintsTitleView, leaderUsernameView, leaderLevelView, territoryParent);
    }

    private void initializeViews() {
        int width = new DimenUtils().calculateScreenWidth(getActivity().getWindowManager());
        layoutManager = new PreCachingLayoutManager(getActivity(), PreCachingLayoutManager.HORIZONTAL, false, width * 2);
        layoutManager.setExtraLayoutSpace(width * 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);
        initializeAdapter();

        horizontalLoadMoreListener = new HorizontalRecyclerViewScrollListenerRosie(layoutManager, () -> footprintsMainPresenter.onLoadMore(true));

        footprintsView.addOnScrollListener(horizontalLoadMoreListener);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(footprintsView);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(footprintsMainAdapter, this);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(footprintsView);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<FootprintMainViewModelContract> rendererBuilder = new FootprintsMainRendererBuilder(footprintsMainPresenter);
        footprintsMainCollection = new FootprintsMainAdapteeCollection();
        footprintsMainAdapter = new RVRendererAdapterSwipeable<>(rendererBuilder, footprintsMainCollection);
        footprintsView.setAdapter(footprintsMainAdapter);
    }

    private void initializeAnimations() {
        DimenUtils dimenUtils = new DimenUtils();
        TRANSLATE_VOTE_CAPTURE_ANIMATION = dimenUtils.dpToPx(getActivity(), 130);

        animationRaddarEat = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_swallow);

        animationTotalFootprintsStart = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_total_footprints_start);
        animationTotalFootprintsEnd = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_total_footprints_end);
        animationMessageTop = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_message_top);

        animationsTotalFootprints = new AnimationSet(false);
        animationsTotalFootprints.setFillEnabled(true);
        animationsTotalFootprints.addAnimation(animationTotalFootprintsStart);
        animationsTotalFootprints.addAnimation(animationTotalFootprintsEnd);

        animationTotalFootprintsEnd.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                hideTotalFootprints();
            }
        });

        animationRaddarEat.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                soundResourceSelected = SOUND_RESOURCE_WIN_COIN;

                if (preferencesUtils.hasSounds()) {
                    handlerSounds.postDelayed(runnableSounds, 10);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                updateCirclesRange(lastLocation);
            }

        });
    }

    private void onRefreshClicked() {
        if (checkGpsEnabledToRefresh()) {
            // TODO: Change to Map footprintsMainPresenter

            if (lastLocation != null) {
                if (!lastLocation.isFromMockProvider()) {
                    totalForceRefreshClicked++;
                    if (footprintsMainAdapter.getItemCount() == 0) {
                        if (!messageMapTop.getText().equals(getString(R.string.welcome_top)) || (messageMapTop.getText().equals(
                                getString(R.string.welcome_top)) && linearMessageMapView.getVisibility() == View.VISIBLE)) {
                            linearMessageMapView.setVisibility(View.GONE);
                            linearMessageMapView.startAnimation(animationMessageTop);
                        }
                    }

                    footprintsDrawed = false;
                    firstMapLoad = false;
                    loadMoreByScrolled = true;

                    if (cleanedMap) {
                        uncleanMap();
                    }

                    hideTotalFootprints();
                    footprintsMainPresenter.setLastLocation(lastLocation);
                    footprintsMainPresenter.forceRefreshing();
                } else {
                    showLocationMockError();
                    hideLoading();
                }
            } else {
                if (checkGpsPermission()) {
                    showLocationError();
                } else {
                    getGpsPermission();
                }

                hideLoading();
            }

        }
    }

    public void onCleanMapClicked() {
        if (cleanedMap) {
            uncleanMap();
        } else {
            cleanMap();
        }
    }

    @Override
    public void onShowTerritoryMainClicked(String zoneKey, boolean fromMap) {
        if (checkGpsEnabledToRefresh()) {
            // TODO: Change to Map footprintsMainPresenter
            if (lastLocation != null && loadingTerritoryAreaView.getVisibility() == View.GONE) {

                if (getActivity() != null) {
                    ((FootprintMainActivity) getActivity()).resetMyUsersRankingAndTerritoryFromMap();
                }

                if (zoneKey.isEmpty()) {
                    if (!cleanedMap) {
                        cleanMap();
                    }

                    if (fromMap) {
                        removeTerritoryAreaPolygon();
                    }

                    territoryPresenter.onShowTerritoryMain(String.valueOf(lastLocation.getLatitude()),
                            String.valueOf(lastLocation.getLongitude()));
                } else {
                    territoryPresenter.onShowTerritoryMainByZone(zoneKey);
                }

            } else {

                if (checkGpsPermission()) {
                    if (loadingTerritoryAreaView.getVisibility() == View.GONE) {
                        showLocationError();
                    }
                } else {
                    getGpsPermission();
                }

                hideLoading();
            }
        }
    }

    public void updateTerritoryFromInfluencers(String zoneKey) {
        if (isCleanedMap()) {
            onShowTerritoryMainClicked(zoneKey, false);
        } else {
            if (checkGpsEnabledToRefresh()) {
                // TODO: Change to Map footprintsMainPresenter
                if (lastLocation != null && loadingTerritoryAreaView.getVisibility() == View.GONE) {

                    if (getActivity() != null) {
                        ((FootprintMainActivity) getActivity()).resetMyUsersRankingAndTerritoryFromMap();
                    }

                    removeTerritoryAreaPolygon();

                    if (zoneKey.isEmpty()) {
                        territoryPresenter.updateTerritory(String.valueOf(lastLocation.getLatitude()),
                                String.valueOf(lastLocation.getLongitude()));
                    } else {
                        territoryPresenter.updateTerritoryByZone(zoneKey);
                    }
                } else {
                    if (checkGpsPermission()) {
                        if (loadingTerritoryAreaView.getVisibility() == View.GONE) {
                            showLocationError();
                        }
                    } else {
                        getGpsPermission();
                    }

                    hideLoading();
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        super.onMapReady(googleMap);
    }

    @Override
    public void hideFootprints() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showFootprintsMain(List<FootprintMainViewModelContract> footprintsMain) {
        footprintsMainAdapter.addAll(footprintsMain);
        footprintsMainAdapter.notifyDataSetChanged();

        isNoMoreFootprintsViewShowed = false;

        if (cleanedMap) {
            cleanMap();
        } else {
            footprintsView.setVisibility(View.VISIBLE);
            linearMessageMapView.setVisibility(View.GONE);
            linearMessageMapView.setAnimation(null);
        }

        updateCirclesRange(lastLocation);

        footprintsDrawed = true;

        int recyclerFootprintHeightPx = new DimenUtils().calculateRecyclerFootprintMainHeight(getActivity(), getActivity().getWindowManager());

        footprintsView.getLayoutParams().height = recyclerFootprintHeightPx;
        clickableView.getLayoutParams().height = recyclerFootprintHeightPx;
    }

    @Override
    public void showHasMore(boolean hasMore) {
        footprintsMainCollection.setShowLoadMore(hasMore);
        horizontalLoadMoreListener.setIsProcessing(false);
        horizontalLoadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearFootprints() {
        footprintsMainAdapter.clear();
        footprintsMainAdapter.notifyDataSetChanged();
        clickableView.setVisibility(View.VISIBLE);
        layoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override
    public void openFootprintMainDetails(String footprintMainKey, long comments, int position) {
        FootprintMainDetailsActivity.open(getActivity(), footprintMainKey, comments);
        lastFootprintKeyClicked = footprintMainKey;
    }

    @Override
    public void openUserProfile(String footprintMainKey, String userKey, int position) {
        UserProfileActivity.openFromFootprintMain(getActivity(), footprintMainKey, userKey);
    }

    @Override
    public void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        USER_RANGE = userProfileViewModel.getRange();
        USER_LEVEL = Integer.valueOf(userProfileViewModel.getLevel());

        if (firstMapLoad) {
            FileUtils fileUtils = new FileUtils(getActivity());

            fileUtils.loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, searchingUserImageView, false);
            checkGpsEnabledToRefresh();
        }

        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).updateUserProfilePreferencesFromMap(userProfileViewModel);
        }

        preferencesUtils.setMyNotificationTopics(userProfileViewModel.getNotificationTopics());

        // FIXME: This should be in the Firebase service but to avoid handling update errors we are going to use this bad code :[
        if (userProfileViewModel.getNotificationToken() == null || !userProfileViewModel.getNotificationToken().equals(preferencesUtils.getMyNotificationToken())) {
            updateNotificationPresenter.updateNotificationToken(preferencesUtils.getMyNotificationToken());
        }

        String language = Locale.getDefault().getLanguage();

        if (userProfileViewModel.getMobileLanguage() != null && !userProfileViewModel.getMobileLanguage().isEmpty() && (
                !userProfileViewModel.getMobileLanguage().equals(language))) {
            updateMobileLanguagePresenter.updateMobileLanguage(preferencesUtils.getMyUserKey(), language);
        }
    }

    @OnClick(R.id.radar_scan)
    public void onRadarScanClicked() {
        if (!radarScanView.isAnimated()) {
            onRefreshClicked();
        }
    }

    @Override
    public void onFirstRadarScanSimulateClicked() {
        onRadarScanClicked();
    }

    private void showRaddarScan() {
        radarScanView.setAnimated(true);
        handlerRadarScan.postDelayed(runnableRadarScanPoints, DELAY_ANIMATION_HANDLER_RADAR_SCAN);
    }

    private void hideRaddarScan() {
        radarScanView.setAnimated(false);
        handlerRadarScan.removeCallbacks(runnableRadarScanPoints);
        randomRadarScanPoints.removeAllKeyWords();
    }

    @Override
    public void moveMapAndAddMaker(LatLngBounds latLngBounds, List<LatLng> listLatLng,
            List<Integer> typeFootprints, List<Integer> usersRelationship, List<Boolean> visibleFootprints) {
        super.moveMapAndAddMaker(latLngBounds, listLatLng, typeFootprints, usersRelationship, visibleFootprints);
    }

    @Override
    public void removeAllMarkers() {
        mapOverlayLayoutView.removeAllMarkers();
    }

    @Override
    public void showEmptyFootprints() {
        updateCirclesRange(lastLocation);

        messageMapTop.setText(getString(R.string.sms_no_more_footprints_top_empty));
        messageMapBottom.setText(getString(R.string.sms_no_more_footprints_bottom));
        footprintsView.setVisibility(View.GONE);
        clickableView.setVisibility(View.GONE);
        isNoMoreFootprintsViewShowed = true;

        if (cleanedMap) {
            linearMessageMapView.setVisibility(View.GONE);
            linearMessageMapView.setAnimation(null);
        } else {
            linearMessageMapView.setVisibility(View.VISIBLE);
            linearMessageMapView.startAnimation(animationTotalFootprintsStart);
            animateCameraEmptyFootprintsMain();
        }

        hideLoading();
    }

    @Override
    public void showNoMoreFootprints() {
        linearMessageMapView.setVisibility(View.VISIBLE);
        messageMapTop.setText(getString(R.string.sms_no_more_footprints_searching_top));
        messageMapBottom.setText(getString(R.string.sms_no_more_footprints_searching_bottom));
        footprintsView.setVisibility(View.GONE);
        clickableView.setVisibility(View.GONE);
        linearMessageMapView.startAnimation(animationTotalFootprintsStart);
        isNoMoreFootprintsViewShowed = true;
        lastLatLngBounds = null;
    }

    @Override
    public void updateAfterVoteMyOwnFootprint() {
        // If I capture my own footprint
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).onReloadMyFootprintsClicked();
            ((FootprintMainActivity) getActivity()).onReloadMyUsersRankingClicked();

        }
    }

    @Override
    public void lastVoteRadarScanClicked() {
        // If I vote the last footprint
        onRadarScanClicked();
    }

    @Override
    public void loadMoreByScrolled(boolean loadMoreByScrolled) {
        this.loadMoreByScrolled = loadMoreByScrolled;
    }

    @Override
    public void onClickedFootprintAnimationSelected(int position) {
        mapOverlayLayoutView.onClickedFootprintAnimationSelected(position);
    }

    @Override
    public void showTotalFootprints(String totalFootprints) {
        if (!totalFootprints.equals("1")) {
            totalFootprintsView.setText(getString(R.string.total_footprints, totalFootprints));
        } else {
            totalFootprintsView.setText(getString(R.string.total_footprints_one, totalFootprints));
        }

        totalFootprintsView.startAnimation(animationsTotalFootprints);
        totalFootprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTotalFootprints() {
        totalFootprintsView.setVisibility(View.GONE);
        totalFootprintsView.setAnimation(null);
    }

    @Override
    public void hideLoading() {
        hideRaddarScan();
    }

    @Override
    public void showLoading() {

        if (footprintsMainAdapter.getItemCount() != 0 && loadMoreByScrolled) {
            clickableView.setVisibility(View.VISIBLE);
        } else {
            clickableView.setVisibility(View.GONE);
            footprintsView.setVisibility(View.GONE);
        }

       showRaddarScan();
    }

    public void cancelRaddarScan() {
        clickableView.setVisibility(View.GONE);
        hideRaddarScan();
    }

    @Override
    public void onSwipedDirectionUp(int position, int screenPositionX) {
        soundResourceSelected = SOUND_RESOURCE_SWIPE_UP;
        if (preferencesUtils.hasSounds()) {
            handlerSounds.postDelayed(runnableSounds, 10);
        }

        boolean isPowerSelected = false;
        long scope;
        String footprintKey;
        String userFootprintKey;

        if (footprintsMainCollection.get(position) instanceof FootprintMainViewModel) {
            isPowerSelected = ((FootprintMainViewModel) footprintsMainCollection.get(position)).isPowerSelected();
            animationSwipeImage(VOTE_ONE_POINT);
            footprintKey = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getKey();
            userFootprintKey = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getUserKey();
            scope = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getScope();
        } else {
            footprintKey = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getKey();
            userFootprintKey = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getUserKey();
            scope = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getScope();
        }

        if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
            footprintsMainPresenter.addVote(userFootprintKey, footprintKey, isPowerSelected ? VotedType.VOTE_SAVE_COLLECTION_LIKE.getValue() : VotedType.VOTE_LIKE.getValue());
        } else {
            initSnackbarError(R.string.error_local_vote_footprint_admin);
        }

        updateAfterSwiped(position, footprintKey, userFootprintKey, VotedType.VOTE_LIKE.getValue());
    }

    @Override
    public void onSwipedDirectionDown(int position, int screenPositionX) {
        soundResourceSelected = SOUND_RESOURCE_SWIPE_DOWN;
        if (preferencesUtils.hasSounds()) {
            handlerSounds.postDelayed(runnableSounds, 10);
        }

        boolean isPowerSelected = false;
        long scope;
        String footprintKey;
        String userFootprintKey;

        if (footprintsMainCollection.get(position) instanceof FootprintMainViewModel) {
            isPowerSelected = ((FootprintMainViewModel) footprintsMainCollection.get(position)).isPowerSelected();
            animationSwipeImage(-VOTE_ONE_POINT);
            footprintKey = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getKey();
            userFootprintKey = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getUserKey();
            scope = ((FootprintMainViewModel) footprintsMainCollection.get(position)).getScope();
        } else {
            footprintKey = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getKey();
            userFootprintKey = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getUserKey();
            scope = ((FootprintInsigniaMainViewModel) footprintsMainCollection.get(position)).getScope();
        }

        if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
            footprintsMainPresenter.addVote(userFootprintKey, footprintKey, isPowerSelected ? VotedType.VOTE_SAVE_COLLECTION_DISLIKE.getValue() : VotedType.VOTE_DISLIKE.getValue());
        } else {
            initSnackbarError(R.string.error_local_vote_footprint_admin);
        }

        updateAfterSwiped(position, footprintKey, userFootprintKey, VotedType.VOTE_DISLIKE.getValue());
    }

    private void updateAfterSwiped(int position, String footprintKey, String userFootprintKey, long voteType) {
        footprintsMainPresenter.removeFootprintKeyFromMap(footprintKey);
        mapOverlayLayoutView.removeSelectedMarker(position);

        if (userFootprintKey.equals(userProfilePreferencesDataSource.getUserKey())) {

            if (voteType == VotedType.VOTE_LIKE.getValue()) {
                userProfilePreferencesDataSource.setTotalLikes(userProfilePreferencesDataSource.getTotalLikes() + 1);
            } else {
                userProfilePreferencesDataSource.setTotalDislikes(userProfilePreferencesDataSource.getTotalDislikes() + 1);
            }

            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() + 1);
        }

        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).updateUserProfilePreferencesFromMap(footprintsMainPresenter.getUserProfilePreferences());
        }
    }

    @Override
    protected void cleanMap() {
        super.cleanMap();
        footprintsView.setVisibility(View.GONE);
        radarScanView.setVisibility(View.GONE);
        radarScanStroke.setVisibility(View.GONE);
        randomRadarScanPoints.setVisibility(View.GONE);
        linearMessageMapView.setVisibility(View.GONE);
        linearMessageMapView.setAnimation(null);
    }

    @Override
    protected void uncleanMap() {
        super.uncleanMap();
        radarScanView.setVisibility(View.VISIBLE);
        radarScanStroke.setVisibility(View.VISIBLE);
        randomRadarScanPoints.setVisibility(View.VISIBLE);
        showTerritoryInfoView.setVisibility(View.GONE);

        removeTerritoryAreaPolygon();

        if (footprintsMainAdapter.getItemCount() != 0) {
            footprintsView.setVisibility(View.VISIBLE);
        }

        if (isNoMoreFootprintsViewShowed ||
                (messageMapTop.getText().toString().equals(getString(R.string.welcome_top)) && totalForceRefreshClicked == 0)) {

            if (messageMapTop.getText().toString().equals(getString(R.string.welcome_top)) &&
                    footprintsMainAdapter.getItemCount() != 0) {
                linearMessageMapView.setVisibility(View.GONE);
                linearMessageMapView.setAnimation(null);
            } else {
                linearMessageMapView.setVisibility(View.VISIBLE);
                linearMessageMapView.startAnimation(animationMessageTop);
            }
        }
    }

    private void animationSwipeImage(int votePoint) {
        FloatingAddVote floatingAddVote = new FloatingAddVote(getActivity());

        /* This is for set the add vote view in some place of the screen
        if (screenPositionX + addVoteView.getWidth() > sizeScreenX) {
            screenPositionX = sizeScreenX - addVoteView.getWidth();
        }

        addVoteView.setX(screenPositionX);*/

        int backgroundRes = votePoint > 0 ? R.layout.add_vote_like : R.layout.add_vote_dislike;
        FloatingElement floatingElementAddVote = new FloatingBuilder()
                .anchorView(addVoteView)
                .targetView(backgroundRes)
                .floatingTransition(new TranslateFloatingTransition(votePoint == VOTE_ONE_POINT ?
                        -TRANSLATE_VOTE_CAPTURE_ANIMATION : TRANSLATE_VOTE_CAPTURE_ANIMATION, ANIMATION_VOTE_DURATION))
                .build();

        floatingAddVote.startFloating(floatingElementAddVote, votePoint, FONT_NAME);
    }

    @Override
    public void onAnimateCameraMapFinishListener() {
        clickableView.setVisibility(View.GONE);
    }

    @Override
    public void onAnimateCameraMapCancelListener() {
        clickableView.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!footprintsDrawed) {
            footprintsMainPresenter.loadData(lastLocation);
        }
    }

    @Override
    public void showTerritoryMainArea(TerritoryArea territoryArea) {
        super.showTerritoryMainArea(territoryArea);
        showTerritoryAreaInfluencers(territoryArea);
    }

    @Override
    public void showWorldTerritoryMainArea() {
        super.showWorldTerritoryMainArea();
    }

    @Override
    public void showTerritoryMainDetails(TerritoryViewModel territoryViewModel) {
        super.showTerritoryMainDetails(territoryViewModel);
        showTerritoryInfluencers(territoryViewModel);
    }

    @Override
    public void showErrorLoadTerritoryMain() {
        initSnackbarError(R.string.error_load_territory);
    }

    @Override
    public void hideLoadingTerritoryMainArea() {
        super.hideLoadingTerritoryMainArea();
    }

    @Override
    public void showLoadingTerritoryMainDetails() {
        super.showLoadingTerritoryMainDetails();
    }

    @Override
    public void showLoadingTerritoryMainArea() {
        super.showLoadingTerritoryArea();
    }

    public boolean isCleanedMap() {
        return cleanedMap;
    }

    public void closeCleanedMap() {
        onCloseClicked();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_COMMENTS) {
            long totalComments = data.getLongExtra(COMMENTS_EXTRA, 0);
            updateComments(totalComments);
        } else if (resultCode == REQUEST_USER_PROFILE_CHANGES) {
            //long totalCompliments = data.getLongExtra(COMPLIMENTS_EXTRA, 0);
            //updateCompliments(totalCompliments);
        }
    }

    private void updateComments(long totalComments) {
        // We can't use item position because we have swipe behavior
        for (FootprintMainViewModelContract footprintMainViewModel : footprintsMainCollection) {

            if (footprintMainViewModel instanceof FootprintMainViewModel) {
                if (((FootprintMainViewModel) footprintMainViewModel).getKey().equals(lastFootprintKeyClicked)) {
                    ((FootprintMainViewModel) footprintMainViewModel).updateComments(totalComments);
                    footprintsMainAdapter.notifyDataSetChanged();
                    break;
                }
            } else if (footprintMainViewModel instanceof FootprintInsigniaMainViewModel) {
                if (((FootprintInsigniaMainViewModel) footprintMainViewModel).getKey().equals(lastFootprintKeyClicked)) {
                    ((FootprintInsigniaMainViewModel) footprintMainViewModel).updateComments(totalComments);
                    footprintsMainAdapter.notifyDataSetChanged();
                    break;
                }
            }
        }
    }

    public void showTerritoryInfluencers(TerritoryViewModel territoryViewModel) {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).showTerritoryInfluencers(territoryViewModel);
        }
    }

    public void showTerritoryAreaInfluencers(TerritoryArea territoryArea) {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).saveTerritoryAreaInfluencers(territoryArea);
        }
    }

    @Override
    protected void initializeShowTerritoryInfluencers(Location location) {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).initializeShowTerritoryInfluencers(location);
        }
    }

    @Override
    protected void updatePresenterLocation() {
        footprintsMainPresenter.setLastLocation(lastLocation);
    }

    public void showTerritoryFromInfluencers(TerritoryViewModel territoryViewModel, TerritoryArea territoryArea) {

        if (getActivity() != null) {
            if (((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().isEmpty() ||
                    !((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().equals(territoryViewModel.getKey())) {
                if (!cleanedMap) {
                    cleanMap();
                }

                removeTerritoryAreaPolygon();

                super.showTerritoryMainDetails(territoryViewModel);

                if (territoryArea != null && territoryArea.getArea() != null) {
                    // Parent key null is the World Map
                    if (territoryViewModel.getParentKey() != null) {
                        territoryPresenter.showTerritoryMainArea(territoryArea);
                    }
                } else {
                    territoryPresenter.onShowTerritoryAreaByTerritory(territoryViewModel.getKey());
                }

                ((FootprintMainActivity) getActivity()).setOnShowTerritoryFromMap(false);
            }
        }
    }

    // TODO: Bad place :[
    public Location getLastLocation() {
        return lastLocation;
    }

    // Herer because in the presenter the getView() throws an Exception :[
    public void updateFootprintsMain(PaginatedCollection<FootprintMain> footprintsMainCollection,
            List<FootprintMainViewModelContract> footprintsMainViewModel) {

        try {
            clearFootprints();
            removeAllMarkers();
            showFootprintsMain(footprintsMainViewModel);
            footprintsMainPresenter.moveMapAndAddMarkerForUpdateFootprintMain(footprintsMainCollection,
                    (latLngBounds, listLatLng, typeFootprints, usersRelationship, visibleFootprints) -> {
                        super.moveMapAndAddMaker(latLngBounds, listLatLng, typeFootprints, usersRelationship, visibleFootprints);
                    });
        } catch(Exception e) {}
    }

    private void showDialogNews() {
        DialogInfo.openNews(getActivity(), getFragmentManager(), DialogInfo.HEIGHT_BIG_DIALOG);
    }

    public FootprintsMainPresenter getFootprintsMainPresenter() {
        return footprintsMainPresenter;
    }

    @Override
    public void showLanguageUpdated(String updatedLanguage) {
        String debug = BuildConfig.IS_DEBUG ? DEBUG : "";
        String LANGUAGE_ES = "es";
        String LANGUAGE_EN = "en";

        FirebaseMessaging.getInstance().unsubscribeFromTopic("allDevices" + debug + "_" +
                (preferencesUtils.getMobileLanguage().equals(LANGUAGE_ES) ? LANGUAGE_ES : LANGUAGE_EN));
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices" + debug + "_" +
                (updatedLanguage.equals(LANGUAGE_ES) ? LANGUAGE_ES : LANGUAGE_EN));

        preferencesUtils.setMobileLanguage(updatedLanguage);
    }
}
