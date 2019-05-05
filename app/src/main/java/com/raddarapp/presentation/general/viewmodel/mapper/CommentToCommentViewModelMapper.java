package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.CommentViewModelBuilder;

import javax.inject.Inject;

public class CommentToCommentViewModelMapper extends Mapper<Comment, CommentViewModel> {

    @Inject
    public CommentToCommentViewModelMapper() {}

    @Override
    public CommentViewModel map(Comment comment) {
        DateUtils dateUtils = new DateUtils();

        final CommentViewModel commentViewModel =
                new CommentViewModelBuilder()
                        .withKey(comment.getKey())
                        .withBody(comment.getBody())
                        .withCreationMoment(String.valueOf(dateUtils.stringDateToMilliseconds(comment.getCreationMoment())))
                        .withUserKey(comment.getOwner().getKey())
                        .withUsername("@" + comment.getOwner().getUsername())
                        .withUserLevel(String.valueOf(comment.getOwner().getLevel()))
                        .withPhoto(comment.getOwner().getImage())
                        .withUserRelationship(comment.getOwner().getUserRelationship())
                        .build();

        return commentViewModel;
    }

    @Override
    public Comment reverseMap(CommentViewModel commentViewModel) {
        throw new UnsupportedOperationException();
    }
}
