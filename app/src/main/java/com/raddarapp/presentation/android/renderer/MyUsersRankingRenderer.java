package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.MyUsersRankingPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyUserRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class MyUsersRankingRenderer extends BaseRendererWithIndex<MyUserRankingViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final MyUsersRankingPresenterContract presenter;
    private static final String SPACE = " ";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.user_image) CircularImageView userImage;
    @BindView(R.id.username) TextView username;
    @BindView(R.id.user_level) TextView userLevel;
    @BindView(R.id.position) TextView positionView;
    @BindView(R.id.total_scope) TextView totalScope;
    @BindView(R.id.total_flags_zone) TextView totalFlagsZone;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;

    public MyUsersRankingRenderer(MyUsersRankingPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        MyUserRankingViewModel myUserRankingViewModel = (MyUserRankingViewModel) getContent();

        initializeFonts();

        username.setText(myUserRankingViewModel.getUsername());
        userLevel.setText(myUserRankingViewModel.getLevel());
        positionView.setText(String.valueOf(getPosition() + 1) + "º");
        totalScope.setText(myUserRankingViewModel.getTotalScopeZone());
        totalFlagsZone.setText(myUserRankingViewModel.getTotalFootprintsZone());

        if (myUserRankingViewModel.getAudio() != null && !myUserRankingViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        if (myUserRankingViewModel.getImage() != null && !myUserRankingViewModel.getImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + myUserRankingViewModel.getImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(userImage);
        } else {
            userImage.setImageResource(R.drawable.placeholder_profile);
        }

    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, username, userLevel, positionView, totalFlagsZone, totalScope);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_user_ranking, parent, false);
    }

    @OnClick(R.id.user_image)
    public void onUserImageClicked() {
        MyUserRankingViewModel myUserRankingViewModel = (MyUserRankingViewModel) getContent();
        presenter.onMyUserRankingClicked(myUserRankingViewModel, getPosition());
    }

    @OnClick(R.id.username)
    public void onUserNameClicked() {
        MyUserRankingViewModel myUserRankingViewModel = (MyUserRankingViewModel) getContent();
        presenter.onMyUserRankingClicked(myUserRankingViewModel, getPosition());
    }

    @OnClick(R.id.ic_level)
    public void onLevelClicked() {
        MyUserRankingViewModel myUserRankingViewModel = (MyUserRankingViewModel) getContent();
        presenter.onMyUserRankingClicked(myUserRankingViewModel, getPosition());
    }

    @OnClick(R.id.linear_top)
    public void onLinearTopClicked() {
        MyUserRankingViewModel myUserRankingViewModel = (MyUserRankingViewModel) getContent();
        presenter.onMyUserRankingClicked(myUserRankingViewModel, getPosition());
    }
}
