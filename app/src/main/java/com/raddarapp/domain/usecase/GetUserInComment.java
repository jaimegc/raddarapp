package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.CommentsRepository;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;


public class GetUserInComment extends RosieUseCase {

    public static final String USE_CASE_GET_USER_IN_COMMENT = "getCommentsByFootprintId";

    private final CommentsRepository commentsRepository;
    private boolean hasMore = false;

    @Inject
    public GetUserInComment(CommentsRepository commentsRepository) {
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

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @UseCase(name = USE_CASE_GET_USER_IN_COMMENT)
    public void getUserInComment(String userKey) throws Exception {
        User user = null;
        PaginatedCollection<Comment> comments = getAllCommentsInCache();

        for (Comment comment : comments.getItems()) {
            if (comment.getOwner().getKey().equals(userKey)) {
                user = comment.getOwner();
                break;
            }
        }

        notifySuccess(user);
    }
}
