package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.renderer.base.BaseRenderer;
import com.raddarapp.presentation.android.utils.TimeAgoUtils;
import com.raddarapp.presentation.general.presenter.CommentsPresenter;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class CommentsRenderer extends BaseRenderer<CommentViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final CommentsPresenter presenter;
    private static final String SPACE = " ";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.comment_user_level) TextView commentUserLevelView;
    @BindView(R.id.comment_username) TextView commentUsernameView;
    @BindView(R.id.comment_timeago) TextView commentTimeAgoView;
    @BindView(R.id.comment) TextView commentView;
    @BindView(R.id.comment_user_image) CircularImageView commentUserImageView;

    public CommentsRenderer(CommentsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        CommentViewModel commentViewModel = (CommentViewModel) getContent();
        // Here because we need the Context
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        int userRelationship = commentViewModel.getUserRelationship();

        commentView.setText(commentViewModel.getBody());
        commentUserLevelView.setText(commentViewModel.getUserLevel());
        commentUsernameView.setText(commentViewModel.getUsername());
        commentTimeAgoView.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(commentViewModel.getCreationMoment())).toLowerCase()));

        if (commentViewModel.getPhoto() != null && !commentViewModel.getPhoto().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + commentViewModel.getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(commentUserImageView);
        } else {
            commentUserImageView.setImageResource(R.drawable.placeholder_profile);
        }
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_comment, parent, false);
    }

}
