package com.raddarapp.presentation.android.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.presentation.android.activity.MyFootprintDetailsActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.MyFootprintsAdapteeCollection;
import com.raddarapp.presentation.android.renderer.MyFootprintsRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.view.RVRendererAdapterRemovable;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.MyFootprintsAllPresenter;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFootprintsAllFragment extends BaseNormalFragment implements MyFootprintsAllPresenter.View {

    private static final int toiletSoundResource = R.raw.toilet_flush;

    @BindView(R.id.recycler_my_footprints) RecyclerView footprintsView;
    @BindView(R.id.loading_bottom) LinearLayout loadingBottomView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.linear_empty) LinearLayout linearEmptyView;

    private RVRendererAdapterRemovable<MyFootprintViewModelContract> myFootprintsAdapter;
    private MyFootprintsAdapteeCollection myFootprintsCollection;
    private ScrollToBottomListener loadMoreListener;

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    final Handler handlerSounds = new Handler();
    private MediaPlayer mediaPlayer;

    private AnimationUtils animationUtils = new AnimationUtils();

    final Runnable runnableToiletFlushSound = new Runnable() {
        public void run() {
            mediaPlayer = MediaPlayer.create(context, toiletSoundResource);

            mediaPlayer.setOnCompletionListener(mediaPlayerCompleted -> {
                mediaPlayerCompleted.release();
                handlerSounds.removeCallbacks(this);
            });

            mediaPlayer.start();
        }
    };

    @Inject @Presenter
    MyFootprintsAllPresenter myFootprintsAllPresenter;

    public static MyFootprintsAllFragment newInstance() {
        MyFootprintsAllFragment fragment = new MyFootprintsAllFragment();

        Bundle args = new Bundle();

        fragment.setArguments(args);

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
        return R.layout.fragment_my_footprints_all;
    }

    private void initializeViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);

        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> myFootprintsAllPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<MyFootprintViewModelContract> rendererBuilder
                = new MyFootprintsRendererBuilder(getActivity(), myFootprintsAllPresenter, true, context);
        myFootprintsCollection = new MyFootprintsAdapteeCollection();
        myFootprintsAdapter = new RVRendererAdapterRemovable<>(rendererBuilder, myFootprintsCollection);
        footprintsView.setAdapter(myFootprintsAdapter);
    }

    @Override
    public void hideMyFootprints() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showMyFootprints(List<MyFootprintViewModelContract> myFootprints) {
        myFootprintsAdapter.addAll(myFootprints);
        myFootprintsAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        myFootprintsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearMyFootprints() {
        myFootprintsAdapter.clear();
        myFootprintsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openMyFootprintDetails(String myFootprintKey, long comments, int position) {
        MyFootprintDetailsActivity.open(getActivity(), myFootprintKey, comments, false);
    }

    @Override
    public void showEmptyMyFootprints() {
        footprintsView.setVisibility(View.GONE);
        linearEmptyView.setVisibility(View.VISIBLE);
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingBottom() {
        footprintsView.scrollToPosition(myFootprintsAdapter.getItemCount() - 1);
        animationUtils.alphaAnimationWithInitial(loadingBottomView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void hideLoadingBottom() {
        loadingBottomView.setVisibility(View.GONE);
    }

    @Override
    public void onMyFootprintDeleted(int position, boolean isDead, long scope, long likes, long dislikes) {
        if (userProfilePreferencesDataSource.hasSounds()) {
            handlerSounds.postDelayed(runnableToiletFlushSound, 10);
        }

        userProfilePreferencesDataSource.setTotalFootprints(userProfilePreferencesDataSource.getTotalFootprints() - 1);
        userProfilePreferencesDataSource.setTotalLikes(userProfilePreferencesDataSource.getTotalLikes() - likes);
        userProfilePreferencesDataSource.setTotalDislikes(userProfilePreferencesDataSource.getTotalDislikes() - dislikes);

        if (isDead) {
            userProfilePreferencesDataSource.setTotalFootprintsDead(userProfilePreferencesDataSource.getTotalFootprintsDead() - 1);
            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() + Math.abs(scope));
        } else {
            userProfilePreferencesDataSource.setRange(userProfilePreferencesDataSource.getRange() - scope);
        }

        myFootprintsAdapter.onItemDismiss(position);
    }

    @Override
    public void showMyFootprintDeletedError() {
        initSnackbarLongError(R.string.error_delete_footprint);
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