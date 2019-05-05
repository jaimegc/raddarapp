package com.raddarapp.presentation.android.fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.android.activity.ImageDetailsActivity;
import com.raddarapp.presentation.android.activity.UserFootprintDetailsActivity;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.dialog.DialogSocialNetwork;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.UserFootprintsAdapteeCollection;
import com.raddarapp.presentation.android.renderer.UserFootprintsRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.utils.UserUtils;
import com.raddarapp.presentation.android.view.adapter.SimpleMenuUserProfileListenerAdapter;
import com.raddarapp.presentation.android.view.floating.FloatingAddHandclap;
import com.raddarapp.presentation.android.view.menu.FabSpeedDialUserProfile;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.UserFootprintsPresenter;
import com.raddarapp.presentation.general.presenter.UserPresenter;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;
import com.squareup.picasso.Picasso;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

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

public class UserProfileFragment extends BaseNormalFragment implements AppBarLayout.OnOffsetChangedListener,
        UserPresenter.View, UserFootprintsPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String USER_KEY_EXTRA = "UserProfile.UserKey";
    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";

    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private static final int ANIMATION_COMPLIMENT_DURATION = 1500;
    private static final float TRANSLATE_COMPLIMENT_ANIMATION = 400;
    protected static final int REQUEST_USER_PROFILE_CHANGES = 202;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;
    private static final Random RANDOM = new Random(System.nanoTime());
    private MediaPlayer mediaPlayer;
    private static final int soundResources[] = {R.raw.handclap1, R.raw.handclap2, R.raw.handclap3, R.raw.handclap4, R.raw.handclap5};

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

    @BindView(R.id.user_image) CircularImageView userImageView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.user_level) TextView userLevelView;
    @BindView(R.id.text_footprints) TextView textUserFootprintsView;
    @BindView(R.id.text_footprints_deaths) TextView textFootprintsDeathsView;
    @BindView(R.id.text_footprints_votes) TextView textFootprintsVotesView;
    @BindView(R.id.footprints_deaths) TextView footprintsDeathsView;
    @BindView(R.id.footprints_votes) TextView footprintsVotesView;
    @BindView(R.id.footprints) TextView textFootprintsView;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.user_vote_hearts_toolbar) ImageView icVoteHeartsToolbar;
    @BindView(R.id.user_raddar_range) TextView userRaddarRangeView;
    @BindView(R.id.add_compliment) ImageView addComplimentView;
    @BindView(R.id.user_border) ImageView userBorderView;
    @BindView(R.id.progress_audio) ProgressBar progressAudio;
    @BindView(R.id.me_compliments) TextView meComplimentsView;
    @BindView(R.id.text_me_compliments) TextView textMeComplimentsView;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.menu_user_profile) FabSpeedDialUserProfile menuUserProfileView;
    @BindView(R.id.ic_star) ImageView icStarView;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;

    private RVRendererAdapterWithIndex<UserFootprintViewModelContract> userFootprintsAdapter;
    private UserFootprintsAdapteeCollection footprintsCollection;
    private ScrollToBottomListener loadMoreListener;

    private String footprintKey;
    private String userKey;
    private int indexScreen;
    private boolean isFirst = true;
    private String lastFootprintKeyClicked = "";
    private String urlProfileImage = "";

    private PreferencesUtils preferencesUtils;

    private AnimationUtils animationUtils = new AnimationUtils();
    private boolean isExpanded = false;

    private RxAudioPlayer rxAudioPlayer = RxAudioPlayer.getInstance();
    private boolean hasAudioProfile = false;
    private String pathAudioProfile = "";

    private Long countCompliments = 0L;
    private Long totalNewCompliments = 0L;

    final Handler handlerSounds = new Handler();

    final Runnable runnableSounds = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, soundResources[RANDOM.nextInt(soundResources.length)]);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSounds.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    @Inject @Presenter
    UserPresenter userPresenter;

    @Inject
    @Presenter
    UserFootprintsPresenter userFootprintsPresenter;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    public static UserProfileFragment newInstance(String footprintKey, String userKey, int indexScreen) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();

        args.putString(FOOTPRINT_KEY_EXTRA, footprintKey);
        args.putString(USER_KEY_EXTRA, userKey);
        args.putInt(INDEX_SCREEN_EXTRA, indexScreen);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        preferencesUtils = new PreferencesUtils(getActivity());
        footprintKey = getArguments().getString(FOOTPRINT_KEY_EXTRA);
        userKey = getArguments().getString(USER_KEY_EXTRA);
        indexScreen = getArguments().getInt(INDEX_SCREEN_EXTRA);
        userFootprintsPresenter.setUserKey(userKey);
        userPresenter.setUserKey(userKey);
        userPresenter.setFootprintKey(footprintKey);
        userPresenter.setIndexScreen(indexScreen);
        //userPresenter.setFollowing(footprintMainDetailsViewModel.getUserRelationship());
        //userPresenter.initializeFollowingButton();
        //userPresenter.setFootprintMainDetailsPresenter(footprintMainDetailsPresenter);
        
        initializeFonts();
        initializeViews();
        initializeMenu();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_profile;
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        textLoadingView.setText(R.string.user_footprints_loading);

        fontUtils.applyFont(getActivity(), FONT_NAME, usernameToolbarView, usernameView, userLevelToolbarView,
                userRaddarToolbarRange, textUserFootprintsView, footprintsDeathsView, footprintsVotesView,
                textFootprintsView, userRaddarRangeView, userLevelView, textLoadingView, meComplimentsView,
                textMeComplimentsView, totalStarsView, totalStarsMiniDecimalsView, textFootprintsVotesView,
                textFootprintsDeathsView);
    }

    private void initializeViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);
        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> userFootprintsPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
        appBarLayout.addOnOffsetChangedListener(this);

        appBarLayout.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                addComplimentView.setX(event.getX());
                addComplimentView.setY(event.getY());

                if (preferencesUtils.hasSounds()) {
                    handlerSounds.postDelayed(runnableSounds, 10);
                }

                animationAddComplimentAndVote();
            }

            return true;
        });
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<UserFootprintViewModelContract> rendererBuilder =
                new UserFootprintsRendererBuilder(getActivity(), userFootprintsPresenter, false,
                    indexScreen, userKey, footprintKey);
        footprintsCollection = new UserFootprintsAdapteeCollection();
        userFootprintsAdapter = new RVRendererAdapterWithIndex<>(rendererBuilder, footprintsCollection);
        footprintsView.setAdapter(userFootprintsAdapter);
    }

    private void initializeMenu() {
        menuUserProfileView.setMenuListener(new SimpleMenuUserProfileListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_user_profile_follow:
                        DialogInfo.openComingSoon(getActivity(), getFragmentManager(), R.drawable.dialog_user_profile,
                                getString(R.string.help_user_profile_title), getString(R.string.help_user_profile_description), DialogInfo.HEIGHT_NORMAL_DIALOG);
                        break;
                    case R.id.action_user_profile_social_network:
                        DialogSocialNetwork.open(getActivity(), getFragmentManager());
                        break;
                    /*case R.id.action_user_profile_report:
                        break;*/
                }

                return false;
            }

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return super.onPrepareMenu(navigationMenu);
            }
        });
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
                if (isFirst) {
                    isFirst = false;
                    linearToolbar.setVisibility(View.INVISIBLE);
                    userImageView.setVisibility(View.VISIBLE);
                    userBorderView.setVisibility(View.VISIBLE);
                } else {
                    animationUtils.alphaAnimation(linearToolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE, true);
                    animationUtils.alphaAnimation(userImageView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                    animationUtils.alphaAnimation(userBorderView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                    animationUtils.alphaAnimation(icStarView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                    animationUtils.alphaAnimation(totalStarsView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                    animationUtils.alphaAnimation(totalStarsMiniDecimalsView, ALPHA_ANIMATIONS_DURATION, View.VISIBLE, true);
                }
            }
        }
    }

    @Override
    public void hideUserFootprints() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showUserFootprints(List<UserFootprintViewModelContract> userFootprints) {
        userFootprintsAdapter.addAll(userFootprints);
        userFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        footprintsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearUserFootprints() {
        userFootprintsAdapter.clear();
        userFootprintsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openUserFootprintDetails(String footprintKey, long comments, int position) {
        UserFootprintDetailsActivity.open(getActivity(), indexScreen, footprintKey, comments);
        lastFootprintKeyClicked = footprintKey;
    }

    @Override
    public void showEmptyUserFootprints(List<UserFootprintViewModelContract> myFootprintEmptyViewModel) {
        userFootprintsAdapter.addAll(myFootprintEmptyViewModel);
        userFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        linearLoadingView.setVisibility(View.VISIBLE);
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

    @OnClick(R.id.play_audio_profile)
    public void onPlayAudioProfileClicked() {

        if (progressAudio.getVisibility() == View.GONE && hasAudioProfile) {
            progressAudio.setVisibility(View.VISIBLE);

            // NOTE: OpusService sometimes fails with the first record downloaded from server :[
            //OpusService.play(getActivity(), new FileUtils(getActivity()).getPathUserProfileAudioCache());

            new FileUtils(getActivity()).loadUserProfileAudioCache(new FileUtils.OnFilePathFinishListener() {
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
                                        OpusService.play(getActivity(), new FileUtils(getActivity()).getPathUserProfileAudioCache());
                                    }

                                    @Override
                                    public void onComplete() {}
                                });
                    } catch (Exception e) {
                        initSnackbarError(R.string.error_local_play_audio);
                        new FileUtils(getActivity()).deleteCacheAudioProfile();
                        progressAudio.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onError() {
                    initSnackbarError(R.string.error_local_play_audio);
                    new FileUtils(getActivity()).deleteCacheAudioProfile();
                    progressAudio.setVisibility(View.GONE);
                }
            }, pathAudioProfile);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userFootprintsPresenter.deleteCache();
        userPresenter.deleteUserFootprintInLocalCache();
        OpusService.stopPlaying(getActivity());
        new FileUtils(getActivity()).deleteCacheAudioProfile();

        if (rxAudioPlayer != null) {
            rxAudioPlayer.stopPlay();
        }

        if (totalNewCompliments > 0) {
            if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
                userPresenter.updateCompliments(totalNewCompliments);
            }
        }
    }

    @Override
    public void showRefreshing() {}

    @Override
    public void hideRefreshing() {}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_USER_PROFILE_CHANGES) {
            //int totalNewComments = data.getIntExtra(COMMENTS_EXTRA, 0);
            //updateComments(totalNewComments);
        }
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    private void animationAddComplimentAndVote() {
        FloatingAddHandclap floating = new FloatingAddHandclap(getActivity());

        FloatingElement floatingElement = new FloatingBuilder()
                .anchorView(addComplimentView)
                .targetView(R.layout.add_handclap)
                .floatingTransition(new TranslateFloatingTransition(-TRANSLATE_COMPLIMENT_ANIMATION, ANIMATION_COMPLIMENT_DURATION))
                .build();

        floating.startFloatingFromProfile(floatingElement, FONT_NAME);

        countCompliments++;
        totalNewCompliments++;

        meComplimentsView.setText(new NumberUtils().numberToGroupedString(countCompliments));
    }

    private void updateComments(int totalNewComments) {
        for (UserFootprintViewModelContract footprintMainViewModel : footprintsCollection) {
            if (((UserFootprintViewModel) footprintMainViewModel).getKey().equals(lastFootprintKeyClicked)) {
                ((UserFootprintViewModel) footprintMainViewModel).updateComments(totalNewComments);
                userFootprintsAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    public Long getTotalNewCompliments() {
        return totalNewCompliments;
    }

    @Override
    public void showFollowingUser() {
        //followUserView.setText(getString(R.string.btn_following));
    }

    @Override
    public void showUnfollowingUser() {
        //followUserView.setText(getString(R.string.btn_unfollowing));
    }

    @Override
    public void showFollowingError() {

    }

    @Override
    public void showUnfollowingError() {

    }

    @Override
    public void showUserProfile(UserProfileViewModel userProfileViewModel) {
        NumberUtils numberUtils = new NumberUtils();
        String range = numberUtils.rangeOrScopeToString(userProfileViewModel.getRange());
        UserUtils userUtils = new UserUtils();

        usernameToolbarView.setText(userProfileViewModel.getUsername());
        userLevelToolbarView.setText(String.valueOf(userProfileViewModel.getLevel()));
        userRaddarToolbarRange.setText(range);

        usernameView.setText(userProfileViewModel.getUsername());
        textFootprintsView.setText(String.valueOf(userProfileViewModel.getTotalFootprints()));
        userLevelView.setText(String.valueOf(userProfileViewModel.getLevel()));
        userRaddarRangeView.setText(range);

        footprintsDeathsView.setText(userProfileViewModel.getTotalFootprintsDead());
        footprintsVotesView.setText(numberUtils.numberToGroupedString(
                userProfileViewModel.getTotalLikes() + userProfileViewModel.getTotalDislikes()));

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

        if ((userProfileViewModel.getAudio() != null && !userProfileViewModel.getAudio().isEmpty())) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
            playAudioProfileToolbarView.setImageResource(R.drawable.ic_profile_audio);
            hasAudioProfile = true;
            pathAudioProfile = userProfileViewModel.getAudio();
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
            playAudioProfileToolbarView.setImageResource(R.drawable.ic_profile_audio_off);
            hasAudioProfile = false;
        }

        new StarsUtils().calculateStars(totalStarsView, totalStarsMiniDecimalsView,
                userProfileViewModel.getTotalLikes(), userProfileViewModel.getTotalDislikes());

        if (userProfileViewModel.getImage() != null && !userProfileViewModel.getImage().isEmpty()) {
            Picasso.with(getActivity())
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + userProfileViewModel.getImage())
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(userImageView);

            urlProfileImage = SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + userProfileViewModel.getImage();
        } else {
            userImageView.setImageResource(R.drawable.placeholder_profile_big);
        }

        Picasso.with(context)
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + userProfileViewModel.getImage() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_card)
                .fit()
                .centerCrop()
                .into(userImageToolbarView);

        countCompliments = userProfileViewModel.getUserCompliments();

        meComplimentsView.setText(numberUtils.numberToGroupedString(countCompliments));

        if (totalNewCompliments > 0) {
            if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
                countCompliments += totalNewCompliments;
                meComplimentsView.setText(new NumberUtils().numberToGroupedString(countCompliments));
                userPresenter.updateCompliments(totalNewCompliments);
                totalNewCompliments = 0l;
            }
        }
    }

    @OnClick(R.id.user_image)
    public void onUserImageClicked() {
        if (!urlProfileImage.isEmpty()) {

            ImageDetailsActivity.openProfileImage(getActivity(), urlProfileImage);
        }
    }
}