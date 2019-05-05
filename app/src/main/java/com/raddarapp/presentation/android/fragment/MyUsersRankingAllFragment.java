package com.raddarapp.presentation.android.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.UserProfileActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.MyUsersRankingAdapteeCollection;
import com.raddarapp.presentation.android.renderer.MyUsersRankingRendererBuilder;
import com.raddarapp.presentation.android.view.renderer.RVRendererAdapterWithIndex;
import com.raddarapp.presentation.android.view.renderer.RendererBuilderWithIndex;
import com.raddarapp.presentation.general.presenter.MyUsersRankingAllPresenter;
import com.raddarapp.presentation.general.viewmodel.contract.MyUserRankingViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyUsersRankingAllFragment extends BaseNormalFragment implements MyUsersRankingAllPresenter.View {

    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    protected static final String NEW_COMMENTS_EXTRA = "UserProfile.NewCommentsExtra";
    private static final String SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_PROFILE_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_SMALL_SUFFIX = BuildConfig.SERVER_IMAGES_SMALL_SUFFIX;

    @BindView(R.id.recycler_my_users_ranking) RecyclerView myUsersRankingView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;

    private RVRendererAdapterWithIndex<MyUserRankingViewModelContract> myUsersRankingAdapter;
    private MyUsersRankingAdapteeCollection myUsersRankingCollection;
    private ScrollToBottomListener loadMoreListener;

    @Inject @Presenter
    MyUsersRankingAllPresenter myUsersRankingAllPresenter;

    public static MyUsersRankingAllFragment newInstance() {
        MyUsersRankingAllFragment fragment = new MyUsersRankingAllFragment();

        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        initializeViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_influencers_all;
    }

    private void initializeViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myUsersRankingView.setHasFixedSize(true);
        myUsersRankingView.setLayoutManager(layoutManager);

        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> myUsersRankingAllPresenter.onLoadMore());

        myUsersRankingView.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilderWithIndex<MyUserRankingViewModelContract> rendererBuilder
                = new MyUsersRankingRendererBuilder(myUsersRankingAllPresenter, true, context);
        myUsersRankingCollection = new MyUsersRankingAdapteeCollection();
        myUsersRankingAdapter = new RVRendererAdapterWithIndex<>(rendererBuilder, myUsersRankingCollection);
        myUsersRankingView.setAdapter(myUsersRankingAdapter);
    }

    @Override
    public void hideMyUsersRanking() {
        myUsersRankingView.setVisibility(View.GONE);
    }

    @Override
    public void showMyUsersRanking(List<MyUserRankingViewModelContract> myFootprints) {
        myUsersRankingAdapter.addAll(myFootprints);
        myUsersRankingAdapter.notifyDataSetChanged();
        myUsersRankingView.setVisibility(View.VISIBLE);
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
    public void openUserProfile(String userKey, int position) {
        UserProfileActivity.openFromMyUsersRanking(getActivity(), userKey, this);
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