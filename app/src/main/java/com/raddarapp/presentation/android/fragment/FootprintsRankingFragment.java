package com.raddarapp.presentation.android.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.presentation.android.activity.FootprintRankingDetailsActivity;
import com.raddarapp.presentation.android.activity.UserProfileActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.FootprintsRankingAdapteeCollection;
import com.raddarapp.presentation.android.renderer.FootprintsRankingRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.raddarapp.presentation.android.view.PreCachingLayoutManager;
import com.raddarapp.presentation.android.view.RVRendererAdapterRemovable;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.FootprintsRankingPresenter;
import com.raddarapp.presentation.general.viewmodel.contract.FootprintRankingViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FootprintsRankingFragment extends BaseNormalFragment implements FootprintsRankingPresenter.View {

    private static final String EMOJI_WORLD = ":earth_africa:";

    @BindView(R.id.recycler_footprints_ranking) RecyclerView footprintsView;
    @BindView(R.id.loading_bottom) LinearLayout loadingBottomView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.linear_empty) LinearLayout linearEmptyView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;

    private RVRendererAdapterRemovable<FootprintRankingViewModelContract> footprintsRankingAdapter;
    private FootprintsRankingAdapteeCollection footprintsRanking;
    private ScrollToBottomListener loadMoreListener;
    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    private AnimationUtils animationUtils = new AnimationUtils();

    @Inject @Presenter
    FootprintsRankingPresenter footprintsRankingPresenter;

    public static FootprintsRankingFragment newInstance() {
        FootprintsRankingFragment fragment = new FootprintsRankingFragment();
        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        userProfilePreferencesDataSource = new MyUserProfilePreferencesDataSource(getActivity());
        initializeViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_footprints_ranking;
    }

    private void initializeViews() {
        int height = new DimenUtils().calculateScreenHeight(getActivity().getWindowManager());
        countryEmojiView.setText(new EmojiUtils().emojiCountry(EMOJI_WORLD));
        PreCachingLayoutManager layoutManager = new PreCachingLayoutManager(getActivity(), PreCachingLayoutManager.VERTICAL, false, height * 2);
        layoutManager.setExtraLayoutSpace(height * 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);

        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> footprintsRankingPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<FootprintRankingViewModelContract> rendererBuilder
                = new FootprintsRankingRendererBuilder(getActivity(), footprintsRankingPresenter,
                new DimenUtils().calculateRecyclerFootprintMainHeight(getActivity(), getActivity().getWindowManager()));
        footprintsRanking = new FootprintsRankingAdapteeCollection();
        footprintsRankingAdapter = new RVRendererAdapterRemovable<>(rendererBuilder, footprintsRanking);
        footprintsView.setAdapter(footprintsRankingAdapter);
    }

    @Override
    public void hideFootprintsRanking() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showFootprintsRanking(List<FootprintRankingViewModelContract> footprintsRanking) {
        footprintsRankingAdapter.addAll(footprintsRanking);
        footprintsRankingAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        footprintsRanking.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearFootprintsRanking() {
        footprintsRankingAdapter.clear();
        footprintsRankingAdapter.notifyDataSetChanged();
    }

    @Override
    public void openFootprintRankingDetails(String footprintRankingKey, long comments, int position) {
        FootprintRankingDetailsActivity.open(getActivity(), footprintRankingKey, comments, position + 1);
    }

    @Override
    public void showEmptyFootprintsRanking() {
        footprintsView.setVisibility(View.GONE);
        linearEmptyView.setVisibility(View.VISIBLE);
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void openUserProfile(String footprintRankingKey, String userKey, int position) {
        UserProfileActivity.openFromFootprintRanking(getActivity(), footprintRankingKey, userKey, this);
    }

    @Override
    public void showLoadingBottom() {
        // When we have two or more views
        //footprintsView.scrollToPosition(footprintsRankingAdapter.getItemCount() - 1);
        //animationUtils.alphaAnimationWithInitial(loadingBottomView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void hideLoadingBottom() {
        //loadingBottomView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        linearLoadingView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }
}