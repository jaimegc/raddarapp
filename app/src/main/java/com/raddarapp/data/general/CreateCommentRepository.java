package com.raddarapp.data.general;

import com.karumi.rosie.repository.RosieRepository;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateCommentWriteableDataSourceContract;
import com.raddarapp.data.general.factory.CreateCommentDataSourceFactory;
import com.raddarapp.domain.model.CreateComment;

import javax.inject.Inject;

public class CreateCommentRepository extends RosieRepository<String, CreateComment> {

    private final CreateCommentWriteableDataSourceContract createCommentWriteableDataSource;

    @Inject
    CreateCommentRepository(CreateCommentDataSourceFactory createCommentDataSourceFactory) {

        createCommentWriteableDataSource = createCommentDataSourceFactory.createWriteableDataSource();

        addWriteableDataSources(createCommentWriteableDataSource);
    }

    public CreateComment createComment(String footprintMainKey, CreateComment createComment) throws Exception {
        CreateComment createdFootprint;

        createdFootprint = createCommentWriteableDataSource.createComment(footprintMainKey, createComment);

        return createdFootprint;
    }
}
