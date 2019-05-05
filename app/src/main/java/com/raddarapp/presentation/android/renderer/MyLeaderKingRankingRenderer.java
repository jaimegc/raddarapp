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
import com.raddarapp.presentation.general.viewmodel.MyLeaderKingRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class MyLeaderKingRankingRenderer extends BaseRendererWithIndex<MyUserRankingViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final MyUsersRankingPresenterContract presenter;
    private static final String SPACE = " ";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.leader_image) CircularImageView leaderImager;
    @BindView(R.id.leader_username) TextView leaderUsername;
    @BindView(R.id.leader_level) TextView leaderLevel;
    @BindView(R.id.text_best_flags) TextView textBestFlags;
    @BindView(R.id.text_total_influence) TextView textTotalInfluence;
    @BindView(R.id.total_scope) TextView totalScopeZone;
    @BindView(R.id.text_flags_here) TextView textFlagsHere;
    @BindView(R.id.total_flags_zone) TextView totalFlagsZone;
    @BindView(R.id.actual_leader) TextView actualLeader;
    @BindView(R.id.position) TextView positionView;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;

    public MyLeaderKingRankingRenderer(MyUsersRankingPresenterContract presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        MyLeaderKingRankingViewModel myLeaderKingRankingViewModel = (MyLeaderKingRankingViewModel) getContent();
        // Here because we need the Context
        UserUtils userUtils = new UserUtils();
        int userRelationship = myLeaderKingRankingViewModel.getUserRelationship();

        initializeFonts();

        leaderUsername.setText(myLeaderKingRankingViewModel.getUsername());
        positionView.setText(String.valueOf(getPosition() + 1) + "º");
        leaderLevel.setText(myLeaderKingRankingViewModel.getLevel());
        totalScopeZone.setText(myLeaderKingRankingViewModel.getTotalScopeZone());
        totalFlagsZone.setText(myLeaderKingRankingViewModel.getTotalFootprintsZone());

        if (myLeaderKingRankingViewModel.getAudio() != null && !myLeaderKingRankingViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        if (myLeaderKingRankingViewModel.getImage() != null && !myLeaderKingRankingViewModel.getImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + myLeaderKingRankingViewModel.getImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(leaderImager);
        } else {
            leaderImager.setImageResource(R.drawable.placeholder_profile);
        }

    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, leaderUsername, leaderLevel, textBestFlags,
                textTotalInfluence, textFlagsHere, totalScopeZone, positionView, totalFlagsZone, actualLeader);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_leader_king_ranking, parent, false);
    }

    @OnClick(R.id.leader_image)
    public void onLeaderUserImageClicked() {
        MyLeaderKingRankingViewModel myLeaderKingRankingViewModel = (MyLeaderKingRankingViewModel) getContent();
        presenter.onMyLeaderKingUserRankingClicked(myLeaderKingRankingViewModel, getPosition());
    }

    @OnClick(R.id.leader_username)
    public void onLeaderUserNameClicked() {
        MyLeaderKingRankingViewModel myLeaderKingRankingViewModel = (MyLeaderKingRankingViewModel) getContent();
        presenter.onMyLeaderKingUserRankingClicked(myLeaderKingRankingViewModel, getPosition());
    }

    @OnClick(R.id.ic_level)
    public void onLevelClicked() {
        MyLeaderKingRankingViewModel myLeaderKingRankingViewModel = (MyLeaderKingRankingViewModel) getContent();
        presenter.onMyLeaderKingUserRankingClicked(myLeaderKingRankingViewModel, getPosition());
    }

    @OnClick(R.id.linear_top)
    public void onLinearTopClicked() {
        MyLeaderKingRankingViewModel myLeaderKingRankingViewModel = (MyLeaderKingRankingViewModel) getContent();
        presenter.onMyLeaderKingUserRankingClicked(myLeaderKingRankingViewModel, getPosition());
    }
}
