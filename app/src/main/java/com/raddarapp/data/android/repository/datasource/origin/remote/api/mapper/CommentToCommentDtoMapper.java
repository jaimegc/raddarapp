package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CommentDto;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.CommentBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CommentToCommentDtoMapper extends Mapper<Comment, CommentDto> {

    @Override
    public CommentDto map(Comment comment) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Comment reverseMap(CommentDto commentDto) {
        User owner = new UserBuilder()
                .withKey(commentDto.getOwnerDto().getId())
                .withUsername(commentDto.getOwnerDto().getUsername())
                .withImage(commentDto.getOwnerDto().getImage())
                .withRange(commentDto.getOwnerDto().getRange())
                .withFollowers(commentDto.getOwnerDto().getFollowers())
                .withFollowing(commentDto.getOwnerDto().getFollowing())
                .withUserRelationship(commentDto.getOwnerDto().getUserRelationship())
                .withLevel(commentDto.getOwnerDto().getLevel())
                .withTotalFootprints(commentDto.getOwnerDto().getTotalFootprints())
                .build();

        Comment comment = new CommentBuilder()
                .withKey(commentDto.getId())
                .withBody(commentDto.getBody())
                .withCreationMoment(commentDto.getCreationMoment())
                .withOwner(owner)
                .build();

        return comment;
    }

    public Collection<Comment> reverseMapCollection(List<CommentDto> commentsDto) {
        List<Comment> commentsCollection = new LinkedList<>();

        for (CommentDto commentDto : commentsDto) {
            commentsCollection.add(reverseMap(commentDto));
        }

        return commentsCollection;
    }
}
