package com.raddarapp.presentation.android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import com.pkmmte.view.CircularImageView;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.renderer.CommentsAdapteeCollection;
import com.raddarapp.presentation.android.renderer.CommentsRendererBuilder;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;
import com.raddarapp.presentation.general.presenter.CommentsPresenter;
import com.raddarapp.presentation.general.presenter.CreateCommentPresenter;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.CreateCommentViewModel;
import com.raddarapp.presentation.general.viewmodel.contract.CommentViewModelContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentsFragment extends BaseNormalFragment implements CreateCommentPresenter.View, CommentsPresenter.View {

    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String COMMENTS_FOOTPRINT_KEY_EXTRA = "CommentsFootprintDetails.FootprintKey";
    private static final String INDEX_SCREEN_EXTRA = "FootprintDetails.IndexScreen";
    private static final int INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS = 0;
    private static final int INDEX_COMMENTS_MY_FOOTPRINT_DETAILS = 1;
    private static final int INDEX_COMMENTS_USER_FOOTPRINT_DETAILS = 2;
    private static final int INDEX_COMMENTS_MY_FOOTPRINT_COLLECTION_DETAILS = 3;
    private static final int INDEX_COMMENTS_FOOTPRINT_RANKING_DETAILS = 4;

    @BindView(R.id.recycler_comments) RecyclerView commentsView;
    @BindView(R.id.loading_view_raddar) View loadingView;
    @BindView(R.id.linear_loading) LinearLayout linearLoadingView;
    @BindView(R.id.edit_create_comment) EditText editCreateCommentView;
    @BindView(R.id.loading_create_comment) ProgressBar loadingCreateComment;
    @BindView(R.id.create_comment) ImageView createCommentView;
    @BindView(R.id.user_image_me) CircularImageView userImageMeView;

    @Inject @Presenter
    CommentsPresenter commentsPresenter;
    @Inject @Presenter
    CreateCommentPresenter createCommentPresenter;

    private RVRendererAdapter<CommentViewModelContract> commentsAdapter;
    private CommentsAdapteeCollection commentsCollection;
    private ScrollToBottomListener loadMoreListener;
    private String footprintKey;
    private PreferencesUtils preferencesUtils;

    private int indexScreen;
    private long totalComments = 0;

    public static CommentsFragment newInstance(String footprintMainKey, int indexScreen) {
        CommentsFragment fragment = new CommentsFragment();

        Bundle args = new Bundle();

        args.putString(COMMENTS_FOOTPRINT_KEY_EXTRA, footprintMainKey);
        args.putInt(INDEX_SCREEN_EXTRA, indexScreen);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comments;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);
        footprintKey = getArguments().getString(COMMENTS_FOOTPRINT_KEY_EXTRA);
        indexScreen = getArguments().getInt(INDEX_SCREEN_EXTRA);
        commentsPresenter.setFootprintKey(footprintKey);
        commentsPresenter.setIndexScreen(indexScreen);
        commentsPresenter.setFirsTime(true);
        createCommentPresenter.setCommentsPresenter(commentsPresenter);
        preferencesUtils = new PreferencesUtils(getActivity());

        new FileUtils(getActivity()).loadMyUserProfileImageCache(SERVER_IMAGES_API_BASE_URL_COMPLETE, userImageMeView, true);

        initializeEditText();
        initializeViews();
        initializeFonts();
    }

    private void initializeEditText() {
        createCommentView.setEnabled(false);

        editCreateCommentView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (text.length() != 0) {
                    createCommentView.setEnabled(true);
                    createCommentView.setImageResource(R.drawable.ic_create_comment_enable);
                } else {
                    createCommentView.setEnabled(false);
                    createCommentView.setImageResource(R.drawable.ic_create_comment_disable);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editCreateCommentView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                onCreateCommentClicked();
                return true;
            }
            return false;
        });

        editCreateCommentView.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editCreateCommentView.setRawInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    private void initializeViews() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        commentsView.setHasFixedSize(true);
        commentsView.setLayoutManager(layoutManager);
        initializeAdapter();

        loadMoreListener = new ScrollToBottomListener(layoutManager, () -> commentsPresenter.onLoadMore());

        commentsView.addOnScrollListener(loadMoreListener);
    }

    private void initializeFonts() {
        new FontUtils().applyFont(getActivity(), FONT_NAME, editCreateCommentView);
    }

    private void initializeAdapter() {
        RendererBuilder<CommentViewModelContract> rendererBuilder = new CommentsRendererBuilder(commentsPresenter);
        commentsCollection = new CommentsAdapteeCollection();
        commentsAdapter = new RVRendererAdapter<>(rendererBuilder, commentsCollection);
        commentsView.setAdapter(commentsAdapter);
    }

    @Override
    public void showHasMore(boolean hasMore) {
        commentsCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }

    @Override
    public void hideLoading() {
        linearLoadingView.setVisibility(View.GONE);
        loadingView.setVisibility(View.GONE);
        editCreateCommentView.setEnabled(true);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
        editCreateCommentView.setEnabled(false);
    }

    @Override
    public void hideComments() {
        commentsView.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<CommentViewModel> comments) {
        commentsAdapter.addAll(comments);
        commentsAdapter.notifyDataSetChanged();
        commentsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateComments(List<CommentViewModel> comments) {
        commentsAdapter.clear();
        commentsAdapter.addAll(comments);
        commentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearComments() {
        commentsAdapter.clear();
        commentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateComments(long comments) {
        totalComments = comments;
    }

    @OnClick(R.id.create_comment)
    public void onCreateCommentClicked() {

        if (preferencesUtils.getRole() == UserRoleType.USER.getValue()) {
            if (createCommentPresenter.isValidComment(editCreateCommentView.getText().toString())) {
                if (indexScreen == INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS) {
                    commentsPresenter.updateFootprintMainComments();
                } else if (indexScreen == INDEX_COMMENTS_MY_FOOTPRINT_DETAILS) {
                    commentsPresenter.updateMyFootprintComments();
                }

                totalComments++;
                createCommentPresenter.onCreateCommentClicked(footprintKey, editCreateCommentView.getText().toString(), false);
                editCreateCommentView.setText(null);
                ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editCreateCommentView.getWindowToken(), 0);
            }
        } else {
            initSnackbarError(R.string.error_local_create_comment_admin);
        }
    }

    @OnClick(R.id.back)
    public void onBackClicked() {
        getActivity().onBackPressed();
    }

    @Override
    public void onPause() {
        super.onPause();
        commentsPresenter.setFirsTime(false);
    }

    public long getTotalComments() {
        return totalComments;
    }

    @Override
    public void showCreatedComment(CreateCommentViewModel createCommentViewModel) {
        // No implemented. In the future, update comment id

    }

    @Override
    public void showCreateCommentError() {

    }

    @Override
    public void showCreateCommentLoading() {
        loadingCreateComment.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideCreateCommentLoading() {
        loadingCreateComment.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLocalCommentEmpty() {
        initSnackbarError(R.string.error_local_empty_comment);
    }
}
