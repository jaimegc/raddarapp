package com.raddarapp.presentation.android.fragment;

import android.media.MediaPlayer;
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
import com.raddarapp.presentation.android.activity.MyFootprintCollectionDetailsActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.MyFootprintsCollectionAdapteeCollection;
import com.raddarapp.presentation.android.renderer.MyFootprintsCollectionRendererBuilder;
import com.raddarapp.presentation.android.utils.AnimationUtils;
import com.raddarapp.presentation.android.view.RVRendererAdapterRemovable;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.MyFootprintsCollectionPresenter;
import com.raddarapp.presentation.general.viewmodel.contract.MyFootprintCollectionViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFootprintsCollectionFragment extends BaseNormalFragment implements MyFootprintsCollectionPresenter.View {

    private static final int toiletSoundResource = R.raw.toilet_flush;

    @BindView(R.id.recycler_my_footprints_collection) RecyclerView footprintsView;
    @BindView(R.id.loading_bottom) LinearLayout loadingBottomView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.linear_empty) LinearLayout linearEmptyView;

    private RVRendererAdapterRemovable<MyFootprintCollectionViewModelContract> myFootprintsCollectionAdapter;
    private MyFootprintsCollectionAdapteeCollection myFootprintsCollection;
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
    MyFootprintsCollectionPresenter myFootprintsCollectionPresenter;

    public static MyFootprintsCollectionFragment newInstance() {
        MyFootprintsCollectionFragment fragment = new MyFootprintsCollectionFragment();

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
        return R.layout.fragment_my_footprints_collection;
    }

    private void initializeViews() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        footprintsView.setHasFixedSize(true);
        footprintsView.setLayoutManager(layoutManager);

        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> myFootprintsCollectionPresenter.onLoadMore());

        footprintsView.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<MyFootprintCollectionViewModelContract> rendererBuilder
                = new MyFootprintsCollectionRendererBuilder(getActivity(), myFootprintsCollectionPresenter);
        myFootprintsCollection = new MyFootprintsCollectionAdapteeCollection();
        myFootprintsCollectionAdapter = new RVRendererAdapterRemovable<>(rendererBuilder, myFootprintsCollection);
        footprintsView.setAdapter(myFootprintsCollectionAdapter);
    }

    @Override
    public void hideMyFootprintsCollection() {
        footprintsView.setVisibility(View.GONE);
    }

    @Override
    public void showMyFootprintsCollection(List<MyFootprintCollectionViewModelContract> myFootprintsCollection) {
        myFootprintsCollectionAdapter.addAll(myFootprintsCollection);
        myFootprintsCollectionAdapter.notifyDataSetChanged();
        footprintsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        myFootprintsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void clearMyFootprintsCollection() {
        myFootprintsCollectionAdapter.clear();
        myFootprintsCollectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void openMyFootprintCollectionDetails(String myFootprintCollectionKey, long comments, int position) {
        MyFootprintCollectionDetailsActivity.open(getActivity(), myFootprintCollectionKey, comments);
    }

    @Override
    public void showEmptyMyFootprintsCollection() {
        footprintsView.setVisibility(View.GONE);
        linearEmptyView.setVisibility(View.VISIBLE);
        linearLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingBottom() {
        footprintsView.scrollToPosition(myFootprintsCollectionAdapter.getItemCount() - 1);
        animationUtils.alphaAnimationWithInitial(loadingBottomView, 10, View.VISIBLE, View.VISIBLE, false);
    }

    @Override
    public void onMyFootprintCollectionDeleted(int position) {
        if (userProfilePreferencesDataSource.hasSounds()) {
            handlerSounds.postDelayed(runnableToiletFlushSound, 10);
        }

        myFootprintsCollectionAdapter.onItemDismiss(position);
    }

    @Override
    public void showMyFootprintCollectionDeletedError() {
        initSnackbarLongError(R.string.error_delete_footprint_collection);
    }

    @Override
    public void hideLoadingBottom() {
        loadingBottomView.setVisibility(View.GONE);
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