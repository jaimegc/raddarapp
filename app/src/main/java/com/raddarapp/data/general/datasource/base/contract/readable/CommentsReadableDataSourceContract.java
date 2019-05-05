package com.raddarapp.data.general.datasource.base.contract.readable;


import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.datasource.paginated.PaginatedReadableDataSource;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.domain.model.Comment;

public interface CommentsReadableDataSourceContract extends PaginatedReadableDataSource<String, Comment> {

    PaginatedGenericTotalCollection<Comment> getCommentsByFootprintId(String footprintKey, Integer pageNumber, Page page) throws Exception;
}
