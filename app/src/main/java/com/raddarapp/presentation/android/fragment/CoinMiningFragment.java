package com.raddarapp.presentation.android.fragment;


import android.media.MediaPlayer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.CountDownUtils;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.view.CountAnimationTextView;
import com.raddarapp.presentation.android.view.floating.FloatingAddCoinMined;
import com.raddarapp.presentation.android.view.floating.FloatingAddCoinMinedWon;
import com.raddarapp.presentation.general.presenter.CoinMiningPresenter;
import com.raddarapp.presentation.general.viewmodel.CoinMiningViewModel;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;
import com.ufreedom.floatingview.FloatingBuilder;
import com.ufreedom.floatingview.FloatingElement;
import com.ufreedom.floatingview.effect.TranslateFloatingTransition;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class CoinMiningFragment extends BaseNormalFragment implements CountDownUtils.CountDownListener, CoinMiningPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final int MAXIMUM_RANGE_MINED_PER_DAY = 20000;
    private static final int ANIMATION_COIN_MINED_DURATION = 1500;
    private static final float TRANSLATE_COIN_MINED_ANIMATION = 500;
    private static final int ANIMATION_COIN_MINED_WON_DURATION = 2200;
    private static float TRANSLATE_ANIMATION_COIN_MINED_ANIMATION;
    private static final int TRANSLATION_TOP_COIN_MINED_WON_DP = 150;

    @BindView(R.id.raddar_range) CountAnimationTextView raddarRangeView;
    @BindView(R.id.level) TextView levelView;
    @BindView(R.id.mined_range) TextView rangeMinedView;
    @BindView(R.id.text_time) TextView textTimeView;
    @BindView(R.id.mined_range_today) CountAnimationTextView rangeMinedTodayView;
    @BindView(R.id.area_mining) RelativeLayout areaMining;
    @BindView(R.id.add_coin_mined) ImageView addCoinMinedView;
    @BindView(R.id.add_coin_mined_won) ImageView addCoinMinedWonView;
    @BindView(R.id.linear_loading) LinearLayout linearMiniLoadingView;
    @BindView(R.id.exchange) Button buttonExchangeView;
    @BindView(R.id.progress_bar_level) ProgressBar progressBarLevelView;

    private MediaPlayer mediaPlayer;
    private static final int soundCoinMinedResource = R.raw.coin_mining;
    private static final int soundExchangeResource = R.raw.win_coin;

    private NumberUtils numberUtils = new NumberUtils();
    private AnimationUtils animationUtils = new AnimationUtils();
    private CountDownUtils countDownUtils = new CountDownUtils();
    private long USER_RANGE = 0;
    private int RANGE_MINED_NOW = 0;
    private int RANGE_MINED_TOTAL = 0;

    @Inject @Presenter
    CoinMiningPresenter presenter;

    final Handler handlerSound = new Handler();

    final Runnable runnableSounds = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, soundCoinMinedResource);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSound.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    final Runnable runnableCoinMinedSound = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, soundExchangeResource);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSound.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    public static CoinMiningFragment newInstance() {
        CoinMiningFragment fragment = new CoinMiningFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coin_mining;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());
        raddarRangeView.setText(numberUtils.rangeOrScopeToString(USER_RANGE));
        rangeMinedTodayView.setText(numberUtils.rangeOrScopeToStringPlus(0));

        TRANSLATE_ANIMATION_COIN_MINED_ANIMATION = new DimenUtils().dpToPx(getActivity(), TRANSLATION_TOP_COIN_MINED_WON_DP);

        areaMining.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                addCoinMinedView.setX(event.getX());
                addCoinMinedView.setY(event.getY());

                if (userProfilePreferencesDataSource.hasSounds()) {
                    handlerSound.postDelayed(runnableSounds, 10);
                }

                animationAddCoinMined();

                RANGE_MINED_NOW++;
                rangeMinedView.setText(numberUtils.rangeOrScopeToStringPlus(RANGE_MINED_NOW));
            }

            return true;
        });
    }

    private void animationAddCoinMined() {
        FloatingAddCoinMined floating = new FloatingAddCoinMined(getActivity());

        FloatingElement floatingElement = new FloatingBuilder()
                .anchorView(addCoinMinedView)
                .targetView(R.layout.add_coin_mined)
                .floatingTransition(new TranslateFloatingTransition(-TRANSLATE_COIN_MINED_ANIMATION, ANIMATION_COIN_MINED_DURATION))
                .build();

        floating.startFloating(floatingElement);
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    public void hideLoading() {
        linearMiniLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(linearMiniLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void showUserProfilePreferences(MyUserProfileViewModel userProfileViewModel) {
        levelView.setText(userProfileViewModel.getLevel());
        RANGE_MINED_NOW = userProfileViewModel.getRangeMinedLocalAccumulated();
        USER_RANGE = userProfileViewModel.getRange();
        rangeMinedView.setText(numberUtils.rangeOrScopeToStringPlus(RANGE_MINED_NOW));
        raddarRangeView.setText(numberUtils.rangeOrScopeToString(USER_RANGE));
        progressBarLevelView.setProgress(userProfileViewModel.getPercentage());
    }

    @Override
    public void showRangeMined(CoinMiningViewModel coinMiningViewModel, boolean maximumRangeMinedPerDay) {

        RANGE_MINED_TOTAL = coinMiningViewModel.getRangeMined();

        if (!maximumRangeMinedPerDay) {
            presenter.setExchangeSent(false);
            rangeMinedTodayView.countAnimationRangeMined(
                    userProfilePreferencesDataSource.getRangeMined(), coinMiningViewModel.getRangeMined(), 0);

            userProfilePreferencesDataSource.setRangeMined(coinMiningViewModel.getRangeMined());
            userProfilePreferencesDataSource.setRangeNotMined(coinMiningViewModel.getRangeNotMined());
            userProfilePreferencesDataSource.setMiningDate(coinMiningViewModel.getMiningDate());
            userProfilePreferencesDataSource.setLevel(coinMiningViewModel.getLevel());
            userProfilePreferencesDataSource.setRange(coinMiningViewModel.getNewRange());
            userProfilePreferencesDataSource.setRangeMinedLocalAccumulated(coinMiningViewModel.getRangeNotMined());
            userProfilePreferencesDataSource.setPercentage(coinMiningViewModel.getNewPercentage());

            if (userProfilePreferencesDataSource.hasSounds()) {
                handlerSound.postDelayed(runnableCoinMinedSound, 10);
            }

            FloatingAddCoinMinedWon floatingAddCoinMinedWon = new FloatingAddCoinMinedWon(getActivity());

            FloatingElement floatingElementCoinMinedWon = new FloatingBuilder()
                    .anchorView(addCoinMinedWonView)
                    .targetView(R.layout.add_coin_mined_won)
                    .floatingTransition(new TranslateFloatingTransition(-TRANSLATE_ANIMATION_COIN_MINED_ANIMATION, ANIMATION_COIN_MINED_WON_DURATION))
                    .build();

            // Only first time enters here when we reach 200 meters
            if (coinMiningViewModel.getRangeMined() != MAXIMUM_RANGE_MINED_PER_DAY) {
                floatingAddCoinMinedWon.startFloating(floatingElementCoinMinedWon, numberUtils.rangeOrScopeToStringPlus(RANGE_MINED_NOW), FONT_NAME);
            } else {
                floatingAddCoinMinedWon.startFloating(floatingElementCoinMinedWon, numberUtils.rangeOrScopeToStringPlus(RANGE_MINED_NOW - coinMiningViewModel.getRangeNotMined()), FONT_NAME);
            }

            raddarRangeView.countAnimation(USER_RANGE, coinMiningViewModel.getNewRange(), 0);

            RANGE_MINED_NOW = coinMiningViewModel.getRangeNotMined();
            USER_RANGE = coinMiningViewModel.getNewRange();

            rangeMinedView.setText(numberUtils.rangeOrScopeToStringPlus(coinMiningViewModel.getRangeNotMined()));
            progressBarLevelView.setProgress(coinMiningViewModel.getNewPercentage());
            levelView.setText(String.valueOf(coinMiningViewModel.getLevel()));
        }
    }

    @Override
    public void showErrorLocalMinimumRangeMined() {
        initSnackbarError(R.string.error_local_minimum_range_mined);
    }

    @Override
    public void showMaximumRangeMinedPerDay() {
        initSnackbarLongError(R.string.error_local_maximum_range_mined_per_day);
    }

    @Override
    public void initializeCountDown(CoinMiningViewModel coinMiningViewModel) {
        userProfilePreferencesDataSource.setRangeMined(coinMiningViewModel.getRangeMined());

        rangeMinedTodayView.countAnimationRangeMined(RANGE_MINED_TOTAL, coinMiningViewModel.getRangeMined(), 0);

        countDownUtils.initializeCountDownCoinMining(textTimeView, ServerApiClient.SERVER_DATE, this);
    }

    @Override
    public void disableViews() {
        buttonExchangeView.setEnabled(false);
        buttonExchangeView.setClickable(false);
        areaMining.setEnabled(false);
        areaMining.setClickable(false);
    }

    @Override
    public void enableViews() {
        buttonExchangeView.setEnabled(true);
        buttonExchangeView.setClickable(true);
        areaMining.setEnabled(true);
        areaMining.setClickable(true);
    }

    @OnClick(R.id.exchange)
    public void onExchangeClicked() {
        presenter.coinMining(RANGE_MINED_NOW);
    }

    @Override
    public void onDestroy() {
        countDownUtils.cancelCountDown();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        userProfilePreferencesDataSource.setRangeMinedLocalAccumulated(RANGE_MINED_NOW);
        super.onPause();
        linearMiniLoadingView.setVisibility(View.GONE);
    }

    @OnClick(R.id.help)
    public void onHelpClicked() {
        DialogInfo.openInfo(getActivity(), getFragmentManager(), R.drawable.dialog_coin_mining,
                getString(R.string.coming_soon_coins_mined_title), getString(R.string.coming_soon_coins_mined_description), DialogInfo.HEIGHT_BIG_DIALOG);
    }

    @Override
    public void onFinish() {
        rangeMinedTodayView.countAnimationRangeMined(MAXIMUM_RANGE_MINED_PER_DAY, 0, 0);
        userProfilePreferencesDataSource.setRangeMined(0);
    }

    public void saveRangeMinedLocalAccumulated() {
        userProfilePreferencesDataSource.setRangeMinedLocalAccumulated(RANGE_MINED_NOW);
    }
}
