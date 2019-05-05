package com.raddarapp.presentation.android.renderer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.NumberUtils;
import com.raddarapp.presentation.android.view.renderer.base.BaseRendererWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsMainPresenter;
import com.raddarapp.presentation.general.viewmodel.FootprintInsigniaMainViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintMainViewModelContract;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class FootprintsInsigniaMainRenderer extends BaseRendererWithIndex<FootprintMainViewModelContract> {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private final FootprintsMainPresenter presenter;
    private static final String SERVER_IMAGES_INSIGNIAS_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_INSIGNIAS_API_BASE_URL_COMPLETE;

    @BindView(R.id.image) ImageView image;
    @BindView(R.id.card_root) RelativeLayout cardRoot;
    @BindView(R.id.scope) TextView scope;
    @BindView(R.id.ic_vote_hearts) ImageView icVoteHearts;
    @BindView(R.id.background_card) RelativeLayout backgroundCard;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.flag_ball_background) View flagBallBackgroundView;
    @BindView(R.id.insignia_hashtag) TextView insigniaHashtag;
    @BindView(R.id.ic_footprint_location) ImageView icFootprintLocationView;

    public FootprintsInsigniaMainRenderer(FootprintsMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void render() {
        super.render();
        Context context = getRootView().getContext();
        FootprintInsigniaMainViewModel footprintInsigniaMainViewModel = (FootprintInsigniaMainViewModel) getContent();
        NumberUtils numberUtils = new NumberUtils();

        initializeFonts();

        if (footprintInsigniaMainViewModel.getScope() >= 0) {
            scope.setTextColor(context.getResources().getColor(R.color.grey1));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_like);
        } else {
            scope.setTextColor(context.getResources().getColor(R.color.red));
            icVoteHearts.setImageResource(R.drawable.ic_vote_hearts_dislike);
        }

        scope.setText(numberUtils.rangeOrScopeToString(footprintInsigniaMainViewModel.getScope()));

        title.setText(footprintInsigniaMainViewModel.getTitle());

        Picasso.with(context)
                .load(SERVER_IMAGES_INSIGNIAS_API_BASE_URL_COMPLETE + footprintInsigniaMainViewModel.getMedia())
                .placeholder(R.drawable.ic_insignia_check_placeholder)
                .fit()
                .centerCrop()
                .into(image);
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getContext(), FONT_NAME, title, insigniaHashtag);

        fontUtils.applyFont(getContext(), FONT_NAME, scope);
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.item_card_footprint_insignia_main, parent, false);
    }

    @OnClick(R.id.flag_ball)
    public void onFlagBallClicked() {
        FootprintInsigniaMainViewModel footprintInsigniaMainViewModel = (FootprintInsigniaMainViewModel) getContent();

        presenter.onClickedFootprintAnimationSelected(footprintInsigniaMainViewModel.getKey());
    }
}
