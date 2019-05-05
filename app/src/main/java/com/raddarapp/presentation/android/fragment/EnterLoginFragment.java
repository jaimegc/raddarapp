package com.raddarapp.presentation.android.fragment;


import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
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
import com.raddarapp.presentation.general.presenter.EnterLoginPresenter;
import com.raddarapp.presentation.general.viewmodel.MyUserProfileViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class EnterLoginFragment extends BaseNormalFragment implements EnterLoginPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;

    @BindView(R.id.login) FloatingActionButton loginView;
    @BindView(R.id.login_username) EditText editLoginUsernameView;
    @BindView(R.id.login_password) EditText editLoginPasswordView;
    @BindView(R.id.login_image_button) ImageView loginImageButtonView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.relative_input_data) RelativeLayout relativeInputData;
    @BindView(R.id.relative_login) RelativeLayout relativeLogin;

    @BindView(R.id.login_user_image) CircularImageView userImageView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.user_level) TextView userLevelView;
    @BindView(R.id.footprints) TextView myFootprintsView;
    @BindView(R.id.me_compliments) TextView meComplimentsView;
    @BindView(R.id.user_raddar_range) TextView userRaddarRangeView;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHeartsView;
    @BindView(R.id.login_go) Button loginGoView;
    @BindView(R.id.text_footprints) TextView textMyFootprintsView;
    @BindView(R.id.text_footprints_deaths) TextView textFootprintsDeathsView;
    @BindView(R.id.text_footprints_votes) TextView textFootprintsVotesView;
    @BindView(R.id.text_me_compliments) TextView textMeComplimentsView;
    @BindView(R.id.footprints_deaths) TextView footprintsDeathsView;
    @BindView(R.id.footprints_votes) TextView footprintsVotesView;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;
    @BindView(R.id.mock_text) TextView mockTextView;

    private AnimationUtils animationUtils = new AnimationUtils();
    private Animation animationLoginGoView;
    private Animation animationRelativeInputLoginDataView;

    @Inject @Presenter
    EnterLoginPresenter presenter;

    private MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    public static EnterLoginFragment newInstance() {
        EnterLoginFragment fragment = new EnterLoginFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_login;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        initializeAnimations();
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());

        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, loginGoView, editLoginUsernameView, editLoginPasswordView, mockTextView);
    }

    private void initializeAnimations() {
        animationLoginGoView = android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.translate_login_go_top);
        animationRelativeInputLoginDataView = android.view.animation.AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_login_input_data);
    }

    private void enableLoginColorView() {
        loginView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.marker_me)));
        loginImageButtonView.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.enabled_green)));
    }

    private void disableLoginColorView() {
        loginView.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled)));
        loginImageButtonView.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled_dark)));
    }

    private void enableLoginFocusableView() {
        editLoginUsernameView.setFocusable(true);
        editLoginPasswordView.setFocusable(true);
        editLoginUsernameView.setFocusableInTouchMode(true);
        editLoginPasswordView.setFocusableInTouchMode(true);
        loginView.setEnabled(true);
        loginView.setClickable(true);
    }

    private void disableLoginFocusableView() {
        editLoginUsernameView.setFocusable(false);
        editLoginPasswordView.setFocusable(false);
        editLoginUsernameView.setFocusableInTouchMode(false);
        editLoginPasswordView.setFocusableInTouchMode(false);
        loginView.setEnabled(false);
        loginView.setClickable(false);
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.login)
    public void onLoginClicked() {
        presenter.login(editLoginUsernameView.getText().toString(), editLoginPasswordView.getText().toString());
    }

    @OnClick(R.id.login_go)
    public void onLoginGoClicked() {
        SplashActivity.open(getActivity());
    }

    @Override
    public void loginSuccessful(MyUserProfileViewModel userProfileViewModel) {

        if (loginGoView.getVisibility() != View.VISIBLE) {
            NumberUtils numberUtils = new NumberUtils();

            relativeInputData.setVisibility(View.GONE);
            relativeLogin.setVisibility(View.GONE);
            relativeInputData.startAnimation(animationRelativeInputLoginDataView);
            relativeLogin.startAnimation(animationRelativeInputLoginDataView);
            loginGoView.setVisibility(View.VISIBLE);
            loginGoView.startAnimation(animationLoginGoView);
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
    public void disableLoginButton() {
        //disableLoginColorView();
        disableLoginFocusableView();
    }

    @Override
    public void enableLoginButton() {
        //enableLoginColorView();
        enableLoginFocusableView();
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        animationUtils.alphaAnimationWithInitial(linearLoadingView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void showErrorLocalUsernameEmpty() {
        initSnackbarError(R.string.error_local_empty_username);
    }

    @Override
    public void showErrorLocalPasswordEmpty() {
        initSnackbarError(R.string.error_local_empty_password);
    }
}
