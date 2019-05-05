package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.google.common.collect.Sets;
import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMyUserProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.UpdateMyUserProfile;
import com.raddarapp.domain.model.builder.MyUserProfileBuilder;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.UserStatusType;

import javax.inject.Inject;

public class UpdateMyUserProfileFakeWriteableDataSource extends EmptyWriteableDataSource<String, UpdateMyUserProfile>
        implements UpdateMyUserProfileWriteableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String NOTIFICATION_TOPIC_COMMENTS = "new_comment";
    private static final String NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS = "favourite_flag";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD = "flag_dead";

    @Inject
    public UpdateMyUserProfileFakeWriteableDataSource() {
    }

    @Override
    public MyUserProfile updateMyUserProfile(UpdateMyUserProfile updateMyUserProfile) throws Exception {
        fakeDelay();

        final MyUserProfile userProfile = new MyUserProfileBuilder()
                .withKey("12345678")
                .withName("Jaime")
                .withSurname("GC")
                .withUsername("jaimegc")
                .withImage("https://avatars1.githubusercontent.com/u/21331906?s=500&v=4")
                .withEmail("jaimegc@facebook.com")
                .withRange(20001)
                .withFollowers(441)
                .withFollowing(201)
                .withTotalFootprints(100)
                .withUserCompliments(100)
                .withLevel(5)
                .withPercentage(70)
                .withAccessToken("abcdefghijklmnopqrs")
                .withRefreshToken("tuvwxyz")
                .withRefreshTokenUpdated(System.currentTimeMillis())
                .withRangeMined(0)
                .withRangeNotMined(0)
                .withRangeMinedLocalAccumulated(0)
                .withGender(UserGenderType.MALE.getValue())
                .withBirthdate("2000-01-01T00:00:00.000Z")
                .withMyPromoCode("1234A")
                .withRole(UserRoleType.USER.getValue())
                .withStatus(UserStatusType.ACTIVE.getValue())
                .withNotificationTopics(Sets.newHashSet(NOTIFICATION_TOPIC_COMMENTS, NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS, NOTIFICATION_TOPIC_FOOTPRINTS_DEAD))
                .withTotalLikes(100)
                .withTotalDislikes(25)
                .withTotalFootprintsDead(4)
                .build();

        return userProfile;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
