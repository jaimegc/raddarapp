package com.raddarapp.presentation.android.renderer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.FootprintStatus;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.dialog.DialogMyFootprintCollection;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.utils.StarsUtils;
import com.raddarapp.presentation.android.view.FootprintEmojiCategory;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.MyFootprintsCollectionPresenter;
import com.raddarapp.presentation.general.viewmodel.MyFootprintCollectionViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;
import com.squareup.picasso.Picasso;
import com.vanniktech.emoji.EmojiTextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import jp.wasabeef.picasso.transformations.BlurTransformation;

public class MyFootprintsCollectionRenderer extends BaseRendererWithIndex<MyFootprintCollectionViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final MyFootprintsCollectionPresenter presenter;
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_MEDIUM_SUFFIX = BuildConfig.SERVER_IMAGES_MEDIUM_SUFFIX;
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.comments) TextView comments;
    @BindView(R.id.image) ImageView image;
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
    @BindView(R.id.play_audio_profile) ImageView playAudioProfileView;
    @BindView(R.id.user_image) CircularImageView userImageView;
    @BindView(R.id.level) TextView levelView;
    @BindView(R.id.username) TextView usernameView;
    @BindView(R.id.dead) ImageView deadView;

    private String footprintCollectionKey;
    private Activity activity;
    private boolean isDead = false;

    public MyFootprintsCollectionRenderer(Activity activity, MyFootprintsCollectionPresenter presenter) {
        this.activity = activity;
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        MyFootprintCollectionViewModel myFootprintCollectionViewModel = (MyFootprintCollectionViewModel) getContent();
        NumberUtils numberUtils = new NumberUtils();

        initializeFonts();

        if (myFootprintCollectionViewModel.getScope() >= 0) {
            scope.setTextColor(context.getResources().getColor(R.color.grey1));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            scope.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        footprintCollectionKey = myFootprintCollectionViewModel.getKey();
        usernameView.setText(myFootprintCollectionViewModel.getUsername());
        levelView.setText(myFootprintCollectionViewModel.getUserLevel());
        title.setText(myFootprintCollectionViewModel.getTitle());
        scope.setText(numberUtils.rangeOrScopeToString(myFootprintCollectionViewModel.getScope()));
        comments.setText(numberUtils.numberToGroupedString(myFootprintCollectionViewModel.getComments()));

        new StarsUtils().calculateStars(starsView, totalStarsView, totalStarsVotedView, totalStarsMiniDecimalsView,
                myFootprintCollectionViewModel.getLikes(), myFootprintCollectionViewModel.getDislikes());

        if (myFootprintCollectionViewModel.getAudio() != null && !myFootprintCollectionViewModel.getAudio().isEmpty()) {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio);
        } else {
            playAudioProfileView.setImageResource(R.drawable.ic_profile_audio_off);
        }

        isDead = myFootprintCollectionViewModel.getStatus() == FootprintStatus.DEAD.getValue();

        if (!isDead) {
            Picasso.with(context)
                    .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + myFootprintCollectionViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                    .placeholder(R.drawable.placeholder_card)
                    .fit()
                    .centerCrop()
                    .into(image);

            deadView.setVisibility(View.GONE);
        } else {
            Picasso.with(context)
                    .load(SERVER_IMAGES_API_BASE_URL_COMPLETE + myFootprintCollectionViewModel.getMedia() + SERVER_IMAGES_MEDIUM_SUFFIX)
                    .placeholder(R.drawable.placeholder_card)
                    .fit()
                    .transform(new BlurTransformation(context))
                    .centerCrop()
                    .into(image);

            deadView.setVisibility(View.VISIBLE);
        }

        if (myFootprintCollectionViewModel.getUserImage() != null && !myFootprintCollectionViewModel.getUserImage().isEmpty()) {
            Picasso.with(context)
                    .load(SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE + myFootprintCollectionViewModel.getUserImage() + SERVER_IMAGES_SMALL_SUFFIX)
                    .placeholder(R.drawable.placeholder_profile)
                    .fit()
                    .centerCrop()
                    .into(userImageView);
        } else {
            userImageView.setImageResource(R.drawable.placeholder_profile);
        }

        EmojiUtils emojiUtils = new EmojiUtils();
        FootprintEmojiCategory emojiCategory = emojiUtils.getFootprintEmojiCategory(myFootprintCollectionViewModel.getCategory());
        this.icEmojiCategory.setBackgroundDrawable(context.getDrawable(emojiCategory.getEmojiImageResource()));
        this.emojiCategory.setText(emojiCategory.getName());
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, comments, timeAgoDistance, totalStarsView, totalStarsVotedView,
                totalStarsMiniDecimalsView, scope, title, emojiCategory);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_my_footprint_collection, parent, false);
    }

    @OnClick(R.id.card_root)
    public void onItemClicked() {
        if (!isDead) {
            MyFootprintCollectionViewModel myFootprintCollectionViewModel = (MyFootprintCollectionViewModel) getContent();
            presenter.onMyFootprintCollectionClicked(myFootprintCollectionViewModel, getPosition());
        } else {
            DialogInfo.openInfo(activity, activity.getFragmentManager(), R.drawable.dialog_flags_dead,
                    activity.getString(R.string.footprint_dead_title), activity.getString(R.string.footprint_dead_description), DialogInfo.HEIGHT_NORMAL_DIALOG);
        }
    }

    @OnLongClick(R.id.card_root)
    public boolean onLongItemClicked() {
        DialogMyFootprintCollection.open(getContext(), activity.getFragmentManager(), () -> presenter.onMyFootprintCollectionDelete(footprintCollectionKey));

        return true;
    }
}
