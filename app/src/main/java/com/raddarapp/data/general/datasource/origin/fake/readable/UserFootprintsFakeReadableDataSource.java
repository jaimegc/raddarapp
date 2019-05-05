package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.UserFootprintReadableDataSourceContract;
import com.raddarapp.domain.model.UserFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.UserFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.AspectRatioType;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintLevel;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintStatus;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.SponsoredType;
import com.raddarapp.domain.model.enums.VisibilityType;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class UserFootprintsFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, UserFootprint>
        implements UserFootprintReadableDataSourceContract {

    private static final int NUMBER_OF_USER_FOOTPRINTS = 100;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String EMOJI_WORLD = ":earth_africa:";
    private static final String EMOJI_SPAIN = "ES";
    private static final String EMOJI_MEXICO = "MX";
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, UserFootprint> items = new HashMap<>();
    private int primaryKey = 0;
    private MapUtils mapUtils = new MapUtils();

    @Inject
    public UserFootprintsFakeReadableDataSource() {
    }

    @Override
    public UserFootprint getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedGenericTotalCollection<UserFootprint> getUserFootprintsByUserId(String userKey, Integer numberPage, Page page) throws Exception {
        Collection<UserFootprint> userFootprints = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_USER_FOOTPRINTS; i++) {
            userFootprints.add(getUserFootprints());
            primaryKey++;
        }

        boolean hasMore = offset + userFootprints.size() < NUMBER_OF_USER_FOOTPRINTS;

        PaginatedCollection<UserFootprint> paginatedFootprints = new PaginatedCollection<>(userFootprints);
        paginatedFootprints.setPage(page);
        paginatedFootprints.setHasMore(hasMore);

        PaginatedGenericTotalCollection<UserFootprint> paginatedFootprintsFake = new PaginatedGenericTotalCollection<>(paginatedFootprints, NUMBER_OF_USER_FOOTPRINTS);

        return paginatedFootprintsFake;
    }

    private UserFootprint getUserFootprints() {
        UserFootprint[] allFootprints = {getUserFootprintImage1(), getUserFootprintImage2(), getUserFootprintImage3(),
                getUserFootprintImage4(), getUserFootprintImage5(), getUserFootprintImage6(), getUserFootprintImage7(),
                getUserFootprintImage8(), getUserFootprintImage9(), getUserFootprintImage10(), getUserFootprintImage11(),
                getUserFootprintImage12(), getUserFootprintImage13(), getUserFootprintImage14(), getUserFootprintImage15(),
                getUserFootprintImage16(), getUserFootprintImage17(), getUserFootprintImage18(), getUserFootprintImage19(),
                getUserFootprintImage20(), getUserFootprintImage21(), getUserFootprintImage22(), getUserFootprintImage23(),
                getUserFootprintImage24(), getUserFootprintImage25(), getUserFootprintImage26(), getUserFootprintImage27()};

        UserFootprint userFootprint = allFootprints[RANDOM.nextInt(allFootprints.length)];

        return userFootprint;
    }

    private UserFootprint getUserFootprintImage1() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-03T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/10/27/18/24/cat-3777201_1280.jpg")
                .withTitle(("My baby cat"))
                .withDescription("I love my cat")
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
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(100)
                .withTotalDislikes(20)
                .withTotalFootprintsDead(2)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage2() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-04T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2019/04/06/15/58/cat-4107662_1280.jpg")
                .withTitle("What are you looking at?")
                .withDescription("My cat is a piece of cake :P")
                .withComments(50)
                .withViews(543)
                .withLikes(0)
                .withDislikes(50)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(-50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(50)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Alcalá de Guadaira")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(200)
                .withTotalDislikes(120)
                .withTotalFootprintsDead(12)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage3() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-05T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Full Moon")
                .withDescription("Cat in the dark")
                .withMedia("https://cdn.pixabay.com/photo/2015/04/23/21/59/tree-736877_960_720.jpg")
                .withComments(50)
                .withViews(111)
                .withLikes(40)
                .withDislikes(40)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(0)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(80)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withTotalLikes(500)
                .withTotalDislikes(10)
                .withTotalFootprintsDead(22)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage4() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-06T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby turtle")
                .withDescription("Baby turtle arriving at the beach")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/04/14/24/turtle-2201433_960_720.jpg")
                .withComments(50)
                .withViews(713)
                .withLikes(75)
                .withDislikes(25)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("Chipiona")
                .withParentZoneName("Provincia de Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(1500)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(442)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage5() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-07T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Kingfisher")
                .withDescription("This bird is a machine!")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/28/14/41/bird-3113835_960_720.jpg")
                .withComments(50)
                .withViews(533)
                .withLikes(25)
                .withDislikes(75)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(-50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withTotalLikes(600)
                .withTotalDislikes(330)
                .withTotalFootprintsDead(32)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage6() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-22T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Piranha")
                .withDescription("Dangerous fish")
                .withMedia("https://cdn.pixabay.com/photo/2018/11/02/23/32/piranha-3791257_960_720.jpg")
                .withComments(50)
                .withViews(41)
                .withLikes(133)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(131)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(135)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Provincia de Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(160)
                .withTotalDislikes(260)
                .withTotalFootprintsDead(23)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage7() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-02-08T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Strong coffee")
                .withDescription("This coffee can be dangerous")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/04/18/39/coffee-2714970_960_720.jpg")
                .withComments(50)
                .withViews(144)
                .withLikes(72)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(70)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(74)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withTotalLikes(440)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(72)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage8() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Healthy Menu")
                .withDescription("Healthy menu every morning")
                .withMedia("https://cdn.pixabay.com/photo/2015/01/09/15/34/chunks-594496_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("Triana")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage9() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Cheese with honey")
                .withDescription("I love this combination")
                .withMedia("https://cdn.pixabay.com/photo/2016/12/06/18/27/cheese-1887233_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Grazalema")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage10() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-05-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artisan bread")
                .withDescription("I love artisan food")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/14/10/01/bread-2142453_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Lepe")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage11() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Perfect eyes")
                .withDescription("Wonderful eyes")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/06/09/25/hijab-3064633_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Ayamonte")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage12() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Daily Scrum Meeting")
                .withDescription("This is my daily meetings")
                .withMedia("https://cdn.pixabay.com/photo/2016/03/09/09/22/workplace-1245776_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage13() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Couple African Happy")
                .withDescription("The love is special")
                .withMedia("https://cdn.pixabay.com/photo/2015/11/07/11/01/couple-1030744_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage14() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artist")
                .withDescription("Artist in the street")
                .withMedia("https://cdn.pixabay.com/photo/2014/05/13/22/40/man-343674_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("New York")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage15() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Father and son")
                .withDescription("Father and son walking")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/21/01/04/father-2770301_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.DEAD.getValue())
                .withZoneName("Cazorla")
                .withParentZoneName("Jaén")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage16() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Small castle")
                .withDescription("Small castle")
                .withMedia("https://cdn.pixabay.com/photo/2013/10/13/15/27/castle-195105_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Segovia")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage17() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Mysterious castle")
                .withDescription("I will never enter there!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/30/02/28/castle-2695680_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage18() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Bridge Romance Fantasy")
                .withDescription("Beautiful place :)")
                .withMedia("https://cdn.pixabay.com/photo/2017/10/25/09/13/bridge-2887353_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Ourense")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage19() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Lost places")
                .withDescription("Misterious places")
                .withMedia("https://cdn.pixabay.com/photo/2019/02/25/11/08/lost-places-4019367_960_720.jpg")
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
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage20() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Gnome Troll")
                .withDescription("Very big gnome troll in the forest")
                .withMedia("https://cdn.pixabay.com/photo/2015/05/26/21/32/control-785555_960_720.jpg")
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
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage21() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Life is good")
                .withDescription("Sure!")
                .withMedia("https://cdn.pixabay.com/photo/2015/12/20/15/11/motivation-1101322_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Dos Hermanas")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage22() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("My gadgets")
                .withDescription("I love my gadgets!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/07/23/29/still-2609154_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Utrera")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage23() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("It is a airplane?")
                .withDescription("Maybe")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/05/14/26/airplane-2583982_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Reykjavik")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage24() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("You can!")
                .withDescription("Yes you can my friend!")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/06/00/37/motivation-2120322_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Madrid")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage25() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Friendship")
                .withDescription("The most important!")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/18/04/25/phone-2237666_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Cabo de Gata")
                .withParentZoneName("Almería")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage26() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby hand")
                .withDescription("Forever young")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/26/18/40/baby-3109433_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private UserFootprint getUserFootprintImage27() {

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-01-09T14:25:23.000+0200")
                .build();

        final UserFootprint userFootprintImage =  new UserFootprintBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Happy selfie")
                .withDescription("Friends")
                .withMedia("https://cdn.pixabay.com/photo/2016/11/14/04/08/selfie-1822563_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withStatus(FootprintStatus.NORMAL.getValue())
                .withZoneName("Beijing")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withTotalLikes(700)
                .withTotalDislikes(220)
                .withTotalFootprintsDead(52)
                .withRaddarLocation(raddarLocation)
                .build();

        items.put(String.valueOf(primaryKey), userFootprintImage);

        return userFootprintImage;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
