package com.raddarapp.presentation.android.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.UserFootprintDetailsActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.UserFootprintsAdapteeCollection;
import com.raddarapp.presentation.android.renderer.UserFootprintsRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.UserFootprintsAllPresenter;
import com.raddarapp.presentation.general.viewmodel.UserProfileViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.UserFootprintViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFootprintsAllFragment extends BaseNormalFragment implements UserFootprintsAllPresenter.View {

    private static final String USER_KEY_EXTRA = "UserProfile.UserKey";
    private static final String FOOTPRINT_KEY_EXTRA = "UserProfile.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "UserProfile.IndexScreen";

    @BindView(R.id.recycler_user_footprints) RecyclerView footprintsView;
    @BindView(R.id.loading_bottom) LinearLayout loadingBottomView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;

    private RVRendererAdapterWithIndex<UserFootprintViewModelContract> userFootprintsAdapter;
    private UserFootprintsAdapteeCollection userFootprintsCollection;
    private ScrollToBottomListener loadMoreListener;

    private AnimationUtils animationUtils = new AnimationUtils();

    private String footprintKey;
    private String userKey;
    private int indexScreen;

    @Inject @Presenter
    UserFootprintsAllPresenter userFootprintsAllPresenter;

    public static UserFootprintsAllFragment newInstance(String footprintKey, String userKey, int indexScreen) {
        UserFootprintsAllFragment fragment = new UserFootprintsAllFragment();

        Bundle args = new Bundle();

        args.putString(FOOTPRINT_KEY_EXTRA, footprintKey);
        args.putString(USER_KEY_EXTRA, userKey);
        args.putInt(INDEX_SCREEN_EXTRA, indexScreen);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        indexScreen = getArguments().getInt(INDEX_SCREEN_EXTRA);
        footprintKey = getArguments().getString(FOOTPRINT_KEY_EXTRA);
        userKey = getArguments().getString(USER_KEY_EXTRA);
        userFootprintsAllPresenter.setIndexScreen(indexScreen);
        userFootprintsAllPresenter.setUserKey(userKey);
        userFootprintsAllPresenter.setFootprintKey(footprintKey);

        initializeViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_footprints_all;
    }

    private void initializeViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);

        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> userFootprintsAllPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<UserFootprintViewModelContract> rendererBuilder =
        new UserFootprintsRendererBuilder(getActivity(), userFootprintsAllPresenter, true,
                indexScreen, userKey, footprintKey);
        userFootprintsCollection = new UserFootprintsAdapteeCollection();
        userFootprintsAdapter = new RVRendererAdapterWithIndex<>(rendererBuilder, userFootprintsCollection);
        footprintsView.setAdapter(userFootprintsAdapter);
    }

    @Override
    public void hideUserFootprints() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showUserFootprints(List<UserFootprintViewModelContract> userFootprints) {
        userFootprintsAdapter.addAll(userFootprints);
        userFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        userFootprintsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearUserFootprints() {
        userFootprintsAdapter.clear();
        userFootprintsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openUserFootprintDetails(String footprintKey, long comments, int position) {
        UserFootprintDetailsActivity.open(getActivity(), indexScreen, footprintKey, comments);
    }

    @Override
    public void showEmptyUserFootprints(List<UserFootprintViewModelContract> userFootprintEmptyViewModel) {
        userFootprintsAdapter.addAll(userFootprintEmptyViewModel);
        userFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingBottom() {
        footprintsView.scrollToPosition(userFootprintsAdapter.getItemCount() - 1);
        animationUtils.alphaAnimationWithInitial(loadingBottomView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void hideLoadingBottom() {
        loadingBottomView.setVisibility(View.GONE);
    }

    @Override
    public void showUserProfile(UserProfileViewModel userProfileDetailsViewModel) {

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