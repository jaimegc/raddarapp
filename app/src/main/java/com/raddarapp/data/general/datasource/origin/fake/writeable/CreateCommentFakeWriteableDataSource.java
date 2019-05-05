package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateCommentWriteableDataSourceContract;
import com.raddarapp.domain.model.CreateComment;
import com.raddarapp.domain.model.builder.CreateCommentBuilder;

import java.util.Random;

import javax.inject.Inject;

public class CreateCommentFakeWriteableDataSource extends EmptyWriteableDataSource<String, CreateComment>
        implements CreateCommentWriteableDataSourceContract {

    private static final Random RANDOM = new Random(System.nanoTime());
    private static final String CREATE_COMMENT_KEY = "fakeKey";
    private static final long FAKE_DELAY_MILLISECONDS = 1500;

    @Inject
    public CreateCommentFakeWriteableDataSource() {
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreateComment createComment(String footprintMainKey, CreateComment createComment) throws Exception {
        fakeDelay();

        final CreateComment createdComment =  new CreateCommentBuilder()
                .withKey(CREATE_COMMENT_KEY + RANDOM.nextInt(Integer.MAX_VALUE))
                .withBody("El cumple fue una pasada ")
                .withCreationMoment("")
                .build();

        return createdComment;
    }
}
