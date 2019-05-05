package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedMyUserRankingTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyUserRankingReadableDataSourceContract;
import com.raddarapp.domain.model.MyUserRanking;
import com.raddarapp.domain.model.builder.MyUserRankingBuilder;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class MyUsersRankingFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, MyUserRanking>
        implements MyUserRankingReadableDataSourceContract {

    private static final int NUMBER_OF_MY_USERS_RANKING = 100;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final int MAX_SCOPE_ZONE = 10000;
    private static final Random RANDOM = new Random(System.nanoTime());
    private static final long USER_POSITION = 1;
    private Map<String, MyUserRanking> items = new HashMap<>();
    private int primaryKey = 0, totalScopeZone;
    private MapUtils mapUtils = new MapUtils();

    @Inject
    public MyUsersRankingFakeReadableDataSource() {
    }

    @Override
    public MyUserRanking getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedMyUserRankingTotalCollection<MyUserRanking> getMyUsersRankingByTerritoryId(String territoryKey, Integer numberPage, Page page) throws Exception {
        Collection<MyUserRanking> myUserRankings = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        if (offset == 0) {
            totalScopeZone = MAX_SCOPE_ZONE;
        }

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_MY_USERS_RANKING; i++) {
            myUserRankings.add(getMyUserRankings());
            primaryKey++;
            totalScopeZone -= 50;
        }

        boolean hasMore = offset + myUserRankings.size() < NUMBER_OF_MY_USERS_RANKING;

        PaginatedCollection<MyUserRanking> paginatedMyUserRankings = new PaginatedCollection<>(myUserRankings);
        paginatedMyUserRankings.setPage(page);
        paginatedMyUserRankings.setHasMore(hasMore);

        PaginatedMyUserRankingTotalCollection<MyUserRanking> paginatedMyUserRankingsFake = new PaginatedMyUserRankingTotalCollection<>(
                paginatedMyUserRankings, USER_POSITION, offset * limit, NUMBER_OF_MY_USERS_RANKING);

        return paginatedMyUserRankingsFake;
    }

    private MyUserRanking getMyUserRankings() {
        MyUserRanking[] allFootprints = {getMyUserRankingImage1(), getMyUserRankingImage2(), getMyUserRankingImage3(),
                getMyUserRankingImage4(), getMyUserRankingImage5(), getMyUserRankingImage6(), getMyUserRankingImage7(),
                getMyUserRankingImage8(), getMyUserRankingImage9(), getMyUserRankingImage10(), getMyUserRankingImage11(),
                getMyUserRankingImage12(), getMyUserRankingImage13(), getMyUserRankingImage14()};

        MyUserRanking MyUserRanking = allFootprints[RANDOM.nextInt(allFootprints.length)];

        return MyUserRanking;
    }

    private MyUserRanking getMyUserRankingImage1() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("James")
                .withImage("https://cdn.pixabay.com/photo/2015/02/18/07/35/photographer-640419_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(20001)
                .withFollowers(412)
                .withFollowing(200)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(40)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(20)
                .withUserCompliments(10)
                .withTotalFootprintsZone(10)
                .withTotalLikesZone(10)
                .withTotalDislikesZone(1)
                .withTotalLikes(630)
                .withTotalDislikes(210)
                .withTotalFootprintsDead(2)
                .build();


        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage2() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Iván")
                .withImage("https://cdn.pixabay.com/photo/2018/04/27/03/50/portrait-3353699__480.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(21081)
                .withFollowers(262)
                .withFollowing(100)
                .withUserRelationship(UserRelationshipType.FOLLOW_ME.getValue())
                .withLevel(62)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(55)
                .withUserCompliments(110)
                .withTotalFootprintsZone(14)
                .withTotalLikesZone(75)
                .withTotalDislikesZone(25)
                .withTotalLikes(700)
                .withTotalDislikes(133)
                .withTotalFootprintsDead(3)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage3() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Carmen")
                .withImage("https://cdn.pixabay.com/photo/2017/05/13/12/40/fashion-2309519_1280.jpg")
                .withRange(12031)
                .withFollowers(332)
                .withFollowing(240)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(23)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(11)
                .withUserCompliments(150)
                .withTotalFootprintsZone(4)
                .withTotalLikesZone(290)
                .withTotalDislikesZone(150)
                .withTotalFootprintsDead(4)
                .withTotalLikes(911)
                .withTotalDislikes(54)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage4() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Fran")
                .withImage("https://cdn.pixabay.com/photo/2018/04/29/01/23/skin-3358873_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(30222)
                .withFollowers(831)
                .withFollowing(1200)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(33)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(5255)
                .withUserCompliments(1000)
                .withTotalFootprintsZone(210)
                .withTotalLikesZone(271)
                .withTotalDislikesZone(271)
                .withTotalFootprintsDead(10)
                .withTotalLikes(1400)
                .withTotalDislikes(670)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage5() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Dani")
                .withImage("https://cdn.pixabay.com/photo/2016/05/23/23/32/human-1411499_1280.jpg")
                .withRange(21021)
                .withFollowers(332)
                .withFollowing(510)
                .withUserRelationship(UserRelationshipType.FRIEND.getValue())
                .withLevel(11)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(5)
                .withUserCompliments(1150)
                .withTotalFootprintsZone(1)
                .withTotalLikesZone(100)
                .withTotalDislikesZone(150)
                .withTotalFootprintsDead(9)
                .withTotalLikes(813)
                .withTotalDislikes(442)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage6() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("María")
                .withImage("https://cdn.pixabay.com/photo/2015/09/02/13/24/girl-919048_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(38253)
                .withFollowers(444)
                .withFollowing(2340)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(66)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(71)
                .withUserCompliments(6610)
                .withTotalFootprintsZone(30)
                .withTotalLikesZone(100)
                .withTotalDislikesZone(200)
                .withTotalFootprintsDead(13)
                .withTotalLikes(900)
                .withTotalDislikes(10)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage7() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Migue")
                .withImage("https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg")
                .withRange(22684)
                .withFollowers(133)
                .withFollowing(13400)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(88)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(555)
                .withUserCompliments(4210)
                .withTotalFootprintsZone(140)
                .withTotalLikesZone(39)
                .withTotalDislikesZone(10)
                .withTotalFootprintsDead(15)
                .withTotalLikes(1400)
                .withTotalDislikes(300)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage8() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Eusebio")
                .withImage("https://cdn.pixabay.com/photo/2018/04/27/03/50/portrait-3353699__480.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(36677)
                .withFollowers(133)
                .withFollowing(111)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(899)
                .withUserCompliments(5510)
                .withTotalFootprintsZone(410)
                .withTotalLikesZone(400)
                .withTotalDislikesZone(20)
                .withTotalLikes(322)
                .withTotalDislikes(110)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage9() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Mercelol")
                .withImage("https://cdn.pixabay.com/photo/2015/10/03/17/30/selfie-970042_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(34677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(639)
                .withUserCompliments(1710)
                .withTotalFootprintsZone(310)
                .withTotalLikesZone(499)
                .withTotalDislikesZone(20)
                .withTotalFootprintsDead(19)
                .withTotalLikes(815)
                .withTotalDislikes(222)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage10() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Carlos")
                .withImage("https://cdn.pixabay.com/photo/2016/01/03/00/13/selfie-1118885_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(22677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(139)
                .withUserCompliments(3710)
                .withTotalFootprintsZone(140)
                .withTotalLikesZone(99)
                .withTotalDislikesZone(20)
                .withTotalFootprintsDead(12)
                .withTotalLikes(900)
                .withTotalDislikes(200)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage11() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Sunny")
                .withImage("https://cdn.pixabay.com/photo/2015/09/25/21/36/selfie-958261_1280.jpg")
                .withAudio("https://raw.githubusercontent.com/jaimegc/raddarapp/master/app/src/main/res/raw/audio_profile_fake.opus")
                .withRange(22677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(639)
                .withUserCompliments(2710)
                .withTotalFootprintsZone(240)
                .withTotalLikesZone(299)
                .withTotalDislikesZone(20)
                .withTotalFootprintsDead(20)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage12() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Paco")
                .withImage("https://cdn.pixabay.com/photo/2014/05/23/23/42/man-352477_1280.jpg")
                .withRange(41677)
                .withFollowers(1293)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(249)
                .withUserCompliments(3710)
                .withTotalFootprintsZone(80)
                .withTotalLikesZone(299)
                .withTotalDislikesZone(20)
                .withTotalFootprintsDead(15)
                .withTotalLikes(900)
                .withTotalDislikes(333)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage13() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Manoli")
                .withImage("https://cdn.pixabay.com/photo/2017/04/10/20/15/paragliding-2219652_1280.jpg")
                .withRange(32677)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(339)
                .withUserCompliments(3710)
                .withTotalFootprintsZone(510)
                .withTotalLikesZone(299)
                .withTotalDislikesZone(20)
                .withTotalFootprintsDead(12)
                .withTotalLikes(941)
                .withTotalDislikes(444)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private MyUserRanking getMyUserRankingImage14() {
        final MyUserRanking myUserRanking = new MyUserRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("Sandra")
                .withImage("https://cdn.pixabay.com/photo/2018/02/12/00/47/people-3147188_1280.jpg")
                .withRange(29977)
                .withFollowers(193)
                .withFollowing(11)
                .withUserRelationship(UserRelationshipType.FOLLOWING.getValue())
                .withLevel(99)
                .withTotalScopeZone(totalScopeZone)
                .withTotalFootprints(939)
                .withUserCompliments(3710)
                .withTotalFootprintsZone(160)
                .withTotalLikesZone(499)
                .withTotalDislikesZone(40)
                .withTotalFootprintsDead(18)
                .withTotalLikes(733)
                .withTotalDislikes(444)
                .build();

        items.put(String.valueOf(primaryKey), myUserRanking);

        return myUserRanking;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
