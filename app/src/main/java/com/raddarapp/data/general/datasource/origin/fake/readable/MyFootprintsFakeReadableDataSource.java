package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.MyFootprintReadableDataSourceContract;
import com.raddarapp.domain.model.MyFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.MyFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.AspectRatioType;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintLevel;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintStatus;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.SponsoredType;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.model.enums.VisibilityType;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class MyFootprintsFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, MyFootprint>
        implements MyFootprintReadableDataSourceContract {

    private static final int NUMBER_OF_MY_FOOTPRINTS = 100;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String EMOJI_WORLD = ":earth_africa:";
    private static final String EMOJI_SPAIN = "ES";
    private static final String EMOJI_MEXICO = "MX";
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, MyFootprint> items = new HashMap<>();
    private int primaryKey = 0;
    private MapUtils mapUtils = new MapUtils();

    @Inject
    public MyFootprintsFakeReadableDataSource() {
    }

    @Override
    public MyFootprint getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedGenericTotalCollection<MyFootprint> getMyFootprints(Integer numberPage, Page page) throws Exception {
        Collection<MyFootprint> myFootprints = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_MY_FOOTPRINTS; i++) {
            myFootprints.add(getMyFootprints());
            primaryKey++;
        }

        boolean hasMore = offset + myFootprints.size() < NUMBER_OF_MY_FOOTPRINTS;

        PaginatedCollection<MyFootprint> paginatedFootprints = new PaginatedCollection<>(myFootprints);
        paginatedFootprints.setPage(page);
        paginatedFootprints.setHasMore(hasMore);

        PaginatedGenericTotalCollection<MyFootprint> paginatedFootprintsFake = new PaginatedGenericTotalCollection<>(paginatedFootprints, NUMBER_OF_MY_FOOTPRINTS);

        return paginatedFootprintsFake;
    }

    @Override
    public MyFootprint addMyFootprintInCacheFromNotification(String content) throws Exception {
        return getMyFootprints();
    }

    private MyFootprint getMyFootprints() {
        MyFootprint[] allFootprints = {getMyFootprintImage1(), getMyFootprintImage2(), getMyFootprintImage3(),
                getMyFootprintImage4(), getMyFootprintImage5(), getMyFootprintImage6(), getMyFootprintImage7(),
                getMyFootprintImage8(), getMyFootprintImage9(), getMyFootprintImage10(), getMyFootprintImage11(),
                getMyFootprintImage12(), getMyFootprintImage13()};

        MyFootprint myFootprint = allFootprints[RANDOM.nextInt(allFootprints.length)];

        return myFootprint;
    }

    private MyFootprint getMyFootprintImage1() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-03T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2015/09/09/19/41/cat-932846_1280.jpg")
                .withTitle(("My crazy cat"))
                .withDescription("Enjoing with my love")
                .withComments(50)
                .withViews(443)
                .withLikes(100)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(100)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage2() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-04T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/09/02/15/34/burrowing-3649048_1280.jpg")
                .withTitle("Little owl in nature")
                .withDescription("Amazing animal in nature")
                .withComments(50)
                .withViews(543)
                .withLikes(100)
                .withDislikes(50)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(150)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Alcalá de Guadaira")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage3() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-05T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/05/02/09/29/auto-3368094_1280.jpg")
                .withTitle("Old car in nature")
                .withDescription("Hidden things in nature")
                .withComments(50)
                .withViews(111)
                .withLikes(200)
                .withDislikes(50)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(150)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(250)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage4() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-06T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2017/02/07/13/47/live-your-dream-2045928_1280.jpg")
                .withTitle("Live your dream")
                .withDescription("Live your dream. Do not give up!")
                .withComments(50)
                .withViews(713)
                .withLikes(75)
                .withDislikes(25)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Casco Antiguo")
                .withParentZoneName("Provincia de Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage5() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-07T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2017/08/07/22/36/still-2608676_1280.jpg")
                .withTitle("Memories of my life")
                .withDescription("All my memories")
                .withComments(50)
                .withViews(533)
                .withLikes(250)
                .withDislikes(75)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(175)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(325)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage6() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-22T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2017/01/30/19/09/gastronomy-2021780_1280.jpg")
                .withTitle("Gastronomy Love")
                .withDescription("Cooking with love")
                .withComments(50)
                .withViews(41)
                .withLikes(133)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(131)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(135)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Provincia de Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage7() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-02-08T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2017/01/13/03/02/burgers-1976198_1280.jpg")
                .withTitle("Cholesterol")
                .withDescription("Healthy hamburger...")
                .withComments(50)
                .withViews(144)
                .withLikes(72)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(70)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(74)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage8() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-09T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2016/07/03/17/48/lost-places-1495150_1280.jpg")
                .withTitle("Lost places")
                .withDescription("Lost places of Spain")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(310)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Triana")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage9() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-03-19T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2017/12/15/13/51/polynesia-3021072_1280.jpg")
                .withTitle("Relax...")
                .withDescription("Relax on the beach")
                .withComments(50)
                .withViews(500)
                .withLikes(200)
                .withDislikes(100)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(100)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(300)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage10() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-09T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2016/11/08/05/26/woman-1807533_1280.jpg")
                .withTitle("Wonderful rain")
                .withDescription("Family under the rain")
                .withComments(50)
                .withViews(311)
                .withLikes(99)
                .withDislikes(40)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(59)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(139)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Shangai")
                .withParentZoneName("China")
                .withCountryEmoji(EMOJI_WORLD)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage11() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-11T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2016/10/30/21/12/family-1784371_1280.jpg")
                .withTitle("Family parenting")
                .withDescription("Happy family")
                .withComments(50)
                .withViews(411)
                .withLikes(111)
                .withDislikes(11)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(100)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(122)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage12() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-11T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/01/17/07/06/laptop-3087585_1280.jpg")
                .withTitle("Writing a letter")
                .withDescription("Writing something")
                .withComments(50)
                .withViews(411)
                .withLikes(100)
                .withDislikes(150)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(-50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(250)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Utrera")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private MyFootprint getMyFootprintImage13() {

        final User user = getMyUser();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-04-11T14:25:23.000+0200")
                .build();

        final MyFootprint myFootprintImage =  new MyFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/06/01/19/07/selfie-with-monkey-3446978_960_720.jpg")
                .withTitle("Selfie with a monkey")
                .withDescription("My best selfie with a monkey!")
                .withComments(50)
                .withViews(611)
                .withLikes(300)
                .withDislikes(150)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(150)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(450)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Puerto Princesa")
                .withParentZoneName("Palawan")
                .withCountryEmoji(EMOJI_WORLD)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), myFootprintImage);

        return myFootprintImage;
    }

    private User getMyUser() {
        return new UserBuilder()
                .withKey(String.valueOf(primaryKey))
                .withUsername("jaimegc")
                .withImage("https://avatars1.githubusercontent.com/u/21331906?s=500&v=4")
                .withRange(20001)
                .withFollowers(441)
                .withFollowing(201)
                .withUserRelationship(UserRelationshipType.UNKNOWN.getValue())
                .withLevel(5)
                .withTotalFootprints(100)
                .build();
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
