package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.CreateComment;
import com.raddarapp.domain.model.builder.CreateCommentBuilder;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.general.viewmodel.CreateCommentViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.CreateCommentViewModelBuilder;

import javax.inject.Inject;

public class CreateCommentToCreateCommentViewModelMapper extends Mapper<CreateComment, CreateCommentViewModel> {

    @Inject
    public CreateCommentToCreateCommentViewModelMapper() {}

    @Override
    public CreateCommentViewModel map(CreateComment createdComment) {
        DateUtils dateUtils = new DateUtils();
        CreateCommentViewModel createdCommentViewModel = new CreateCommentViewModelBuilder()
                .withKey(createdComment.getKey())
                .withBody(createdComment.getBody())
                .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(createdComment.getCreationMoment())))
                .build();

        return createdCommentViewModel;
    }

    @Override
    public CreateComment reverseMap(CreateCommentViewModel createCommentViewModel) {

        final CreateComment createComment = new CreateCommentBuilder()
                .withBody(createCommentViewModel.getBody())
                .withCreationMoment(createCommentViewModel.getCreationMoment())
                .build();

        return createComment;
    }
}
