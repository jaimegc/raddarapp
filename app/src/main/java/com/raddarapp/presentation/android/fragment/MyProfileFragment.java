package com.raddarapp.presentation.android.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.piasy.rxandroidaudio.PlayConfig;
import com.github.piasy.rxandroidaudio.RxAudioPlayer;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.enums.PromoCodeType;
import com.raddarapp.domain.model.enums.UserStatusType;
import com.raddarapp.presentation.android.activity.CoinMiningActivity;
import com.raddarapp.presentation.android.activity.ImageDetailsActivity;
import com.raddarapp.presentation.android.activity.MyFootprintsAllActivity;
import com.raddarapp.presentation.android.activity.MyFootprintsCollectionActivity;
import com.raddarapp.presentation.android.activity.MyProfileSettingsActivity;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.activity.MyFootprintDetailsActivity;
import com.raddarapp.presentation.android.activity.SplashActivity;
import com.raddarapp.presentation.android.dialog.DialogActivateDesactivateUser;
import com.raddarapp.presentation.android.dialog.DialogSharePromoCode;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.MyFootprintsAdapteeCollection;
import com.raddarapp.presentation.android.renderer.MyFootprintsRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.utils.TutorialUtils;
import com.raddarapp.presentation.android.view.RVRendererAdapterRemovable;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.ActivateUserPresenter;
import com.raddarapp.presentation.general.presenter.LogoutPresenter;
import com.raddarapp.presentation.general.presenter.MyFootprintsPresenter;
import com.raddarapp.presentation.general.presenter.MyPromoCodePresenter;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.oply.opuslib.OpusService;

