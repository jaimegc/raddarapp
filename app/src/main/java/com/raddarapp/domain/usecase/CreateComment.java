package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.raddarapp.data.general.CreateCommentRepository;

import javax.inject.Inject;

public class CreateComment extends RosieUseCase {

    public static final String USE_CASE_CREATE_COMMENT = "createComment";

    private final CreateCommentRepository createCommentMainRepository;

    @Inject
    public CreateComment(CreateCommentRepository createCommentMainRepository) {
        this.createCommentMainRepository = createCommentMainRepository;
    }

    @UseCase(name = USE_CASE_CREATE_COMMENT)
    public void createComment(String footprintMainKey, com.raddarapp.domain.model.CreateComment createComment) throws Exception {
        com.raddarapp.domain.model.CreateComment createdCommented =
                createCommentMainRepository.createComment(footprintMainKey, createComment);
        notifySuccess(createdCommented);
    }
}
