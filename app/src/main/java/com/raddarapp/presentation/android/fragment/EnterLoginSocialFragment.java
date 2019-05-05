package com.raddarapp.presentation.android.fragment;


import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.presentation.android.activity.SplashActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.general.presenter.EnterLoginSocialTokenPresenter;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class EnterLoginSocialFragment extends BaseNormalFragment implements EnterLoginSocialTokenPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String INDEX_LOGIN_KEY_EXTRA = "EnterLoginSocialToken.IndexLoginKeyExtra";
    private static final String TOKEN_LOGIN_KEY_EXTRA = "EnterLoginSocialToken.IndexTokenKeyExtra";
    private static final int INDEX_FACEBOOK_LOGIN = 0;
    private static final int INDEX_GOOGLE_LOGIN = 1;
    private int indexLogin;
    private String token;

    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;

    @BindView(R.id.login_user_image) CircularImageView userImageView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.user_level) TextView userLevelView;
    @BindView(R.id.footprints) TextView myFootprintsView;
    @BindView(R.id.user_raddar_range) TextView userRaddarRangeView;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHeartsView;
    @BindView(R.id.text_congratulations_top) TextView congratulationsTopView;
    @BindView(R.id.text_congratulations_bottom) TextView congratulationsBottomView;
    @BindView(R.id.login_go) Button loginGoView;
    @BindView(R.id.me_compliments) TextView meComplimentsView;
    @BindView(R.id.relative_login_complete) RelativeLayout relativeLoginCompleteView;
    @BindView(R.id.relative_login_incomplete) RelativeLayout relativeLoginIncompleteView;
    @BindView(R.id.text_footprints) TextView textMyFootprintsView;
    @BindView(R.id.text_footprints_deaths) TextView textFootprintsDeathsView;
    @BindView(R.id.text_footprints_votes) TextView textFootprintsVotesView;
    @BindView(R.id.text_me_compliments) TextView textMeComplimentsView;
    @BindView(R.id.footprints_deaths) TextView footprintsDeathsView;
    @BindView(R.id.footprints_votes) TextView footprintsVotesView;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;

    private AnimationUtils animationUtils = new AnimationUtils();
    private Animation animationLoginGoAlphaStartView;
    private Animation animationLoginGoAlphaEndView;

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    private boolean isRetry = true;

    @Inject @Presenter
    EnterLoginSocialTokenPresenter presenter;

    public static EnterLoginSocialFragment newInstance(Integer loginIndex, String token) {
        EnterLoginSocialFragment fragment = new EnterLoginSocialFragment();

        Bundle args = new Bundle();

        args.putInt(INDEX_LOGIN_KEY_EXTRA, loginIndex);
        args.putString(TOKEN_LOGIN_KEY_EXTRA, token);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_login_social;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        indexLogin = getArguments().getInt(INDEX_LOGIN_KEY_EXTRA);
        token = getArguments().getString(TOKEN_LOGIN_KEY_EXTRA);

        initializeAnimations();
        presenter.setLoginToken(token);
        presenter.setIndexLogin(indexLogin);
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());

        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, loginGoView);
    }

    private void initializeAnimations() {
        animationLoginGoAlphaStartView = android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_login_retry_start);
        animationLoginGoAlphaEndView = android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_login_retry_end);
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.login_go)
    public void onLoginGoClicked() {

        if (relativeLoginIncompleteView.getVisibility() == View.GONE) {
            if (!isRetry) {
                SplashActivity.open(getActivity());
            } else {

                if (indexLogin == INDEX_FACEBOOK_LOGIN) {
                    presenter.loginFacebookRetry();
                } else if (indexLogin == INDEX_GOOGLE_LOGIN) {
                    presenter.loginGoogleRetry();
                }

                loginGoView.setClickable(false);
                loginGoView.setVisibility(View.GONE);
                loginGoView.startAnimation(animationLoginGoAlphaEndView);
            }
        } else {
            SplashActivity.open(getActivity());
        }
    }

    @Override
    public void loginSuccessful(MyUserProfileViewModel userProfileViewModel, boolean isComplete) {
        loginGoView.setText(R.string.login_continue);
        loginGoView.setBackgroundResource(R.drawable.background_button_purple);
        NumberUtils numberUtils = new NumberUtils();

        if (isComplete) {
            relativeLoginIncompleteView.setVisibility(View.GONE);
        } else {
            congratulationsTopView.setText(getString(R.string.enter_login_congratulations_top, userProfileViewModel.getName()));

            congratulationsBottomView.setText(getString(R.string.enter_login_congratulations_bottom));

            // Only for hdpi screens
            if (getActivity() != null && getActivity().getResources().getDisplayMetrics().densityDpi == DisplayMetrics.DENSITY_HIGH) {
                congratulationsBottomView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textsize_small1));
            }

            relativeLoginIncompleteView.setVisibility(View.VISIBLE);
        }

        if (loginGoView.getVisibility() != View.VISIBLE) {
            loginGoView.setClickable(true);
            isRetry = false;
            loginGoView.setVisibility(View.VISIBLE);
            loginGoView.startAnimation(animationLoginGoAlphaStartView);
            usernameView.setText(userProfileViewModel.getUsername());
            myFootprintsView.setText(userProfileViewModel.getTotalFootprints());
            userLevelView.setText(userProfileViewModel.getLevel());
            userRaddarRangeView.setText(numberUtils.rangeOrScopeToString(userProfileViewModel.getRange()));
            meComplimentsView.setText(numberUtils.numberToGroupedString(userProfileViewModel.getUserCompliments()));
            userProfilePreferencesDataSource.setRefreshTokenUpdated(System.currentTimeMillis());
            footprintsDeathsView.setText(userProfileViewModel.getTotalFootprintsDead());
            footprintsVotesView.setText(numberUtils.numberToGroupedString(
                    userProfileViewModel.getTotalLikes() + userProfileViewModel.getTotalDislikes()));

            new StarsUtils().calculateStars(totalStarsView, totalStarsMiniDecimalsView,
                    userProfileViewModel.getTotalLikes(), userProfileViewModel.getTotalDislikes());

            if (userProfileViewModel.getRange() >= 0) {
                userRaddarRangeView.setTextColor(getActivity().getResources().getColor(R.color.grey3));
                icVoteHeartsView.setImageResource(R.drawable.ic_vote_hearts_like);
            } else {
                userRaddarRangeView.setTextColor(getActivity().getResources().getColor(R.color.red));
                icVoteHeartsView.setImageResource(R.drawable.ic_vote_hearts_dislike);
            }

            new FileUtils(getActivity()).loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, userImageView, false);
        }
    }

    @Override
    public void showLoginError() {
        isRetry = true;
        loginGoView.setClickable(true);
        loginGoView.setBackgroundResource(R.drawable.background_button_login_retry);
        loginGoView.setText(R.string.login_retry);
        loginGoView.setVisibility(View.VISIBLE);
        loginGoView.startAnimation(animationLoginGoAlphaStartView);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(linearLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
    }
}
