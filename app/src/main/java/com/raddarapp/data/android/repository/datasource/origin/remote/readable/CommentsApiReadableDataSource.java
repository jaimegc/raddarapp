package com.raddarapp.data.android.repository.datasource.origin.remote.readable;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CommentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.CommentToCommentDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.CommentsReadableDataSourceContract;
import com.raddarapp.domain.model.Comment;

import java.util.Collection;

import javax.inject.Inject;

public class CommentsApiReadableDataSource extends EmptyPaginatedReadableDataSource<String, Comment>
        implements CommentsReadableDataSourceContract {

    private final CommentApiClient commentApiClient;
    private final CommentToCommentDtoMapper mapper = new CommentToCommentDtoMapper();

    @Inject
    public CommentsApiReadableDataSource(CommentApiClient commentApiClient) {
        this.commentApiClient = commentApiClient;
    }

    @Override
    public PaginatedGenericTotalCollection<Comment> getCommentsByFootprintId(String footprintKey, Integer pageNumber, Page page) throws Exception {
        int limit = page.getLimit();
        long total;
        ServerResponseCollection<CommentDto> commentsApiResponse = commentApiClient.getCommentsByFootprintId(footprintKey, pageNumber, limit);

        total = commentsApiResponse.getMetadata().getTotalElements();

        Collection<Comment> commentsCollection = mapper.reverseMapCollection(commentsApiResponse.getResponse());

        PaginatedCollection<Comment> commentsPaginated = new PaginatedCollection<>(commentsCollection);
        commentsPaginated.setPage(page);

        commentsPaginated.setHasMore(commentsApiResponse.getMetadata().getPageNumber() < commentsApiResponse.getMetadata().getTotalPages());

        PaginatedGenericTotalCollection<Comment> comments = new PaginatedGenericTotalCollection<>(commentsPaginated, total);

        return comments;
    }
}
