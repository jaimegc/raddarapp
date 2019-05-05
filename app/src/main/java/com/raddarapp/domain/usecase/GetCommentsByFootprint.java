package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.CommentsRepository;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.Comment;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;


public class GetCommentsByFootprint extends RosieUseCase {

    public static final String USE_CASE_GET_COMMENTS_BY_FOOTPRINT_ID = "getCommentsByFootprintId";

    private final CommentsRepository commentsRepository;
    private boolean hasMore = false;

    @Inject
    public GetCommentsByFootprint(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public PaginatedCollection<Comment> getAllCommentsInCache() {
        Collection<Comment> all;

        try {
            all = commentsRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<Comment> commentsMain = new PaginatedCollection<>(all);
        commentsMain.setPage(page);
        commentsMain.setHasMore(hasMore);

        return commentsMain;
    }

    public void deleteCache() {
        try {
            commentsRepository.deleteAll();
        } catch (Exception e) {}
    }


    public PaginatedCollection<Comment> addCommentInCache(Comment comment) {
        try {
            PaginatedCollection<Comment> allCommentsInCache = getAllCommentsInCache();
            return commentsRepository.addCommentInCache(comment, allCommentsInCache);
        } catch (Exception e) {
            return null;
        }
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_COMMENTS_BY_FOOTPRINT_ID)
    public void getCommentsByFootprintId(String footprintKey, Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<Comment> commentsMain = commentsRepository.getCommentsByFootprintId(footprintKey, pageNumber, page);
        notifySuccess(commentsMain);
    }
}
