package com.raddarapp.presentation.android.renderer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintStatus;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.android.view.StableAspectAnyUserFootprintRelativeLayout;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.contract.UserFootprintsPresenterContract;
import com.raddarapp.presentation.general.viewmodel.UserFootprintViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.BlurTransformation;

public class UserFootprintsRenderer extends BaseRendererWithIndex<UserFootprintViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final UserFootprintsPresenterContract presenter;
    private static final String SPACE = " ";
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_MEDIUM_SUFFIX = BuildConfig.SERVER_IMAGES_MEDIUM_SUFFIX;

    @BindView(R.id.comments) TextView comments;
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.card_root) StableAspectAnyUserFootprintRelativeLayout cardRoot;
    @BindView(R.id.timeago_distance) TextView timeAgoDistance;
    @BindView(R.id.scope) TextView scope;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.ic_emoji_category) EmojiTextView icEmojiCategory;
    @BindView(R.id.emoji_category) TextView emojiCategory;
    @BindView(R.id.total_stars) TextView totalStarsView;
    @BindView(R.id.total_stars_voted) TextView totalStarsVotedView;
    @BindView(R.id.total_stars_mini_decimals) TextView totalStarsMiniDecimalsView;
    @BindView(R.id.ic_stars) ImageView starsView;
    @BindView(R.id.dead) ImageView deadView;

    private Activity activity;
    private int myUserLevel;
    private boolean isDead = false;

    public UserFootprintsRenderer(Activity activity, UserFootprintsPresenterContract presenter, int myUserLevel) {
        this.activity = activity;
        this.presenter = presenter;
        this.myUserLevel = myUserLevel;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        UserFootprintViewModel userFootprintViewModel = (UserFootprintViewModel) getContent();
        // Here because we need the Context
        NumberUtils numberUtils = new NumberUtils();

        initializeFonts();

        if (userFootprintViewModel.getScope() >= 0) {
            scope.setTextColor(context.getResources().getColor(R.color.grey1));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            scope.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        title.setText(userFootprintViewModel.getTitle());
        scope.setText(numberUtils.rangeOrScopeToString(userFootprintViewModel.getScope()));

        comments.setText(numberUtils.numberToGroupedString(userFootprintViewModel.getComments()));

        new StarsUtils().calculateStars(starsView, totalStarsView, totalStarsVotedView, totalStarsMiniDecimalsView,
                userFootprintViewModel.getLikes(), userFootprintViewModel.getDislikes());

        isDead = userFootprintViewModel.getStatus() == FootprintStatus.DEAD.getValue();

        if (!isDead) {
            Picasso.with(context)
                    .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + userFootprintViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                    .placeholder(R.drawable.placeholder_card)
                    .fit()
                    .centerCrop()
                    .into(image);

            deadView.setVisibility(View.GONE);
        } else {
            Picasso.with(context)
                    .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + userFootprintViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                    .placeholder(R.drawable.placeholder_card)
                    .fit()
                    .transform(new BlurTransformation(context))
                    .centerCrop()
                    .into(image);

            deadView.setVisibility(View.VISIBLE);
        }

        EmojiUtils emojiUtils = new EmojiUtils();
        FootprintEmojiCategory emojiCategory = emojiUtils.getFootprintEmojiCategory(userFootprintViewModel.getCategory());
        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
        this.emojiCategory.setText(emojiCategory.getName());
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, comments, timeAgoDistance, totalStarsView,
                totalStarsVotedView, scope, title, emojiCategory, totalStarsMiniDecimalsView);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_user_footprint, parent, false);
    }

    @OnClick(R.id.card_root)
    public void onItemClicked() {
        if (!isDead) {
            UserFootprintViewModel userFootprintViewModel = (UserFootprintViewModel) getContent();
            presenter.onUserFootprintClicked(userFootprintViewModel, getPosition());    
        } else {
            DialogInfo.openInfo(activity, activity.getFragmentManager(), R.drawable.dialog_flags_dead,
                    activity.getString(R.string.footprint_dead_title), activity.getString(R.string.footprint_dead_description), DialogInfo.HEIGHT_NORMAL_DIALOG);
        }
    }
}
