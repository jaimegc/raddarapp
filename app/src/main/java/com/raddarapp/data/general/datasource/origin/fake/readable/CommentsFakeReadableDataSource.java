package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.CommentsReadableDataSourceContract;
import com.raddarapp.domain.model.Comment;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.CommentBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.model.util.GenerateUsers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class CommentsFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, Comment>
    implements CommentsReadableDataSourceContract {

    private static final int NUMBER_OF_COMMENTS = 50;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, Comment> items = new HashMap<>();
    private int primaryKey = 0;
    private GenerateUsers generateUsers = new GenerateUsers();

    @Inject
    public CommentsFakeReadableDataSource() {
    }

    @Override
    public Comment getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedGenericTotalCollection<Comment> getCommentsByFootprintId(String footprintKey, Integer pageNumber, Page page) throws Exception {
        Collection<Comment> comments = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_COMMENTS; i++) {
            comments.add(getComment());
            primaryKey++;
        }

        boolean hasMore = offset + comments.size() < NUMBER_OF_COMMENTS;
        PaginatedCollection<Comment> paginatedComments = new PaginatedCollection<>(comments);
        paginatedComments.setPage(page);
        paginatedComments.setHasMore(hasMore);

        PaginatedGenericTotalCollection<Comment> paginatedFootprintsFake = new PaginatedGenericTotalCollection<>(paginatedComments, NUMBER_OF_COMMENTS);

        return paginatedFootprintsFake;
    }

    private Comment getComment() {
        Comment[] allComments = {getCommentImage1(), getCommentImage2(), getCommentImage3(),
                getCommentImage4(), getCommentImage5(), getCommentImage6(), getCommentImage7(),
                getCommentImage8(), getCommentImage9(), getCommentImage10(), getCommentImage11(),
                getCommentImage12(), getCommentImage13(), getCommentImage14(), getCommentImage15(),
                getCommentImage16(), getCommentImage17(), getCommentImage18()};

        Comment comment = allComments[RANDOM.nextInt(allComments.length)];

        return comment;
    }

    private Comment getCommentImage1() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Have fun!")
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage2() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("This is incredible")
                .withCreationMoment("2017-06-02T10:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage3() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Congratulations! Wonderful photo")
                .withCreationMoment("2017-02-22T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage4() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Great moment!")
                .withCreationMoment("2016-02-02T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage5() {

        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("I'm happy for you!")
                .withCreationMoment("2017-01-12T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage6() {

        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("I hate your photo!")
                .withCreationMoment("2016-11-07T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage7() {

        final User user = generateUsers.generateUser7(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("I love your photo")
                .withCreationMoment("2017-01-11T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage8() {

        final User user = generateUsers.generateUser8(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("The best photo")
                .withCreationMoment("2016-12-18T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage9() {

        final User user = generateUsers.generateUser9(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Amazing!")
                .withCreationMoment("2017-10-18T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage10() {

        final User user = generateUsers.generateUser10(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Hello my friend! What is your camera?")
                .withCreationMoment("2018-03-05T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage11() {

        final User user = generateUsers.generateUser11(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Nice image!")
                .withCreationMoment("2018-03-05T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage12() {

        final User user = generateUsers.generateUser12(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Do you have more pictures like this one?")
                .withCreationMoment("2019-02-15T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage13() {

        final User user = generateUsers.generateUser13(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("This image is a ****")
                .withCreationMoment("2019-04-15T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage14() {

        final User user = generateUsers.generateUser14(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("I don't like it")
                .withCreationMoment("2019-03-25T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage15() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("You are a magician! I love you!")
                .withCreationMoment("2019-04-28T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage16() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Thanks! You are my hero!")
                .withCreationMoment("2019-01-12T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage17() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("Really??? :O")
                .withCreationMoment("2019-04-22T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private Comment getCommentImage18() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final Comment commentImage =  new CommentBuilder()
                .withKey(String.valueOf(primaryKey))
                .withBody("OMG!!!")
                .withCreationMoment("2018-01-22T14:25:23.000+0200")
                .withOwner(user)
                .build();

        items.put(String.valueOf(primaryKey), commentImage);

        return commentImage;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
