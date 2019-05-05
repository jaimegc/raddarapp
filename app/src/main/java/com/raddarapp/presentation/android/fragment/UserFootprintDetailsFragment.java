package com.raddarapp.presentation.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.presentation.android.activity.CommentsActivity;
import com.raddarapp.presentation.android.activity.ImageDetailsActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.utils.StringUtils;
import com.raddarapp.presentation.android.utils.TimeAgoUtils;
import com.raddarapp.presentation.android.utils.ViewUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.general.presenter.CommentsFootprintDetailsPresenter;
import com.raddarapp.presentation.general.presenter.CreateCommentPresenter;
import com.raddarapp.presentation.general.presenter.UserFootprintDetailsPresenter;
import com.raddarapp.presentation.general.presenter.UserToolbarPresenter;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.CreateCommentViewModel;
import com.raddarapp.presentation.general.viewmodel.UserFootprintDetailsViewModel;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFootprintDetailsFragment extends BaseNormalFragment implements UserFootprintDetailsPresenter.View,
        CreateCommentPresenter.View, UserToolbarPresenter.View, CommentsFootprintDetailsPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";
    private static final String FOOTPRINT_COMMENTS_EXTRA = "FootprintDetails.FootprintComments";
    protected static final String COMMENTS_EXTRA = "FootprintDetails.NewCommentsExtra";
    protected static final String POSITION_EXTRA = "FootprintDetails.PositionExtra";
    private static final int REQUEST_COMMENTS = 200;
    private static final String SPACE = " ";
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.footprint_image) ImageView footprintImageView;
    @BindView(R.id.user_image) CircularImageView userImageView;
    @BindView(R.id.user_image_me) CircularImageView userImageMeView;
    @BindView(R.id.comment_user_image1) CircularImageView commentUserImage1View;
    @BindView(R.id.comment_user_image2) CircularImageView commentUserImage2View;
    @BindView(R.id.comment_user_image3) CircularImageView commentUserImage3View;
    @BindView(R.id.description) TextView descriptionView;
    @BindView(R.id.level) TextView levelView;
    @BindView(R.id.raddar_range_user) TextView raddarRangeUser;
    @BindView(R.id.title) TextView titleView;
    @BindView(R.id.timeago) TextView timeAgoView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.zone) TextView zoneView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.scope) TextView scopeView;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.ic_vote_hearts_user) ImageView icVoteHeartsUser;
    @BindView(R.id.comment_user_level1) TextView commentUserLevel1View;
    @BindView(R.id.comment_user_level2) TextView commentUserLevel2View;
    @BindView(R.id.comment_user_level3) TextView commentUserLevel3View;
    @BindView(R.id.comment_username1) TextView commentUsername1View;
    @BindView(R.id.comment_username2) TextView commentUsername2View;
    @BindView(R.id.comment_username3) TextView commentUsername3View;
    @BindView(R.id.comment_timeago1) TextView commentTimeAgo1View;
    @BindView(R.id.comment_timeago2) TextView commentTimeAgo2View;
    @BindView(R.id.comment_timeago3) TextView commentTimeAgo3View;
    @BindView(R.id.comments) TextView commentsView;
    @BindView(R.id.comment1) TextView comment1View;
    @BindView(R.id.comment2) TextView comment2View;
    @BindView(R.id.comment3) TextView comment3View;
    @BindView(R.id.comments_all) TextView commentsAllView;
    @BindView(R.id.loading) AVLoadingIndicatorView progressBarView;
    @BindView(R.id.relative_comment1) RelativeLayout relativeComment1View;
    @BindView(R.id.relative_comment2) RelativeLayout relativeComment2View;
    @BindView(R.id.relative_comment3) RelativeLayout relativeComment3View;
    @BindView(R.id.edit_create_comment) EditText editCreateCommentView;
    @BindView(R.id.loading_create_comment) ProgressBar loadingCreateComment;
    @BindView(R.id.create_comment) ImageView createCommentView;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_voted) TextView totalStarsVotedView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;
    @BindView(R.id.ic_stars) ImageView starsView;
    @BindView(R.id.ic_emoji_category) EmojiTextView icEmojiCategory;

    @Inject @Presenter
    UserFootprintDetailsPresenter userFootprintDetailsPresenter;
    @Inject @Presenter
    CommentsFootprintDetailsPresenter commentsDetailsPresenter;
    @Inject @Presenter
    CreateCommentPresenter createCommentPresenter;
    @Inject @Presenter
    UserToolbarPresenter userToolbarPresenter;

    private ViewUtils viewUtils = new ViewUtils();
    private NumberUtils numberUtils = new NumberUtils();
    private PreferencesUtils preferencesUtils;
    private String footprintKey;
    private long totalComments;
    private int indexScreen;
    private String urlFootprintImage;

    private String userKeyComment1;
    private String userKeyComment2;
    private String userKeyComment3;

    public static UserFootprintDetailsFragment newInstance(String footprintKey, int indexScreen) {
        UserFootprintDetailsFragment fragment = new UserFootprintDetailsFragment();
        Bundle args = new Bundle();

        args.putString(FOOTPRINT_KEY_EXTRA, footprintKey);
        args.putInt(INDEX_SCREEN_EXTRA, indexScreen);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_footprint_details;
    }

    @Override
    protected void onPreparePresenter() {
        super.onPreparePresenter();
        footprintKey = getArguments().getString(FOOTPRINT_KEY_EXTRA);
        totalComments = getArguments().getLong(FOOTPRINT_COMMENTS_EXTRA);
        indexScreen = getArguments().getInt(INDEX_SCREEN_EXTRA);

        userFootprintDetailsPresenter.setUserFootprintKey(footprintKey);
        commentsDetailsPresenter.setFootprintKey(footprintKey);
        commentsDetailsPresenter.setTotalComments(totalComments);
        createCommentPresenter.setCommentsPresenter(commentsDetailsPresenter);
        userToolbarPresenter.setIndexScreen(indexScreen);

        preferencesUtils = new PreferencesUtils(getActivity());
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        createCommentView.setEnabled(false);

        editCreateCommentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text.length() != 0) {
                    createCommentView.setEnabled(true);
                    createCommentView.setImageResource(R.drawable.ic_create_comment_enable);
                } else {
                    createCommentView.setEnabled(false);
                    createCommentView.setImageResource(R.drawable.ic_create_comment_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCreateCommentView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onCreateCommentClicked();
                return true;
            }
            return false;
        });

        editCreateCommentView.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editCreateCommentView.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        editCreateCommentView.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editCreateCommentView.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        new FileUtils(getActivity()).loadMyUserProfileImageCache(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE, userImageMeView, true);

        initializeFonts();
    }

    private void initializeFonts() {
        new FontUtils().applyFont(getActivity(), FONT_NAME, editCreateCommentView);
    }

    @Override
    public void showUserFootprintDetails(UserFootprintDetailsViewModel userFootprintDetailsViewModel) {
        DimenUtils dimenUtils = new DimenUtils();
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        StringUtils stringUtils = new StringUtils();

        if (userFootprintDetailsViewModel.getDescription() != null && !userFootprintDetailsViewModel.getDescription().isEmpty()) {
            descriptionView.setText(userFootprintDetailsViewModel.getDescription());
        } else {
            descriptionView.setVisibility(View.GONE);
        }

        titleView.setText(userFootprintDetailsViewModel.getTitle());
        scopeView.setText(numberUtils.rangeOrScopeToString(userFootprintDetailsViewModel.getScope()));

        if (userFootprintDetailsViewModel.getScope() >= 0) {
            scopeView.setTextColor(context.getResources().getColor(R.color.grey1));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            scopeView.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        new StarsUtils().calculateStars(starsView, totalStarsView, totalStarsVotedView, totalStarsMiniDecimalsView,
                userFootprintDetailsViewModel.getLikes(), userFootprintDetailsViewModel.getDislikes());

        timeAgoView.setText(timeAgoUtils.timeAgo(Long.parseLong(userFootprintDetailsViewModel.getCreationMoment())));
        countryEmojiView.setText(userFootprintDetailsViewModel.getCountryEmoji());

        if (userFootprintDetailsViewModel.getParentZoneName() != null) {
            zoneView.setText(context.getString(R.string.zone_parent_name,
                    userFootprintDetailsViewModel.getZoneName(), userFootprintDetailsViewModel.getParentZoneName()));
        } else {
            zoneView.setText(context.getString(R.string.zone_name, userFootprintDetailsViewModel.getZoneName()));
        }

        if (userFootprintDetailsViewModel.getComments() != 1) {
            commentsView.setText(numberUtils.numberToGroupedString(userFootprintDetailsViewModel.getComments()) + SPACE + context.getString(R.string.comments));
        } else {
            commentsView.setText(numberUtils.numberToGroupedString(userFootprintDetailsViewModel.getComments()) + SPACE + context.getString(R.string.comment));
        }

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) footprintImageView.getLayoutParams();
        params.height = dimenUtils.calculateHeightPxByAspectRatio(getActivity(), userFootprintDetailsViewModel.getAspectRatio());
        footprintImageView.setImageResource(R.drawable.placeholder_card);

        Picasso.with(getActivity())
                .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + userFootprintDetailsViewModel.getMedia())
                //.placeholder(R.drawable.placeholder_card)
                .fit()
                .centerCrop()
                .into(footprintImageView);

        urlFootprintImage = SERVER_IMAGES_API_BASE_URL_COMPLETE + userFootprintDetailsViewModel.getMedia();

        EmojiUtils emojiUtils = new EmojiUtils();
        FootprintEmojiCategory emojiCategory = emojiUtils.getFootprintEmojiCategory(userFootprintDetailsViewModel.getCategory());
        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
    }

    @Override
    public void showEmptyComments() {
        // No implemented
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.comments_all)
    public void onCommentsAllClicked() {
        CommentsActivity.openFromUserFootprintDetails(getActivity(), footprintKey);
    }

    @OnClick(R.id.create_comment)
    public void onCreateCommentClicked() {

        if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
            if (createCommentPresenter.isValidComment(editCreateCommentView.getText().toString())) {
                userFootprintDetailsPresenter.updateComments();

                /*if (totalComments + totalCommentsCreated == 0) {
                    scroll.post(() -> scroll.fullScroll(ScrollView.FOCUS_DOWN));
                }*/

                totalComments++;
                createCommentPresenter.onCreateCommentClicked(footprintKey, editCreateCommentView.getText().toString(), true);
                editCreateCommentView.setText(null);
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editCreateCommentView.getWindowToken(), 0);
            }
        } else {
            initSnackbarError(R.string.error_local_create_comment_admin);
        }
    }

    @OnClick(R.id.footprint_image)
    public void onFootprintImageClicked() {
        ImageDetailsActivity.open(getActivity(), urlFootprintImage, ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), footprintImageView, getString(R.string.ts_image_details)));
    }

    @Override
    public void showOneComment(CommentViewModel comment) {
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        int userRelationship = comment.getUserRelationship();
        userKeyComment1 = comment.getKey();

        commentsView.setText("1" + SPACE + context.getString(R.string.comment));

        comment1View.setText(comment.getBody());
        commentUserLevel1View.setText(comment.getUserLevel());
        commentUsername1View.setText(comment.getUsername());
        commentTimeAgo1View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comment.getCreationMoment())).toLowerCase()));

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comment.getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage1View);

        viewUtils.visibilityVisible(relativeComment1View);
    }

    @Override
    public void showTwoComments(List<CommentViewModel> comments) {
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        userKeyComment1 = comments.get(0).getKey();
        userKeyComment2 = comments.get(1).getKey();

        commentsView.setText("2" + SPACE + context.getString(R.string.comments));

        comment1View.setText(comments.get(0).getBody());
        comment2View.setText(comments.get(1).getBody());
        commentUserLevel1View.setText(comments.get(0).getUserLevel());
        commentUserLevel2View.setText(comments.get(1).getUserLevel());
        commentUsername1View.setText(comments.get(0).getUsername());
        commentUsername2View.setText(comments.get(1).getUsername());
        commentTimeAgo1View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comments.get(0).getCreationMoment())).toLowerCase()));
        commentTimeAgo2View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comments.get(1).getCreationMoment())).toLowerCase()));

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comments.get(0).getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage1View);

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comments.get(1).getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage2View);

        viewUtils.visibilityVisible(relativeComment1View, relativeComment2View);
    }

    @Override
    public void showThreeComments(List<CommentViewModel> comments, boolean moreThanThree) {
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        userKeyComment1 = comments.get(0).getKey();
        userKeyComment2 = comments.get(1).getKey();
        userKeyComment3 = comments.get(2).getKey();

        comment1View.setText(comments.get(0).getBody());
        comment2View.setText(comments.get(1).getBody());
        comment3View.setText(comments.get(2).getBody());
        commentUserLevel1View.setText(comments.get(0).getUserLevel());
        commentUserLevel2View.setText(comments.get(1).getUserLevel());
        commentUserLevel3View.setText(comments.get(2).getUserLevel());
        commentUsername1View.setText(comments.get(0).getUsername());
        commentUsername2View.setText(comments.get(1).getUsername());
        commentUsername3View.setText(comments.get(2).getUsername());
        commentTimeAgo1View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comments.get(0).getCreationMoment())).toLowerCase()));
        commentTimeAgo2View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comments.get(1).getCreationMoment())).toLowerCase()));
        commentTimeAgo3View.setText(String.format(
                timeAgoUtils.timeAgo(Long.parseLong(comments.get(2).getCreationMoment())).toLowerCase()));

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comments.get(0).getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage1View);

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comments.get(1).getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage2View);

        Picasso.with(getActivity())
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + comments.get(2).getPhoto() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(commentUserImage3View);

        if (moreThanThree) {
            viewUtils.visibilityVisible(relativeComment1View, relativeComment2View, relativeComment3View, commentsAllView);
            commentsView.setText(numberUtils.numberToGroupedString(totalComments) + SPACE + context.getString(R.string.comments));
        } else {
            viewUtils.visibilityVisible(relativeComment1View, relativeComment2View, relativeComment3View);
            commentsView.setText("3" + SPACE + context.getString(R.string.comments));
        }
    }

    @Override
    public void updateComments(long totalNewComments) {
        totalComments = totalNewComments;
        userFootprintDetailsPresenter.updateComments(totalNewComments);
        commentsDetailsPresenter.setTotalComments(totalNewComments);
    }

    @Override
    public void hideLoading() {
        progressBarView.setVisibility(View.GONE);
        editCreateCommentView.setEnabled(true);
    }

    @Override
    public void showLoading() {
        progressBarView.setVisibility(View.VISIBLE);
        editCreateCommentView.setEnabled(false);

        viewUtils.visibilityGone(commentsAllView, relativeComment1View, relativeComment2View, relativeComment3View);
    }

    public long getTotalComments() {
        return totalComments;
    }

    public void deleteCache() {
        commentsDetailsPresenter.deleteCache();
    }

    @Override
    public void showUserProfile(UserProfileViewModel userProfileDetailsViewModel) {
        NumberUtils numberUtils = new NumberUtils();

        usernameView.setText(userProfileDetailsViewModel.getUsername());
        levelView.setText(userProfileDetailsViewModel.getLevel());
        raddarRangeUser.setText(numberUtils.rangeOrScopeToString(userProfileDetailsViewModel.getRange()));

        if (userProfileDetailsViewModel.getRange() >= 0) {
            raddarRangeUser.setTextColor(context.getResources().getColor(R.color.grey3));
            icVoteHeartsUser.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            raddarRangeUser.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHeartsUser.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        if ((userProfileDetailsViewModel.getAudio() != null && !userProfileDetailsViewModel.getAudio().isEmpty())) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        Picasso.with(context)
                .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + userProfileDetailsViewModel.getImage() + SERVER_IMAGES_SMALL_SUFFIX)
                .placeholder(R.drawable.placeholder_profile)
                .fit()
                .centerCrop()
                .into(userImageView);
    }

    @Override
    public void showCreatedComment(CreateCommentViewModel createCommentViewModel) {
        // No implemented. In the future, update comment id
    }

    @Override
    public void showCreateCommentError() {

    }

    @Override
    public void showCreateCommentLoading() {
        loadingCreateComment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCreateCommentLoading() {
        loadingCreateComment.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == REQUEST_COMMENTS) {
            long totalNewComments = data.getLongExtra(COMMENTS_EXTRA, 0);
            updateComments(totalNewComments);
            editCreateCommentView.setText(null);
        }
    }

    @Override
    public void showErrorLocalCommentEmpty() {
        initSnackbarError(R.string.error_local_empty_comment);
    }

    @Override
    public void onPause() {
        super.onPause();
        loadingCreateComment.setVisibility(View.GONE);
    }
}
