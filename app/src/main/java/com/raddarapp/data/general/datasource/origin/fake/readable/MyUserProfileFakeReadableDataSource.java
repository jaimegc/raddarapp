package com.raddarapp.data.general.datasource.origin.fake.readable;

import com.google.common.collect.Sets;
import com.karumi.rosie.repository.datasource.EmptyReadableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserProfileReadableDataSourceContract;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.MyUserPassword;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.builder.MyUserProfileBuilder;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.domain.model.enums.UserRoleType;
import com.raddarapp.domain.model.enums.UserStatusType;

import javax.inject.Inject;

public class MyUserProfileFakeReadableDataSource extends EmptyReadableDataSource<String, MyUserProfile>
        implements MyUserProfileReadableDataSourceContract {

    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String NOTIFICATION_TOPIC_COMMENTS = "new_comment";
    private static final String NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS = "favourite_flag";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD = "flag_dead";

    @Inject
    MyUserProfilePreferencesDataSource userProfilePreferencesDataSource;

    @Override
    public MyUserProfile loginFacebook(MyUserLoginSocialToken userFacebookToken) throws Exception {
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
                .withNotificationToken("4321A")
                .withTotalLikes(100)
                .withTotalDislikes(25)
                .withTotalFootprintsDead(4)
                .build();

        return userProfile;
    }

    @Override
    public MyUserProfile loginGoogle(MyUserLoginSocialToken userGoogleToken) throws Exception {
        fakeDelay();

        final MyUserProfile userProfile = new MyUserProfileBuilder()
                .withKey("12345678")
                .withName("Jaime")
                .withSurname("GC")
                .withUsername("jaimegc")
                .withImage("https://avatars1.githubusercontent.com/u/21331906?s=500&v=4")
                .withEmail("jaimegc@google.com")
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
                .withNotificationToken("4321A")
                .withTotalLikes(100)
                .withTotalDislikes(25)
                .withTotalFootprintsDead(4)
                .build();

        return userProfile;
    }

    @Override
    public MyUserProfile login(MyUserPassword userPassword) throws Exception {
        fakeDelay();

        final MyUserProfile userProfile = new MyUserProfileBuilder()
                .withKey("12345678")
                .withName("Jaime")
                .withSurname("GC")
                .withUsername("jaimegc")
                .withImage("https://avatars1.githubusercontent.com/u/21331906?s=500&v=4")
                .withEmail("jaimegc@login.com")
                .withRange(20001)
                .withFollowers(441)
                .withFollowing(201)
                .withTotalFootprints(100)
                .withUserCompliments(100)
                .withLevel(5)
                .withPercentage(40)
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
                .withNotificationToken("4321A")
                .withTotalLikes(100)
                .withTotalDislikes(25)
                .withTotalFootprintsDead(4)
                .build();

        return userProfile;
    }

    @Override
    public boolean logout() throws Exception {
        userProfilePreferencesDataSource.logout();

        return userProfilePreferencesDataSource.getUsername().isEmpty();
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
