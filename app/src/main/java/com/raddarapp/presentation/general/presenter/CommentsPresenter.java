package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.usecase.GetCommentsByFootprint;
import com.raddarapp.domain.usecase.GetFootprintMainDetails;
import com.raddarapp.domain.usecase.GetMyFootprintDetails;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.CommentToCommentViewModelMapper;
import com.raddarapp.presentation.general.viewmodel.mapper.CommentToCommentsViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CommentsPresenter extends BasePresenterRefreshWithLoading<CommentsPresenter.View> {

    private static final int NUMBER_OF_COMMENTS_PER_PAGE = 30;
    private static final int INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS = 0;
    private static final int INDEX_COMMENTS_MY_FOOTPRINT_DETAILS = 1;
    private final CommentToCommentsViewModelMapper mapperComments;
    private final CommentToCommentViewModelMapper mapperComment;
    private final GetCommentsByFootprint getCommentsByFootprint;
    private final GetMyFootprintDetails getMyFootprintDetails;
    private final GetFootprintMainDetails getFootprintsDetails;
    private Integer pageNumber = 0;
    private String footprintKey;
    // This is because we are using the same cache for comments in footprints main details and comments full screen
    private boolean isFirsTime = true;
    private FootprintMain footprintMain;
    private MyFootprint myFootprint;
    private int indexScreen;
    private long totalComments;

    @Inject
    public CommentsPresenter(UseCaseHandler useCaseHandler, CommentToCommentsViewModelMapper mapperComments,
            GetCommentsByFootprint getCommentsByFootprint, GetFootprintMainDetails getFootprintsDetails,
            CommentToCommentViewModelMapper mapperComment, GetMyFootprintDetails getMyFootprintDetails) {
        super(useCaseHandler);
        this.mapperComments = mapperComments;
        this.getCommentsByFootprint = getCommentsByFootprint;
        this.getFootprintsDetails = getFootprintsDetails;
        this.getMyFootprintDetails = getMyFootprintDetails;
        this.mapperComment = mapperComment;
    }

    @Override
    public void initialize() {
        super.initialize();

        switch (indexScreen) {
            case INDEX_COMMENTS_FOOTPRINT_MAIN_DETAILS:
                loadFootprintMainDetails();
                break;
            case INDEX_COMMENTS_MY_FOOTPRINT_DETAILS:
                loadMyFootprintDetails();
                break;
        }
    }

    @Override
    public void update() {
        super.update();

        loadData(isFirsTime);
    }

    public void loadData(boolean isFirsTime) {
        getView().hideComments();

        if (isFirsTime) {
            getCommentsByFootprint.deleteCache();
            loadComments();
        } else {
            PaginatedCollection<Comment> allCommentsInCache = getCommentsByFootprint.getAllCommentsInCache();

            if (allCommentsInCache.getPage().getLimit() == 0) {
                loadComments();
            } else {

                try {
                    getView().updateComments(totalComments);
                } catch (Exception e) {}

                getView().clearComments();
                showComments(allCommentsInCache);
            }
        }

    }

    private void loadFootprintMainDetails() {
        createUseCaseCall(getFootprintsDetails)
                .args(footprintKey)
                .useCaseName(GetFootprintMainDetails.USE_CASE_GET_FOOTPRINT_MAIN_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getFootprintDetails(FootprintMain footprintMain) {
                        CommentsPresenter.this.footprintMain = footprintMain;
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadMyFootprintDetails() {
        createUseCaseCall(getMyFootprintDetails)
                .args(footprintKey)
                .useCaseName(GetMyFootprintDetails.USE_CASE_GET_MY_FOOTPRINT_DETAILS)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getMyFootprintDetails(MyFootprint myFootprint) {
                        CommentsPresenter.this.myFootprint = myFootprint;
                    }
                })
                .onError(error -> false)
                .execute();
    }

    private void loadComments() {
        if (isFirsTime) {
            showLoading();
        }

        createUseCaseCall(getCommentsByFootprint)
                .args(footprintKey, pageNumber, Page.withOffsetAndLimit(pageNumber * NUMBER_OF_COMMENTS_PER_PAGE, NUMBER_OF_COMMENTS_PER_PAGE))
                .useCaseName(GetCommentsByFootprint.USE_CASE_GET_COMMENTS_BY_FOOTPRINT_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getCommentsByFootprintId(PaginatedGenericTotalCollection<Comment> comments) {
                        getCommentsByFootprint.setHasMore(comments.getPaginatedCollection().hasMore());
                        pageNumber++;

                        try {
                            totalComments = comments.getTotalItems();
                            getView().updateComments(totalComments);
                        } catch (Exception e) {}

                        showComments(comments.getPaginatedCollection());
                    }
                })
                .onError(error -> {
                    showError();
                    return false;
                }).execute();
    }

    public void updateFootprintMainComments()  {
        footprintMain.updateComments();
    }

    public void updateMyFootprintComments()  {
        myFootprint.updateComments();
    }

    private void showComments(PaginatedCollection<Comment> comments) {

        try {
            List<CommentViewModel> commentsViewModels = mapperComments.map(comments);

            getView().showComments(commentsViewModels);
            getView().showHasMore(comments.hasMore());

            hideLoading();
        } catch (Exception e) {}
    }

    private void showError() {
        hideLoading();
    }

    public void onLoadMore() {
        isFirsTime = false;
        loadComments();
    }

    public void setFootprintKey(String footprintKey) {
        this.footprintKey = footprintKey;
    }

    public void addCommentInCache(Comment comment) {
        List<CommentViewModel> comments = new ArrayList<>();
        comments.add(mapperComment.map(comment));
        comments.addAll(mapperComments.map(getCommentsByFootprint.getAllCommentsInCache()));

        List<CommentViewModel> commentsViewModel = mapperComments.map(
                getCommentsByFootprint.addCommentInCache(comment));

        getView().updateComments(commentsViewModel);
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    public void setFirsTime(boolean firsTime) {
        isFirsTime = firsTime;
    }

    public void setIndexScreen(int indexScreen) {
        this.indexScreen = indexScreen;
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void hideComments();

        void showComments(List<CommentViewModel> comments);

        void showHasMore(boolean hasMore);

        // We use List because RVRendererAdapter hasn't add(position, item) method
        void updateComments(List<CommentViewModel> comment);

        void clearComments();

        void updateComments(long totalComments);
    }
}
