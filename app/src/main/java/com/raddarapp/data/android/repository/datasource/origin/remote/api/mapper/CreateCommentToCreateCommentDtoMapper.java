package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.CreateCommentDtoBuilder;
import com.raddarapp.domain.model.CreateComment;
import com.raddarapp.domain.model.builder.CreateCommentBuilder;


public class CreateCommentToCreateCommentDtoMapper extends Mapper<CreateComment, CreateCommentDto> {

    @Override
    public CreateComment reverseMap(CreateCommentDto createCommentDto) {
        CreateComment createComment = new CreateCommentBuilder()
                .withKey(createCommentDto.getId())
                .withBody(createCommentDto.getBody())
                .withCreationMoment(createCommentDto.getCreationMoment())
                .build();

        return createComment;
    }

    @Override
    public CreateCommentDto map(CreateComment createComment) {
        final CreateCommentDto createCommentDto = new CreateCommentDtoBuilder()
                .withId(createComment.getKey())
                .withBody(createComment.getBody())
                .withCreationMoment(createComment.getCreationMoment())
                .build();

        return createCommentDto;
    }
}
