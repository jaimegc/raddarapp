package com.raddarapp.presentation.general.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.usecase.GetCommentsByFootprint;
import com.raddarapp.presentation.general.presenter.base.BasePresenterRefreshWithLoading;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.mapper.CommentToCommentsViewModelMapper;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class CommentsFootprintDetailsPresenter extends BasePresenterRefreshWithLoading<CommentsFootprintDetailsPresenter.View> {

    // Incremented +1 to check more than 3
    private static final int MAX_NUMBER_OF_COMMENTS_FOOTPRINT_DETAILS_PER_PAGE = 4;
    private final CommentToCommentsViewModelMapper mapper;
    private final GetCommentsByFootprint getCommentsByFootprint;
    private int offset = 0;
    private String footprintKey;
    private long totalComments;

    @Inject
    public CommentsFootprintDetailsPresenter(UseCaseHandler useCaseHandler, CommentToCommentsViewModelMapper mapper,
            GetCommentsByFootprint getCommentsByFootprint) {
        super(useCaseHandler);
        this.mapper = mapper;
        this.getCommentsByFootprint = getCommentsByFootprint;
    }

    @Override
    public void update() {
        super.update();

        loadData();
    }

    public void loadData() {
        showLoading();

        PaginatedCollection<Comment> allCommentsInCache = getCommentsByFootprint.getAllCommentsInCache();

        if (allCommentsInCache.getPage().getLimit() == 0) {
            loadComments();
        } else {

            try {
                getView().updateComments(totalComments);
            } catch (Exception e) {}

            List<Comment> comments = new LinkedList<>();

            for (Comment comment : allCommentsInCache.getItems()) {
                comments.add(comment);
            }

            Comparator<Comment> comparator = (one, two) -> two.getCreationMoment().compareTo(one.getCreationMoment());
            Collections.sort(comments, comparator);

            PaginatedCollection<Comment> allOrderedCommentsInCache = new PaginatedCollection<>(comments);
            allOrderedCommentsInCache.setPage(allOrderedCommentsInCache.getPage());

            showComments(allOrderedCommentsInCache);
            offset = allCommentsInCache.getItems().size();
        }
    }

    private void loadComments() {
        showLoading();
        createUseCaseCall(getCommentsByFootprint)
                .args(footprintKey, 0, Page.withOffsetAndLimit(offset, MAX_NUMBER_OF_COMMENTS_FOOTPRINT_DETAILS_PER_PAGE))
                .useCaseName(GetCommentsByFootprint.USE_CASE_GET_COMMENTS_BY_FOOTPRINT_ID)
                .onSuccess(new OnSuccessCallback() {
                    @Success
                    public void getCommentsByFootprintId(PaginatedGenericTotalCollection<Comment> comments) {
                        offset = comments.getPaginatedCollection().getPage().getOffset() + MAX_NUMBER_OF_COMMENTS_FOOTPRINT_DETAILS_PER_PAGE;

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

    private void showComments(PaginatedCollection<Comment> comments) {
        try {
            List<CommentViewModel> commentsViewModels = mapper.map(comments);

            if (commentsViewModels.size() >= MAX_NUMBER_OF_COMMENTS_FOOTPRINT_DETAILS_PER_PAGE) {
                getView().showThreeComments(commentsViewModels, true);
            } else if (commentsViewModels.size() == 3) {
                getView().showThreeComments(commentsViewModels, false);
            } else if (commentsViewModels.size() == 2) {
                getView().showTwoComments(commentsViewModels);
            } else if (commentsViewModels.size() == 1) {
                getView().showOneComment(commentsViewModels.get(0));
            } else {
                getView().showEmptyComments();
            }

            hideLoading();
        } catch (Exception e) {}
    }

    private void showError() {
        hideLoading();
    }

    public void setFootprintKey(String footprintKey) {
        this.footprintKey = footprintKey;
    }

    public void addCommentInCache(Comment comment) {
        showComments(getCommentsByFootprint.addCommentInCache(comment));
    }

    public void deleteCache() {
        getCommentsByFootprint.deleteCache();
    }

    @Override
    public void forceRefreshing() {
        // No implemented
    }

    public void setTotalComments(long totalComments) {
        this.totalComments = totalComments;
    }

    public interface View extends BasePresenterRefreshWithLoading.View {
        void showEmptyComments();

        void showOneComment(CommentViewModel comment);

        void showTwoComments(List<CommentViewModel> comments);

        void showThreeComments(List<CommentViewModel> comments, boolean moreThanThree);

        void updateComments(long totalComments);
    }
}
