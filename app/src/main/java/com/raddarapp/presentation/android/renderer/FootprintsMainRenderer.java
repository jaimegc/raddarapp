package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.utils.StringUtils;
import com.raddarapp.presentation.android.utils.TimeAgoUtils;
import com.raddarapp.presentation.android.utils.UserUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsMainPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintMainViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class FootprintsMainRenderer extends BaseRendererWithIndex<FootprintMainViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final FootprintsMainPresenter presenter;
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;
    private static final String SERVER_IMAGES_MEDIUM_SUFFIX = BuildConfig.SERVER_IMAGES_MEDIUM_SUFFIX;

    @BindView(R.id.comments) TextView comments;
    @BindView(R.id.image) RoundedImageView image;
    @BindView(R.id.user_image) CircularImageView imageUser;
    @BindView(R.id.username) TextView username;
    @BindView(R.id.card_root) RelativeLayout cardRoot;
    @BindView(R.id.timeago) TextView timeAgoView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.zone) TextView zoneView;
    @BindView(R.id.text_save_collection) TextView textSaveCollection;
    @BindView(R.id.scope) TextView scope;
    @BindView(R.id.level) TextView level;
    @BindView(R.id.save_collection) RelativeLayout saveCollectionView;
    @BindView(R.id.image_save_collection) ImageView imageSaveCollection;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.flag_ball_background) View flagBallBackgroundView;
    @BindView(R.id.emoji_category) TextView emojiCategory;
    @BindView(R.id.ic_emoji_category) EmojiTextView icEmojiCategory;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.ic_stars) ImageView starsView;
    @BindView(R.id.ic_footprint_location) ImageView icFootprintLocationView;
    @BindView(R.id.relative_recent_diagonal) RelativeLayout relativeRecentDiagonal;
    @BindView(R.id.relative_footprint_warning) RelativeLayout relativeFootprintWarningView;
    @BindView(R.id.footprint_warning) TextView footprintWarningView;
    @BindView(R.id.recent) TextView recentView;

    public FootprintsMainRenderer(FootprintsMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        // Here because we need the Context
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        StringUtils stringUtils = new StringUtils();
        UserUtils userUtils = new UserUtils();
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();

        initializeFonts();

        username.setText(footprintMainViewModel.getUsername());

        scope.setText(numberUtils.rangeOrScopeToString(footprintMainViewModel.getScope()));
        level.setText(footprintMainViewModel.getUserLevel());

        if (footprintMainViewModel.getScope() >= 0) {
            scope.setTextColor(context.getResources().getColor(R.color.grey1));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            scope.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        title.setText(footprintMainViewModel.getTitle());

        comments.setText(numberUtils.numberToGroupedString(footprintMainViewModel.getComments()));

        new StarsUtils().calculateStars(starsView, footprintMainViewModel.getLikes(), footprintMainViewModel.getDislikes());

        if (footprintMainViewModel.getAudio() != null && !footprintMainViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        Picasso.with(context)
                .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + footprintMainViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                .placeholder(R.drawable.placeholder_card)
                .fit()
                .centerCrop()
                .into(image);

        if (footprintMainViewModel.getUserImage() != null && !footprintMainViewModel.getUserImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + footprintMainViewModel.getUserImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(imageUser);
        } else {
            imageUser.setImageResource(R.drawable.placeholder_profile);
        }

        if (!footprintMainViewModel.isPowerSelected()) {
            saveCollectionView.setVisibility(View.GONE);
        } else {
            saveCollectionView.setVisibility(View.VISIBLE);
        }

        if (footprintMainViewModel.isVisible()) {
            flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin);
            icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location)));
            icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location);
        } else {
            flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin_invisible);
            icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location_invisible)));
            icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location_invisible);
        }

        if (!dateUtils.isRecent(Long.parseLong(footprintMainViewModel.getCreationMoment()))) {
            relativeRecentDiagonal.setVisibility(View.GONE);

            if (footprintMainViewModel.getScope() == 0) {
                relativeFootprintWarningView.setVisibility(View.VISIBLE);
            } else {
                relativeFootprintWarningView.setVisibility(View.GONE);
            }
        } else {
            relativeRecentDiagonal.setVisibility(View.VISIBLE);
            relativeFootprintWarningView.setVisibility(View.GONE);
        }

        timeAgoView.setText(timeAgoUtils.timeAgo(Long.parseLong(footprintMainViewModel.getCreationMoment())) +
                " " + context.getString(R.string.zone_in));
        countryEmojiView.setText(footprintMainViewModel.getCountryEmoji());
        zoneView.setText(context.getString(R.string.zone_name, footprintMainViewModel.getZoneName()));

        EmojiUtils emojiUtils = new EmojiUtils();
        FootprintEmojiCategory emojiCategory = emojiUtils.getFootprintEmojiCategory(footprintMainViewModel.getCategory());

        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
        this.emojiCategory.setText(emojiCategory.getName());
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, username, comments, textSaveCollection, emojiCategory,
                timeAgoView, level, title, zoneView, footprintWarningView, recentView);

        fontUtils.applyFont(getContext(), FONT_NAME, scope);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_footprint_main, parent, false);
    }


    @OnClick(R.id.card_root)
    public void onItemClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onFootprintMainClicked(footprintMainViewModel, getPosition());
    }

    @OnClick(R.id.user_image)
    public void onUserImageClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onUserClicked(footprintMainViewModel, getPosition());
    }

    @OnClick(R.id.username)
    public void onUserNameClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onUserClicked(footprintMainViewModel, getPosition());
    }

    @OnClick(R.id.ic_level)
    public void onLevelClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();
        presenter.onUserClicked(footprintMainViewModel, getPosition());
    }

    @OnLongClick(R.id.card_root)
    public boolean onItemLongClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();

        if (footprintMainViewModel.isPowerSelected()) {
            saveCollectionView.setVisibility(View.GONE);
            cardRoot.setAnimation(null);
        } else {
            saveCollectionView.setVisibility(View.VISIBLE);
            Animation animationCardPower = AnimationUtils.loadAnimation(getContext(), R.anim.card_power);
            Animation animationCardPowerCount1 = AnimationUtils.loadAnimation(getContext(), R.anim.card_power_count1);
            Animation animationCardPowerCount2 = AnimationUtils.loadAnimation(getContext(), R.anim.card_power_count2);
            cardRoot.startAnimation(animationCardPower);
            textSaveCollection.startAnimation(animationCardPowerCount1);
            imageSaveCollection.startAnimation(animationCardPowerCount2);
        }

        footprintMainViewModel.updatePowerSelected(!footprintMainViewModel.isPowerSelected());

        return true;
    }

    @OnClick(R.id.flag_ball)
    public void onFlagBallClicked() {
        FootprintMainViewModel footprintMainViewModel = (FootprintMainViewModel) getContent();

        if (footprintMainViewModel.isVisible()) {
            presenter.onClickedFootprintAnimationSelected(footprintMainViewModel.getKey());
        }
    }
}
