package com.raddarapp.data.general;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.CommentsReadableDataSourceContract;
import com.raddarapp.data.general.factory.CommentsDataSourceFactory;
import com.raddarapp.domain.model.Comment;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class CommentsRepository extends PaginatedRosieRepository<String, Comment> {

    private final CommentsReadableDataSourceContract commentsReadableDataSource;

    @Inject
    CommentsRepository(CommentsDataSourceFactory commentsDataSourceFactory,
            PaginatedCacheDataSource<String, Comment> inMemoryPaginatedCache) {

        commentsReadableDataSource = commentsDataSourceFactory.createReadableDataSource();

        addCacheDataSources(inMemoryPaginatedCache);
        addPaginatedCacheDataSources(inMemoryPaginatedCache);

        addReadableDataSources(commentsReadableDataSource);
        addPaginatedReadableDataSources(commentsReadableDataSource);
    }

    public PaginatedGenericTotalCollection<Comment> getCommentsByFootprintId(String footprintKey, Integer pageNumber, Page page) throws Exception {
        PaginatedGenericTotalCollection<Comment> comments;

        comments = commentsReadableDataSource.getCommentsByFootprintId(footprintKey, pageNumber, page);

        populatePaginatedCaches(page, comments.getPaginatedCollection());

        return comments;
    }

    public PaginatedCollection<Comment> addCommentInCache(Comment newComment, PaginatedCollection<Comment> paginatedComments) throws Exception {
        List<Comment> comments = new LinkedList<>();

        comments.add(newComment);

        for (Comment comment : paginatedComments.getItems()) {
            comments.add(comment);
        }

        Comparator<Comment> comparator = (one, two) -> two.getCreationMoment().compareTo(one.getCreationMoment());
        Collections.sort(comments, comparator);

        PaginatedCollection<Comment> finalPaginatedComments = new PaginatedCollection<>(comments);
        paginatedComments.setPage(paginatedComments.getPage());
        populatePaginatedCaches(paginatedComments.getPage(), finalPaginatedComments);

        return finalPaginatedComments;
    }
}
