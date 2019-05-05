package com.raddarapp.presentation.android.renderer;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.utils.StringUtils;
import com.raddarapp.presentation.android.utils.TimeAgoUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsRankingPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintRankingViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class FootprintsRankingRenderer extends BaseRendererWithIndex<FootprintRankingViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final FootprintsRankingPresenter presenter;
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_MEDIUM_SUFFIX = BuildConfig.SERVER_IMAGES_MEDIUM_SUFFIX;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.comments) TextView comments;
    @BindView(R.id.image) ImageView image;
    @BindView(R.id.timeago) TextView timeAgoView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.zone) TextView zoneView;
    @BindView(R.id.scope) TextView scope;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.ic_emoji_category) EmojiTextView icEmojiCategory;
    @BindView(R.id.emoji_category) TextView emojiCategory;
    @BindView(R.id.ic_stars) ImageView starsView;
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.user_image) CircularImageView userImageView;
    @BindView(R.id.level) TextView levelView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.position) TextView positionView;
    @BindView(R.id.flag_ball_background) View flagBallBackgroundView;
    @BindView(R.id.ic_footprint_location) ImageView icFootprintLocationView;
    @BindView(R.id.top_position) TextView topPositionView;
    @BindView(R.id.relative_top_diagonal) RelativeLayout relativeTopDiagonal;
    @BindView(R.id.relative_root) RelativeLayout relativeRoot;

    private String footprintCollectionKey;
    private Activity activity;
    private int footprintPosition;
    private int height;

    public FootprintsRankingRenderer(Activity activity, FootprintsRankingPresenter presenter, int height) {
        this.activity = activity;
        this.presenter = presenter;
        this.height = height;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        FootprintRankingViewModel footprintRankingViewModel = (FootprintRankingViewModel) getContent();
        NumberUtils numberUtils = new NumberUtils();
        TimeAgoUtils timeAgoUtils = new TimeAgoUtils(context);
        StringUtils stringUtils = new StringUtils();

        initializeFonts();

        relativeRoot.getLayoutParams().height = height;

        footprintPosition = getPosition();
        footprintCollectionKey = footprintRankingViewModel.getKey();
        usernameView.setText(footprintRankingViewModel.getUsername());
        levelView.setText(footprintRankingViewModel.getUserLevel());
        title.setText(footprintRankingViewModel.getTitle());
        scope.setText(numberUtils.rangeOrScopeToString(footprintRankingViewModel.getScope()));
        comments.setText(numberUtils.numberToGroupedString(footprintRankingViewModel.getComments()));
        positionView.setText(String.valueOf(footprintPosition + 1) + "º");

        if (footprintPosition < 3) {

            switch (footprintPosition) {
                case 0:
                    topPositionView.setBackgroundResource(R.color.ranking_leader_gold);
                    break;
                case 1:
                    topPositionView.setBackgroundResource(R.color.ranking_leader_silver);
                    break;
                case 2:
                    topPositionView.setBackgroundResource(R.color.ranking_leader_bronze);
                    break;
            }

            relativeTopDiagonal.setVisibility(View.VISIBLE);
            positionView.setVisibility(View.GONE);
            topPositionView.setText(context.getString(R.string.footprints_ranking_diagonal_top) + " " + (footprintPosition + 1));
        } else {
            relativeTopDiagonal.setVisibility(View.GONE);
            positionView.setVisibility(View.VISIBLE);
        }

        new StarsUtils().calculateStars(starsView, footprintRankingViewModel.getLikes(), footprintRankingViewModel.getDislikes());

        if (footprintRankingViewModel.getAudio() != null && !footprintRankingViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        Picasso.with(context)
                .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + footprintRankingViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                .placeholder(R.drawable.placeholder_card)
                .fit()
                .centerCrop()
                .into(image);

        if (footprintRankingViewModel.getUserImage() != null && !footprintRankingViewModel.getUserImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + footprintRankingViewModel.getUserImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(userImageView);
        } else {
            userImageView.setImageResource(R.drawable.placeholder_profile);
        }

        if (footprintRankingViewModel.isVisible()) {
            flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin);
            icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location)));
            icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location);
        } else {
            flagBallBackgroundView.setBackgroundResource(R.drawable.circle_coin_invisible);
            icFootprintLocationView.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.footprint_location_invisible)));
            icFootprintLocationView.setImageResource(R.drawable.ic_footprint_location_invisible);
        }

        timeAgoView.setText(timeAgoUtils.timeAgo(Long.parseLong(footprintRankingViewModel.getCreationMoment())) +
                " " + context.getString(R.string.zone_in));
        countryEmojiView.setText(footprintRankingViewModel.getCountryEmoji());
        zoneView.setText(context.getString(R.string.zone_name, footprintRankingViewModel.getZoneName()));

        EmojiUtils emojiUtils = new EmojiUtils();
        FootprintEmojiCategory emojiCategory = emojiUtils.getFootprintEmojiCategory(footprintRankingViewModel.getCategory());
        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
        this.emojiCategory.setText(emojiCategory.getName());
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, comments, scope, title, emojiCategory);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_footprint_ranking, parent, false);
    }

    @OnClick(R.id.user_image)
    public void onUserImageClicked() {
        FootprintRankingViewModel footprintRankingViewModel = (FootprintRankingViewModel) getContent();
        presenter.onUserClicked(footprintRankingViewModel, getPosition());
    }

    @OnClick(R.id.username)
    public void onUserNameClicked() {
        FootprintRankingViewModel footprintRankingViewModel = (FootprintRankingViewModel) getContent();
        presenter.onUserClicked(footprintRankingViewModel, getPosition());
    }

    @OnClick(R.id.ic_level)
    public void onLevelClicked() {
        FootprintRankingViewModel footprintRankingViewModel = (FootprintRankingViewModel) getContent();
        presenter.onUserClicked(footprintRankingViewModel, getPosition());
    }

    @OnClick(R.id.card_root)
    public void onItemClicked() {
        FootprintRankingViewModel footprintRankingViewModel = (FootprintRankingViewModel) getContent();
        presenter.onFootprintRankingClicked(footprintRankingViewModel, getPosition());
    }
}
