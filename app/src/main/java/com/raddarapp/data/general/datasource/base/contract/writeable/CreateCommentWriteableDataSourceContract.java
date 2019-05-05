package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.CreateComment;

public interface CreateCommentWriteableDataSourceContract extends WriteableDataSource<String, CreateComment> {

    CreateComment createComment(String footprintMainKey, CreateComment createComment) throws Exception;
}
