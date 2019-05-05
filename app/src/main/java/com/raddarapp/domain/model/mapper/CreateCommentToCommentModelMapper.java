package com.raddarapp.domain.model.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.CreateComment;
import com.raddarapp.domain.model.builder.CommentBuilder;
import com.raddarapp.presentation.android.utils.DateUtils;

import java.util.Random;

import javax.inject.Inject;

public class CreateCommentToCommentModelMapper extends Mapper<CreateComment, Comment> {

    private static final Integer MAX_KEY_RANDOM_CACHE = Integer.MAX_VALUE;
    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public CreateCommentToCommentModelMapper(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public Comment map(CreateComment createComment) {
        DateUtils dateUtils = new DateUtils();

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(new Random().nextInt(MAX_KEY_RANDOM_CACHE)))
                .withBody(createComment.getBody())
                .withCreationMoment(dateUtils.millisecondsToString(System.currentTimeMillis()))
                .withOwner(userProfilePreferencesDataSource.userProfileToUserMapper())
                .build();

        return commentImage;
    }

    @Override
    public CreateComment reverseMap(Comment comment) {
        throw new UnsupportedOperationException();
    }
}
