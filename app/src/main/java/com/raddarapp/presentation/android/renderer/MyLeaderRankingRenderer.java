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
import com.raddarapp.presentation.android.utils.UserUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.MyUsersRankingPresenterContract;
import com.raddarapp.presentation.general.viewmodel.MyLeaderRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class MyLeaderRankingRenderer extends BaseRendererWithIndex<MyUserRankingViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final MyUsersRankingPresenterContract presenter;
    private static final String SPACE = " ";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.leader_image) CircularImageView leaderImage;
    @BindView(R.id.leader_username) TextView leaderUsername;
    @BindView(R.id.leader_level) TextView leaderLevel;
    @BindView(R.id.position) TextView positionView;
    @BindView(R.id.leader_icon) ImageView leaderIcon;
    @BindView(R.id.total_scope) TextView totalScopeZone;
    @BindView(R.id.total_flags_zone) TextView totalFlagsZone;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;

    public MyLeaderRankingRenderer(MyUsersRankingPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        MyLeaderRankingViewModel myLeaderRankingViewModel = (MyLeaderRankingViewModel) getContent();
        // Here because we need the Context
        UserUtils userUtils = new UserUtils();
        int userRelationship = myLeaderRankingViewModel.getUserRelationship();

        initializeFonts();

        leaderUsername.setText(myLeaderRankingViewModel.getUsername());
        positionView.setText(String.valueOf(getPosition() + 1) + "º");
        leaderLevel.setText(myLeaderRankingViewModel.getLevel());
        totalScopeZone.setText(myLeaderRankingViewModel.getTotalScopeZone());
        totalFlagsZone.setText(myLeaderRankingViewModel.getTotalFootprintsZone());

        if (myLeaderRankingViewModel.getAudio() != null && !myLeaderRankingViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        switch (getPosition()) {
            case 0:
                leaderIcon.setImageResource(R.drawable.crown_silver);
                break;
            case 1:
                leaderIcon.setImageResource(R.drawable.crown_silver);
                break;
            case 2:
                leaderIcon.setImageResource(R.drawable.crown_bronze);
                break;
        }

        if (myLeaderRankingViewModel.getImage() != null && !myLeaderRankingViewModel.getImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + myLeaderRankingViewModel.getImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(leaderImage);
        } else {
            leaderImage.setImageResource(R.drawable.placeholder_profile);
        }

    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, leaderUsername, leaderLevel, positionView, totalScopeZone, totalFlagsZone);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_leader_ranking, parent, false);
    }

    @OnClick(R.id.leader_image)
    public void onLeaderUserImageClicked() {
        MyLeaderRankingViewModel myLeaderRankingViewModel = (MyLeaderRankingViewModel) getContent();
        presenter.onMyLeaderUserRankingClicked(myLeaderRankingViewModel, getPosition());
    }

    @OnClick(R.id.leader_username)
    public void onLeaderUserNameClicked() {
        MyLeaderRankingViewModel myLeaderRankingViewModel = (MyLeaderRankingViewModel) getContent();
        presenter.onMyLeaderUserRankingClicked(myLeaderRankingViewModel, getPosition());
    }

    @OnClick(R.id.ic_level)
    public void onLevelClicked() {
        MyLeaderRankingViewModel myLeaderRankingViewModel = (MyLeaderRankingViewModel) getContent();
        presenter.onMyLeaderUserRankingClicked(myLeaderRankingViewModel, getPosition());
    }

    @OnClick(R.id.linear_top)
    public void onLinearTopClicked() {
        MyLeaderRankingViewModel myLeaderRankingViewModel = (MyLeaderRankingViewModel) getContent();
        presenter.onMyLeaderUserRankingClicked(myLeaderRankingViewModel, getPosition());
    }
}
