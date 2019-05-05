package com.raddarapp.presentation.android.fragment;

import android.location.Location;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.TerritoryArea;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.activity.UserProfileActivity;
import com.raddarapp.presentation.android.dialog.DialogInfo;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.MyUsersRankingAdapteeCollection;
import com.raddarapp.presentation.android.renderer.MyUsersRankingRendererBuilder;
import com.raddarapp.presentation.android.utils.DimenUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.TutorialUtils;
import com.raddarapp.presentation.android.utils.ViewUtils;
import com.raddarapp.presentation.android.view.adapter.SimpleMenuInfluencersListenerAdapter;
import com.raddarapp.presentation.android.view.menu.FabSpeedDialInfluencersSettings;
import com.raddarapp.presentation.android.view.refreshing.PullToRefreshView;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.MyTerritoryMainPresenter;
import com.raddarapp.presentation.general.presenter.MyUsersRankingPresenter;
import com.raddarapp.presentation.general.viewmodel.TerritoryViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyUsersRankingFragment extends BaseNormalFragment implements AppBarLayout.OnOffsetChangedListener,
        MyTerritoryMainPresenter.View, MyUsersRankingPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SPACE = " ";
    private static final int SMALL_HEIGHT_COORDINATOR_EMPTY_USERS_RANKING = 66;

    @BindView(R.id.appbar) AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.territory) TextView territoryView;
    @BindView(R.id.territory_parent) TextView territoryParentView;
    @BindView(R.id.territory_total_footprints) TextView territoryTotalFootprintsView;
    @BindView(R.id.territory_total_footprints_title) TextView territoryTotalFootprintsTitleView;
    @BindView(R.id.territory_actual_players_title) TextView territoryTotalPlayersTitleView;
    @BindView(R.id.territory_actual_players) TextView territoryTotalPlayersView;
    @BindView(R.id.country_emoji) TextView countryEmojiView;
    @BindView(R.id.text_loading) TextView textLoading;
    @BindView(R.id.text_empty_leaders) TextView textEmptyLeaders;
    @BindView(R.id.loading_view_influencers) LinearLayout loadingViewInfluencers;
    @BindView(R.id.linear_territory_details) LinearLayout linearTerritoryDetails;
    @BindView(R.id.linear_actual_position) LinearLayout linearActualPosition;
    @BindView(R.id.loading_image) ImageView loadingImageView;
    @BindView(R.id.show_territory_son) ImageView showTerritorySon;
    @BindView(R.id.show_territory_parent) ImageView showTerritoryParent;
    @BindView(R.id.show_territory_map) ImageView showTerritoryMap;
    @BindView(R.id.territory_actual_position) TextView territoryActualPositionView;
    @BindView(R.id.territory_actual_position_title) TextView territoryActualPositionTitleView;
    @BindView(R.id.influencers_settings) ImageView influencersSettings;
    @BindView(R.id.leaders_settings) FabSpeedDialInfluencersSettings menuSettingsView;
    @BindView(R.id.loading) AVLoadingIndicatorView loadingView;
    @BindView(R.id.linear_searching) LinearLayout linearSearchingView;
    @BindView(R.id.relative_territory) RelativeLayout relativeTerritoryView;

    @BindView(R.id.coordinator_layout) CoordinatorLayout coordinatorLayoutView;
    @BindView(R.id.recycler_my_users_ranking) RecyclerView myUsersRankingView;
    @BindView(R.id.pull_to_refresh) PullToRefreshView pullToRefreshView;

    @Inject @Presenter
    MyTerritoryMainPresenter myTerritoryMainPresenter;
    @Inject @Presenter
    MyUsersRankingPresenter myUsersRankingPresenter;

    private RVRendererAdapterWithIndex<MyUserRankingViewModelContract> myUsersRankingAdapter;
    private MyUsersRankingAdapteeCollection myUsersRankingCollection;
    private ScrollToBottomListener loadMoreListener;
    private boolean hasMore = false;
    private boolean isEmptyInfluencersClicked = false;

    private TerritoryViewModel territoryViewModel;
    private TerritoryArea territoryArea;

    private ViewUtils viewUtils = new ViewUtils();
    private DimenUtils dimenUtils = new DimenUtils();

    public static MyUsersRankingFragment newInstance() {
        MyUsersRankingFragment fragment = new MyUsersRankingFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_influencers;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        initializeFonts();
        initializeViews();
        initializeMenu();

        pullToRefreshView.setOnRefreshListener(() -> pullToRefreshView.postDelayed(() -> myUsersRankingPresenter.forceRefreshing(), 0));
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, territoryView, territoryTotalFootprintsView,
                territoryTotalFootprintsTitleView, textLoading, territoryParentView, territoryActualPositionView,
                textEmptyLeaders, territoryTotalPlayersTitleView, territoryTotalPlayersView, territoryActualPositionTitleView);
    }

    private void initializeViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myUsersRankingView.setHasFixedSize(true);
        myUsersRankingView.setLayoutManager(layoutManager);
        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> myUsersRankingPresenter.onLoadMore());

        myUsersRankingView.addOnScrollListener(loadMoreListener);
        appBarLayout.addOnOffsetChangedListener(this);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<MyUserRankingViewModelContract> rendererBuilder
                = new MyUsersRankingRendererBuilder(myUsersRankingPresenter, false, context);
        myUsersRankingCollection = new MyUsersRankingAdapteeCollection();
        myUsersRankingAdapter = new RVRendererAdapterWithIndex<>(rendererBuilder, myUsersRankingCollection);
        myUsersRankingView.setAdapter(myUsersRankingAdapter);
    }

    private void initializeMenu() {
        menuSettingsView.setMenuListener(new SimpleMenuInfluencersListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_influencers_refresh_ubication:
                        refreshUbication();
                        break;

                    /*case R.id.action_influencers_ranking_leaders:
                        RankingsActivity.open(getActivity());
                        break;*/
                    case R.id.action_influencers_help:
                        DialogInfo.openInfo(getActivity(), getFragmentManager(), R.drawable.dialog_influencers_ranking,
                                getString(R.string.help_leaders_title), getString(R.string.help_leaders_description), DialogInfo.HEIGHT_BIG_DIALOG);
                        break;
                }

                return false;
            }

            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return super.onPrepareMenu(navigationMenu);
            }
        });
    }

    public void initializeShowTerritoryInfluencers(Location location) {
        deleteAllCache();

        viewUtils.visibilityInvisible(menuSettingsView, influencersSettings, coordinatorLayoutView,
                showTerritoryParent, showTerritorySon);
        viewUtils.visibilityGone(linearActualPosition, textEmptyLeaders, coordinatorLayoutView);
        viewUtils.visibilityVisible(linearSearchingView);

        myTerritoryMainPresenter.loadTerritoryMain(location.getLatitude(), location.getLongitude());
    }

    public void showTerritoryInfluencers(TerritoryViewModel territoryViewModel) {
        showMyTerritoryDetails(territoryViewModel);
    }

    public void saveTerritoryAreaInfluencers(TerritoryArea territoryArea) {
        saveMyTerritoryArea(territoryArea);
    }

    @Override
    public void showMyTerritoryDetails(TerritoryViewModel territoryViewModel) {
        this.territoryViewModel = territoryViewModel;
        showTerritory(territoryViewModel);

        viewUtils.visibilityVisible(linearTerritoryDetails, influencersSettings, menuSettingsView, linearSearchingView);
        viewUtils.visibilityGone(textEmptyLeaders);

        textLoading.setText(getString(R.string.loading_leaders));
        loadingImageView.setImageResource(R.drawable.searching_leaders);
        myUsersRankingPresenter.setTerritoryKey(territoryViewModel.getKey());
        myUsersRankingPresenter.loadMyUsersRankingData();
        isEmptyInfluencersClicked = false;
    }

    public void resetMyUsersRankingAndTerritoryFromMap() {
        deleteAllCache();
        normalCoordinatorLayout();

        viewUtils.visibilityInvisible(menuSettingsView, influencersSettings, coordinatorLayoutView,
                showTerritoryParent, showTerritorySon);
        viewUtils.visibilityGone(linearSearchingView);
        showLoading();
    }

    @OnClick(R.id.loading_view_influencers)
    public void onEmptyInfluencersClicked() {
        if (loadingViewInfluencers.getVisibility() == View.VISIBLE &&
                textEmptyLeaders.getVisibility() == View.VISIBLE && !isEmptyInfluencersClicked) {
            showLoadingInfluencers();
            myUsersRankingPresenter.onEmptyInfluencersClicked();
            isEmptyInfluencersClicked = true;
        }
    }

    @Override
    public void saveMyTerritoryArea(TerritoryArea territoryArea) {
        this.territoryArea = territoryArea;
    }

    private void showTerritory(TerritoryViewModel territoryViewModel) {
        boolean hasTerritorySon;
        territoryView.setText(territoryViewModel.getName());
        countryEmojiView.setText(territoryViewModel.getEmojiCountry());
        territoryParentView.setText(((territoryViewModel.getParentName() != null && !territoryViewModel.getParentName().isEmpty()) ?
                 territoryViewModel.getParentName() : ""));

        territoryTotalFootprintsView.setText(SPACE + territoryViewModel.getTotalFootprints());

        if (getActivity() != null) {
            if (((FootprintMainActivity) getActivity()).getFirstTerritoryKey().isEmpty() ||
                    ((FootprintMainActivity) getActivity()).getFirstTerritoryKey().equals(territoryViewModel.getKey())) {

                ((FootprintMainActivity) getActivity()).setFirstTerritoryKey(territoryViewModel.getKey());

                hasTerritorySon = false;
            } else {
                hasTerritorySon = true;
            }

            if (!((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().contains(territoryViewModel.getKey())) {
                ((FootprintMainActivity) getActivity()).getActualTerritoryKeyList().add(territoryViewModel.getKey());
            }

            if (territoryViewModel.getParentKey() != null && !territoryViewModel.getParentKey().isEmpty()) {

                if (!((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().contains(territoryViewModel.getParentKey())) {
                    ((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().add(territoryViewModel.getParentKey());
                }

                if (hasTerritorySon) {
                    showTerritoryParent.setVisibility(View.VISIBLE);
                    showTerritorySon.setVisibility(View.VISIBLE);
                } else {
                    showTerritoryParent.setVisibility(View.VISIBLE);
                    showTerritorySon.setVisibility(View.INVISIBLE);
                }

            } else {

                // World Map
                if (!((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().contains("")) {
                    ((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().add("");
                }

                if (((FootprintMainActivity) getActivity()).getParentTerritoryKeyList().size() == 1) {
                    showTerritorySon.setVisibility(View.INVISIBLE);
                } else {
                    showTerritorySon.setVisibility(View.VISIBLE);
                }

                showTerritoryParent.setVisibility(View.INVISIBLE);
            }

            initializeMenu();
        }

    }

    @Override
    public void showErrorLoadMyTerritoryDetails() {
        initSnackbarError(R.string.error_load_territory);
    }

    @Override
    public void hideLoading() {
        loadingViewInfluencers.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        textLoading.setText(getString(R.string.loading_territories));

        viewUtils.visibilityVisible(loadingViewInfluencers, linearSearchingView);
        viewUtils.visibilityGone(textEmptyLeaders, linearTerritoryDetails);

        loadingImageView.setImageResource(R.drawable.searching_territory);
    }

    public void onShowTerritoryClicked() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).showTerritoryFromInfluencers(territoryViewModel, territoryArea);
        }
    }

    private void refreshUbication() {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).refreshUbicationFromInfluencers();
        }
    }

    private void onShowTerritoryParentClicked() {
        if (getActivity() != null) {
            String zoneKey;

            zoneKey = ((FootprintMainActivity) getActivity()).onShowTerritoryParentClicked();

            if (!zoneKey.isEmpty()) {
                updateTerritoryFromInfluencers(zoneKey);
            }
        }
    }

    private void onShowTerritorySonClicked() {
        if (getActivity() != null) {
            String zoneKey;

            zoneKey = ((FootprintMainActivity) getActivity()).onShowTerritorySonClicked();

            if (!zoneKey.isEmpty()) {
                updateTerritoryFromInfluencers(zoneKey);
            }
        }
    }

    private void updateTerritoryFromInfluencers(String zoneKey) {
        if (getActivity() != null) {
            ((FootprintMainActivity) getActivity()).updateTerritoryFromInfluencers(zoneKey);
        }
    }

    @Override
    public void showRefreshing() {
        pullToRefreshView.setRefreshing(true);
    }

    @Override
    public void hideRefreshing() {
        pullToRefreshView.setRefreshing(false);
    }

    @Override
    public void hideMyUsersRanking() {
    }

    @Override
    public void showMyUsersRanking(List<MyUserRankingViewModelContract> myUsersRanking) {
        myUsersRankingAdapter.addAll(myUsersRanking);
        myUsersRankingAdapter.notifyDataSetChanged();

        viewUtils.visibilityVisible(coordinatorLayoutView, pullToRefreshView, myUsersRankingView);

        isEmptyInfluencersClicked = false;
    }

    @Override
    public void showHasMore(boolean hasMore) {
        myUsersRankingCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearMyUsersRanking() {
        myUsersRankingAdapter.clear();
        myUsersRankingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmptyMyUsersRanking() {
        viewUtils.visibilityGone(myUsersRankingView, linearSearchingView);
        viewUtils.visibilityVisible(loadingViewInfluencers, textEmptyLeaders, coordinatorLayoutView, linearActualPosition);
        smallCoordinatorLayout();

        isEmptyInfluencersClicked = false;
    }

    private void smallCoordinatorLayout() {
        RelativeLayout.LayoutParams lpHeightCoordinatorLayout = (RelativeLayout.LayoutParams) coordinatorLayoutView.getLayoutParams();
        lpHeightCoordinatorLayout.height = (int) dimenUtils.dpToPx(getActivity(), SMALL_HEIGHT_COORDINATOR_EMPTY_USERS_RANKING);
        coordinatorLayoutView.setLayoutParams(lpHeightCoordinatorLayout);
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) collapsingToolbar.getLayoutParams();
        params.setScrollFlags(0);
    }

    private void normalCoordinatorLayout() {
        RelativeLayout.LayoutParams lpHeightCoordinatorLayout = (RelativeLayout.LayoutParams) coordinatorLayoutView.getLayoutParams();
        lpHeightCoordinatorLayout.height = RelativeLayout.LayoutParams.MATCH_PARENT;
        coordinatorLayoutView.setLayoutParams(lpHeightCoordinatorLayout);
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) collapsingToolbar.getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
    }

    @Override
    public void showMyActualPosition(long myPosition, long total) {
        linearActualPosition.setVisibility(View.VISIBLE);
        territoryActualPositionView.setText(SPACE + myPosition + "º");
        territoryTotalPlayersView.setText(SPACE + total);
    }

    @Override
    public void showMyActualPositionUnknown(long total) {
        linearActualPosition.setVisibility(View.VISIBLE);
        territoryActualPositionView.setText(SPACE + getString(R.string.territory_actual_position_unknown));
        territoryTotalPlayersView.setText(SPACE + total);
    }

    @Override
    public void hideLoadingInfluencers() {
        loadingViewInfluencers.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingInfluencers() {
        viewUtils.visibilityVisible(linearSearchingView);
        viewUtils.visibilityGone(textEmptyLeaders);
        textLoading.setText(getString(R.string.loading_leaders));
    }

    @Override
    public void openUserProfile(String userKey, int position) {
        UserProfileActivity.openFromMyUsersRanking(getActivity(), userKey, this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        pullToRefreshView.setEnabled(verticalOffset == 0);
    }

    public void closeMenus() {
        if (menuSettingsView.isMenuOpen()) {
            menuSettingsView.closeMenu();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pullToRefreshView.setRefreshing(false);
    }

    @OnClick(R.id.show_territory_parent)
    public void onShowTerritoryParentArrowClicked() {
        onShowTerritoryParentClicked();
    }

    @OnClick(R.id.show_territory_son)
    public void onShowTerritorySonArrowClicked() {
        onShowTerritorySonClicked();
    }

    @OnClick(R.id.show_territory_map)
    public void onShowTerritoryMapClicked() {
        onShowTerritoryClicked();
    }


    private void deleteAllCache() {
        myTerritoryMainPresenter.deleteCache();
        myUsersRankingPresenter.deleteCache();
        myUsersRankingPresenter.deleteUsersRankingLocalCache();
        myUsersRankingPresenter.setPageNumber(0);
        clearMyUsersRanking();
    }

    public void onReloadMyUsersRankingClicked() {
        myUsersRankingPresenter.onReloadMyUsersRankingClicked();
    }

    public void initializeTutorialMyRanking() {
        if (getActivity() != null) {
            if (linearTerritoryDetails.getVisibility() == View.VISIBLE) {
                new TutorialUtils(getActivity(), FONT_NAME, relativeTerritoryView, influencersSettings, showTerritoryMap, showTerritoryParent).initializeTutorialUserRanking();
            }
        }
    }
}