public class MyProfileFragment extends BaseNormalFragment implements AppBarLayout.OnOffsetChangedListener,
        MyPromoCodePresenter.View, LogoutPresenter.View, MyFootprintsPresenter.View, ActivateUserPresenter.View,
        DialogActivateDesactivateUser.OnDialogActivateUserListener {

    private static final int METERS_SHARE_PROMO_CODE = 100000;
    private static final int WIN_METERS_SHARE_PROMO_CODE = 20000;
    private static final int REQUEST_STORAGE_PROMO_CODE_PERMISSION = 102;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    protected static final String NEW_COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;
    private static final Random RANDOM = new Random(System.nanoTime());
    private MediaPlayer mediaPlayer;
    private static final int toiletSoundResource = R.raw.toilet_flush;

    @BindView(R.id.recycler_footprints) RecyclerView footprintsView;
    @BindView(R.id.linear_view_normal_blue) LinearLayout linearLoadingView;
    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.text_loading) TextView textLoadingView;

    @BindView(R.id.username_toolbar) TextView usernameToolbarView;
    @BindView(R.id.user_level_toolbar) TextView userLevelToolbarView;
    @BindView(R.id.user_raddar_range_toolbar) TextView userRaddarToolbarRange;
    @BindView(R.id.user_image_toolbar) CircularImageView userImageToolbarView;
    @BindView(R.id.linear_toolbar) LinearLayout linearToolbar;
    @BindView(R.id.play_audio_profile_toolbar) ImageView playAudioProfileToolbarView;
    @BindView(R.id.linear_loading) LinearLayout linearMiniLoadingView;
    @BindView(R.id.user_image) CircularImageView userImageView;
    @BindView(R.id.button_footprints_flavs) Button footprintsFlavsView;
    @BindView(R.id.user_border) ImageView userBorderView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.user_level) TextView userLevelView;
    @BindView(R.id.text_footprints) TextView textMyFootprintsView;
    @BindView(R.id.text_footprints_deaths) TextView textFootprintsDeathsView;
    @BindView(R.id.text_footprints_votes) TextView textFootprintsVotesView;
    @BindView(R.id.me_compliments) TextView meComplimentsView;
    @BindView(R.id.text_me_compliments) TextView textMeComplimentsView;
    @BindView(R.id.footprints) TextView myFootprintsView;
    @BindView(R.id.footprints_deaths) TextView footprintsDeathsView;
    @BindView(R.id.footprints_votes) TextView footprintsVotesView;
    @BindView(R.id.user_raddar_range) TextView userRaddarRangeView;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.user_vote_hearts_toolbar) ImageView icVoteHeartsToolbar;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.progress_audio) ProgressBar progressAudio;
    @BindView(R.id.user_profile_settings) ImageView menuSettingsView;
    @BindView(R.id.linear_footprints) LinearLayout linearFootprints;
    @BindView(R.id.linear_deaths) LinearLayout linearDeaths;
    @BindView(R.id.linear_votes) LinearLayout linearVotes;
    @BindView(R.id.linear_compliments) LinearLayout linearCompliments;
    @BindView(R.id.linear_stars) LinearLayout linearStars;
    @BindView(R.id.coin_mining) ImageView icCoinMiningView;
    @BindView(R.id.promo_code) ImageView icPromoCodeView;
    @BindView(R.id.ic_star) ImageView icStarView;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;

    private RVRendererAdapterRemovable<MyFootprintViewModelContract> myFootprintsAdapter;
    private MyFootprintsAdapteeCollection myFootprintsCollection;
    private ScrollToBottomListener loadMoreListener;

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    private AnimationUtils animationUtils = new AnimationUtils();
    private boolean isExpanded = false;

    private RxAudioPlayer rxAudioPlayer = RxAudioPlayer.getInstance();
    private boolean hasAudioProfile = false;
    private boolean coinMiningScreenClicked = false;
    private boolean osDialogActivateDesactivateUserOpened = false;

    private Long countCompliments = 0L;

    final Handler handlerSounds = new Handler();

    final Runnable runnableToiletFlushSound = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, toiletSoundResource);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSounds.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    @Inject @Presenter
    MyFootprintsPresenter myFootprintsPresenter;
    @Inject @Presenter
    LogoutPresenter logoutPresenter;
    @Inject @Presenter
    MyPromoCodePresenter myPromoCodePresenter;
    @Inject @Presenter
    ActivateUserPresenter activateUserPresenter;

    public static MyProfileFragment newInstance() {
        MyProfileFragment fragment = new MyProfileFragment();
        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());

        initializeFonts();
        initializeViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_profile;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        textLoadingView.setText(R.string.my_footprints_loading);

        fontUtils.applyFont(getActivity(), FONT_NAME, usernameToolbarView, usernameView, userLevelToolbarView,
                userRaddarToolbarRange, textMyFootprintsView, textFootprintsDeathsView, textFootprintsVotesView,
                myFootprintsView, footprintsDeathsView, footprintsVotesView, userRaddarRangeView, userLevelView, textLoadingView,
                meComplimentsView, textMeComplimentsView, footprintsFlavsView, totalStarsView, totalStarsMiniDecimalsView);
    }

    private void initializeViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);
        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> myFootprintsPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
        appBarLayout.addOnOffsetChangedListener(this);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<MyFootprintViewModelContract> rendererBuilder
                = new MyFootprintsRendererBuilder(getActivity(), myFootprintsPresenter, false, context);
        myFootprintsCollection = new MyFootprintsAdapteeCollection();
        myFootprintsAdapter = new RVRendererAdapterRemovable<>(rendererBuilder, myFootprintsCollection);
        footprintsView.setAdapter(myFootprintsAdapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
            if (isExpanded) {
                isExpanded = false;
                animationUtils.alphaAnimation(linearToolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                animationUtils.alphaAnimation(userImageView, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                animationUtils.alphaAnimation(userBorderView, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                animationUtils.alphaAnimation(icStarView, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                animationUtils.alphaAnimation(totalStarsView, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                animationUtils.alphaAnimation(totalStarsMiniDecimalsView, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
            }
        } else {
            if (!isExpanded) {
                isExpanded = true;
                animationUtils.alphaAnimation(linearToolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                animationUtils.alphaAnimation(userImageView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                animationUtils.alphaAnimation(userBorderView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                animationUtils.alphaAnimation(icStarView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                animationUtils.alphaAnimation(totalStarsView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                animationUtils.alphaAnimation(totalStarsMiniDecimalsView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
            }
        }

    }

    @Override
    public void hideMyFootprints() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showMyFootprints(List<MyFootprintViewModelContract> myFootprints) {
        myFootprintsAdapter.addAll(myFootprints);
        myFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        myFootprintsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearMyFootprints() {
        myFootprintsAdapter.clear();
        myFootprintsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openMyFootprintDetails(String myFootprintKey, long comments, int position) {
        MyFootprintDetailsActivity.open(getActivity(), myFootprintKey, comments, false);
    }

    @Override
    public void showEmptyMyFootprints(List<MyFootprintViewModelContract> myFootprintEmptyViewModel) {
        myFootprintsAdapter.addAll(myFootprintEmptyViewModel);
        myFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        NumberUtils numberUtils = new NumberUtils();

        usernameToolbarView.setText(userProfileViewModel.getUsername());
        userLevelToolbarView.setText(userProfileViewModel.getLevel());
        userRaddarToolbarRange.setText(numberUtils.rangeOrScopeToString(userProfileViewModel.getRange()));

        usernameView.setText(userProfileViewModel.getUsername());
        myFootprintsView.setText(userProfileViewModel.getTotalFootprints());
        footprintsDeathsView.setText(userProfileViewModel.getTotalFootprintsDead());
        footprintsVotesView.setText(numberUtils.numberToGroupedString(
                userProfileViewModel.getTotalLikes() + userProfileViewModel.getTotalDislikes()));
        userLevelView.setText(userProfileViewModel.getLevel());
        userRaddarRangeView.setText(numberUtils.rangeOrScopeToString(userProfileViewModel.getRange()));

        if (userProfileViewModel.getRange() >= 0) {
            userRaddarToolbarRange.setTextColor(context.getResources().getColor(R.color.grey3));
            userRaddarRangeView.setTextColor(context.getResources().getColor(R.color.grey3));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
            icVoteHeartsToolbar.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            userRaddarToolbarRange.setTextColor(context.getResources().getColor(R.color.red));
            userRaddarRangeView.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
            icVoteHeartsToolbar.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        if ((userProfileViewModel.getAudio() != null && !userProfileViewModel.getAudio().isEmpty()) ||
                userProfileViewModel.getAudioCache() != null && !userProfileViewModel.getAudioCache().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
            playAudioProfileToolbarView.setImageResource(R.drawable.ic_profile_audio);
            hasAudioProfile = true;
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
            playAudioProfileToolbarView.setImageResource(R.drawable.ic_profile_audio_off);
            hasAudioProfile = false;
        }

        new StarsUtils().calculateStars(totalStarsView, totalStarsMiniDecimalsView,
                userProfileViewModel.getTotalLikes(), userProfileViewModel.getTotalDislikes());

        FileUtils fileUtils = new FileUtils(getActivity());

        fileUtils.loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, userImageView, false);
        fileUtils.loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, userImageToolbarView, true);

        countCompliments = userProfileViewModel.getUserCompliments();

        meComplimentsView.setText(numberUtils.numberToGroupedString(countCompliments));

        if (coinMiningScreenClicked) {
            coinMiningScreenClicked = false;
            // Update footprint main screen. We have blocked update() en FootprintMainPresenter because sometimes produces crashes
            if (getActivity() != null) {
                ((FootprintMainActivity) getActivity()).updateUserProfilePreferences(userProfileViewModel);
            }
        }

        /*if (userProfilePreferencesDataSource.getMyPromoCode().isEmpty()) {
            myPromoCodePresenter.getMyPromoCode(PromoCodeType.REGISTER.getValue());
        }*/

        if (userProfileViewModel.getStatus() == UserStatusType.BANNED.getValue()) {
            logoutPresenter.logout();
        } else if (userProfileViewModel.getStatus() == UserStatusType.INACTIVE.getValue() && !osDialogActivateDesactivateUserOpened) {
            DialogActivateDesactivateUser.openActivateUser(getActivity(), getFragmentManager(), this);
            osDialogActivateDesactivateUserOpened = true;
        }
    }

    @Override
    public void openMyFootprintsAll() {
        MyFootprintsAllActivity.open(getActivity());
    }

    @Override
    public void onMyFootprintDeleted(int position, boolean isDead, long scope, long likes, long dislikes) {
        if (userProfilePreferencesDataSource.hasSounds()) {
            handlerSounds.postDelayed(runnableToiletFlushSound, 10);
        }

        myFootprintsAdapter.onItemDismiss(position);
        userProfilePreferencesDataSource.setTotalFootprints(userProfilePreferencesDataSource.getTotalFootprints() - 1);
        myFootprintsView.setText(String.valueOf(userProfilePreferencesDataSource.getTotalFootprints()));

        userProfilePreferencesDataSource.setTotalLikes(userProfilePreferencesDataSource.getTotalLikes() - likes);
        userProfilePreferencesDataSource.setTotalDislikes(userProfilePreferencesDataSource.getTotalDislikes() - dislikes);

        footprintsVotesView.setText(new NumberUtils().numberToGroupedString(userProfilePreferencesDataSource.getTotalLikes() +
                userProfilePreferencesDataSource.getTotalDislikes()));

        new StarsUtils().calculateStars(totalStarsView, totalStarsMiniDecimalsView,
                userProfilePreferencesDataSource.getTotalLikes(), userProfilePreferencesDataSource.getTotalDislikes());

        if (isDead) {
            userProfilePreferencesDataSource.setTotalFootprintsDead(userProfilePreferencesDataSource.getTotalFootprintsDead() - 1);
            footprintsDeathsView.setText(String.valueOf(userProfilePreferencesDataSource.getTotalFootprintsDead()));
            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() + Math.abs(scope));
        } else {
            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() - scope);
        }

        userRaddarRangeView.setText(String.valueOf(userProfilePreferencesDataSource.getRange()));
        userRaddarToolbarRange.setText(String.valueOf(userProfilePreferencesDataSource.getRange()));

        if (userProfilePreferencesDataSource.getRange() >= 0) {
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
            icVoteHeartsToolbar.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
            icVoteHeartsToolbar.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }
    }

    @Override
    public void showMyFootprintDeletedError() {
        initSnackbarLongError(R.string.error_delete_footprint);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        linearLoadingView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.play_audio_profile)
    public void onPlayAudioProfileClicked() {

        if (progressAudio.getVisibility() == View.GONE && hasAudioProfile) {
            progressAudio.setVisibility(View.VISIBLE);

            // NOTE: OpusService sometimes fails with the first record downloaded from server :[
            //OpusService.play(getActivity(), new FileUtils(getActivity()).getPathMyUserProfileAudioCache());

            new FileUtils(getActivity()).loadMyUserProfileAudioCache(new FileUtils.OnFilePathFinishListener() {
                @Override
                public void filePath(String path) {
                    progressAudio.setVisibility(View.GONE);

                    try {
                        rxAudioPlayer.play(PlayConfig.file(new File(path)).looping(false).build())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(final Disposable disposable) {}

                                    @Override
                                    public void onNext(final Boolean aBoolean) {}

                                    @Override
                                    public void onError(final Throwable throwable) {
                                        // On Android 5.x RxAudioPlayer fails, so we use OpusService
                                        OpusService.play(getActivity(), new FileUtils(getActivity()).getPathMyUserProfileAudioCache());
                                    }

                                    @Override
                                    public void onComplete() {}
                                });
                    } catch (Exception e) {
                        initSnackbarError(R.string.error_local_play_audio);
                        new FileUtils(getActivity()).deleteCacheMyAudioProfile(false);
                        progressAudio.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onError() {
                    initSnackbarError(R.string.error_local_play_audio);
                    new FileUtils(getActivity()).deleteCacheMyAudioProfile(false);
                    progressAudio.setVisibility(View.GONE);
                }
            });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
        OpusService.stopPlaying(getActivity());
        if (rxAudioPlayer != null) {
            rxAudioPlayer.stopPlay();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OpusService.stopPlaying(getActivity());
        if (rxAudioPlayer != null) {
            rxAudioPlayer.stopPlay();
        }
    }

    public void forceRefreshing() {
        myFootprintsPresenter.forceRefreshing();
    }

    @Override
    public void showRefreshing() {
        animationUtils.alphaAnimationWithInitial(linearMiniLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void hideRefreshing() {
        linearMiniLoadingView.setVisibility(View.GONE);
    }

    @OnClick(R.id.user_profile_settings)
    public void onSettingsClicked() {
        MyProfileSettingsActivity.open(getActivity());
    }

    @OnClick(R.id.button_footprints_flavs)
    public void onFootprintsFlavsClicked() {
        MyFootprintsCollectionActivity.open(getActivity());
    }

    @OnClick(R.id.user_image)
    public void onUserImageClicked() {
        if (userProfilePreferencesDataSource.getImage() != null && !userProfilePreferencesDataSource.getImage().isEmpty()) {
            ImageDetailsActivity.openProfileImage(getActivity(),
                    new FileUtils(getActivity()).urlMyUserProfileImage(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE));
        }
    }

    @OnClick(R.id.coin_mining)
    public void onLinearRaddarClicked() {
        coinMiningScreenClicked = true;
        CoinMiningActivity.open(getActivity());
    }

    @OnClick(R.id.promo_code)
    public void onPromoCodeClicked() {
        if (checkStoragePromoCodePermission()) {
            sharePromoCode();
        } else {
            getStoragePromoCodePermission();
        }
    }

    public void initializeTutorialMyProfile() {
        if (getActivity() != null) {
            new TutorialUtils(getActivity(), FONT_NAME, menuSettingsView, playAudioProfileView, linearFootprints, linearDeaths,
                    linearVotes, linearCompliments, linearStars, userRaddarRangeView, ((FootprintMainActivity) getActivity()).getMyProfileIcon(),
                    icCoinMiningView, icPromoCodeView, WIN_METERS_SHARE_PROMO_CODE, METERS_SHARE_PROMO_CODE).initializeTutorialMyProfile();
        }
    }

    private void sharePromoCode() {
        if (!userProfilePreferencesDataSource.getMyPromoCode().isEmpty()) {
            DialogSharePromoCode.open(getFragmentManager(), userProfilePreferencesDataSource.getMyPromoCode());
        } else {
            myPromoCodePresenter.getMyPromoCode(PromoCodeType.REGISTER.getValue());
            initSnackbarLong(R.string.my_footprints_empty_my_promo_code_seconds);
        }
    }

    private void initializeStoragePromoCodePermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.
                        WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            getStoragePromoCodePermission();
        }
    }

    private void getStoragePromoCodePermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE_PROMO_CODE_PERMISSION);
    }

    private boolean checkStoragePromoCodePermission() {
        return ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_STORAGE_PROMO_CODE_PERMISSION){
            if (grantResults.length== 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                sharePromoCode();
            } else {
                showStoragePermissionPromoCodeError();
            }
        }
    }

    @Override
    public void goLogin() {
        if (getActivity() != null) {
            new FileUtils(getActivity()).deleteAllCache();
            SplashActivity.open(getActivity());
        }
    }

    @Override
    public void saveMyPromoCode(String myPromoCode) {
        userProfilePreferencesDataSource.setMyPromoCode(myPromoCode);
    }

    @Override
    public void onActivatedUser() {
        activateUserPresenter.activateUser(userProfilePreferencesDataSource.getUserKey());
    }

    @Override
    public void onExitActivateUser() {
        logoutPresenter.logout();
    }

    @Override
    public void activateUserSuccess() {
        osDialogActivateDesactivateUserOpened = false;
    }

    @Override
    public void showActivateUserError() {
        initSnackbarError(R.string.error_activate_user);
        logoutPresenter.logout();
    }
}