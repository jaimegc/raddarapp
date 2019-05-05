package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CreateCommentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.CreateCommentToCreateCommentDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateCommentWriteableDataSourceContract;
import com.raddarapp.domain.model.CreateComment;

import javax.inject.Inject;

public class CreateCommentApiWriteableDataSource extends EmptyWriteableDataSource<String, CreateComment>
        implements CreateCommentWriteableDataSourceContract {

    private final CreateCommentApiClient createCommentApiClient;
    private final CreateCommentToCreateCommentDtoMapper mapper = new CreateCommentToCreateCommentDtoMapper();

    @Inject
    public CreateCommentApiWriteableDataSource(CreateCommentApiClient createCommentApiClient) {
        this.createCommentApiClient = createCommentApiClient;
    }

    @Override
    public CreateComment createComment(String footprintMainKey, CreateComment createComment) throws Exception {
        CreateComment createdComment = null;
        CreateCommentDto createdCommentDto = null;

        createdCommentDto = createCommentApiClient.createComment(footprintMainKey, mapper.map(createComment));
        createdComment = mapper.reverseMap(createdCommentDto);

        return createdComment;
    }
}