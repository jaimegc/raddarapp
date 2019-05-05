package com.raddarapp.presentation.general.viewmodel.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.karumi.rosie.repository.PaginatedCollection;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.presentation.android.utils.DateUtils;
import com.raddarapp.presentation.general.viewmodel.CommentViewModel;
import com.raddarapp.presentation.general.viewmodel.builder.CommentViewModelBuilder;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

public class CommentToCommentsViewModelMapper extends Mapper<PaginatedCollection<Comment>, List<CommentViewModel>> {

    @Inject
    public CommentToCommentsViewModelMapper() {}

    @Override
    public List<CommentViewModel> map(PaginatedCollection<Comment> comments) {
        List<CommentViewModel> commentsViewModels = new LinkedList<>();

        commentsViewModels.addAll(mapCommentsToCommentsViewModel(comments));

        return commentsViewModels;
    }

    private List<CommentViewModel> mapCommentsToCommentsViewModel(PaginatedCollection<Comment> comments) {
        List<CommentViewModel> commentsViewModels = new LinkedList<>();
        DateUtils dateUtils = new DateUtils();

        for (Comment comment : comments.getItems()) {
            CommentViewModel commentViewModel =
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

            commentsViewModels.add(commentViewModel);
        }

        return commentsViewModels;
    }

    @Override
    public PaginatedCollection<Comment> reverseMap(List<CommentViewModel> value) {
        throw new UnsupportedOperationException();
    }
}
