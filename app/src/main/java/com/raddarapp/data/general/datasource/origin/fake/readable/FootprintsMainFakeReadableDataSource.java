package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedFootprintsMainTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsMainReadableDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.enums.AspectRatioType;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintLevel;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.FootprintMainBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.builder.UserBuilder;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.SponsoredType;
import com.raddarapp.domain.model.enums.UserRelationshipType;
import com.raddarapp.domain.model.enums.VisibilityType;
import com.raddarapp.domain.model.enums.VotedType;
import com.raddarapp.domain.model.util.GenerateUsers;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

public class FootprintsMainFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, FootprintMain>
        implements FootprintsMainReadableDataSourceContract {

    private static final int NUMBER_OF_FOOTPRINTS_MAIN = 41;
    private static final long FAKE_DELAY_MILLISECONDS = 2000;
    private static final String EMOJI_WORLD = ":earth_africa:";
    private static final String EMOJI_SPAIN = "ES";
    private static final String EMOJI_MEXICO = "MX";
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, FootprintMain> items = new HashMap<>();
    private int primaryKey = 0;
    private MapUtils mapUtils = new MapUtils();
    private GenerateUsers generateUsers = new GenerateUsers();

    @Inject
    MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public FootprintsMainFakeReadableDataSource(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    @Override
    public FootprintMain getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedFootprintsMainTotalCollection<FootprintMain> getFootprintsMain(RaddarLocation raddarLocation, Integer pageNumber, Page page) throws Exception {
        Collection<FootprintMain> footprintsMain = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_FOOTPRINTS_MAIN; i++) {
            footprintsMain.add(getFootprintMain());
            primaryKey++;
        }

        boolean hasMore = offset + footprintsMain.size() < NUMBER_OF_FOOTPRINTS_MAIN;

        PaginatedCollection<FootprintMain> paginatedFootprints = new PaginatedCollection<>(footprintsMain);
        paginatedFootprints.setPage(page);
        paginatedFootprints.setHasMore(hasMore);

        PaginatedFootprintsMainTotalCollection<FootprintMain> paginatedFootprintsFake = new PaginatedFootprintsMainTotalCollection<>(
                paginatedFootprints, getUserProfile(), NUMBER_OF_FOOTPRINTS_MAIN);

        return paginatedFootprintsFake;
    }

    @Override
    public PaginatedCollection<FootprintMain> getFootprintsMainByUserId(String userKey, Page page) throws Exception {
        Collection<FootprintMain> footprintsMain = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_FOOTPRINTS_MAIN; i++) {
            if(userKey.equals(primaryKey)) {
                footprintsMain.add(getFootprintMain());
                break;
            }
            primaryKey++;
        }

        PaginatedCollection<FootprintMain> paginatedFootprints = new PaginatedCollection<>(footprintsMain);
        paginatedFootprints.setPage(page);
        paginatedFootprints.setHasMore(offset + footprintsMain.size() < NUMBER_OF_FOOTPRINTS_MAIN);

        return paginatedFootprints;
    }

    private FootprintMain getFootprintMain() {
        FootprintMain[] allFootprints = {getFootprintMainImage1(), getFootprintMainImage2(), getFootprintMainImage3(),
                getFootprintMainImage4(), getFootprintMainImage5(), getFootprintMainImage6(), getFootprintMainImage7(),
                getFootprintMainImage8(), getFootprintMainImage9(), getFootprintMainImage10(), getFootprintMainImage11(),
                getFootprintMainImage12(), getFootprintMainImage13(), getFootprintMainImage14(), getFootprintMainImage15(),
                getFootprintMainImage16(), getFootprintMainImage17(), getFootprintMainImage18(), getFootprintMainImage19(),
                getFootprintMainImage20(), getFootprintMainImage21(), getFootprintMainImage22(), getFootprintMainImage23(),
                getFootprintMainImage24(), getFootprintMainImage25(), getFootprintMainImage26(), getFootprintMainImage27()};

        FootprintMain footprintMain = allFootprints[RANDOM.nextInt(allFootprints.length)];

        return footprintMain;
    }

    private FootprintMain getFootprintMainImage1() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-21T20:21:53.000+0200")
                .build();

        final FootprintMain footprintMain =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/10/27/18/24/cat-3777201_1280.jpg")
                .withTitle(("My baby cat"))
                .withDescription("I love my cat")
                .withComments(50)
                .withViews(443)
                .withLikes(100)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
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
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMain);

        return footprintMain;
    }

    private FootprintMain getFootprintMainImage2() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-20T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2019/04/06/15/58/cat-4107662_1280.jpg")
                .withTitle("What are you looking at?")
                .withDescription("My cat is a piece of cake :P")
                .withComments(50)
                .withViews(543)
                .withLikes(0)
                .withDislikes(50)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(-50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(50)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Alcalá de Guadaira")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage3() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Full Moon")
                .withDescription("Cat in the dark")
                .withMedia("https://cdn.pixabay.com/photo/2015/04/23/21/59/tree-736877_960_720.jpg")
                .withComments(50)
                .withViews(111)
                .withLikes(40)
                .withDislikes(40)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(0)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(80)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage4() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-20T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby turtle")
                .withDescription("Baby turtle arriving at the beach")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/04/14/24/turtle-2201433_960_720.jpg")
                .withComments(50)
                .withViews(713)
                .withLikes(75)
                .withDislikes(25)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Chipiona")
                .withParentZoneName("Provincia de Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage5() {

        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Kingfisher")
                .withDescription("This bird is a machine!")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/28/14/41/bird-3113835_960_720.jpg")
                .withComments(50)
                .withViews(533)
                .withLikes(25)
                .withDislikes(75)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(-50)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(100)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage6() {

        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-22T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Piranha")
                .withDescription("Dangerous fish")
                .withMedia("https://cdn.pixabay.com/photo/2018/11/02/23/32/piranha-3791257_960_720.jpg")
                .withComments(50)
                .withViews(41)
                .withLikes(133)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(131)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(135)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Provincia de Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage7() {

        final User user = generateUsers.generateUser7(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-02-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Strong coffee")
                .withDescription("This coffee can be dangerous")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/04/18/39/coffee-2714970_960_720.jpg")
                .withComments(50)
                .withViews(144)
                .withLikes(72)
                .withDislikes(2)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(70)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(74)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage8() {

        final User user = generateUsers.generateUser8(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Healthy Menu")
                .withDescription("Healthy menu every morning")
                .withMedia("https://cdn.pixabay.com/photo/2015/01/09/15/34/chunks-594496_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Triana")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage9() {

        final User user = generateUsers.generateUser9(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Cheese with honey")
                .withDescription("I love this combination")
                .withMedia("https://cdn.pixabay.com/photo/2016/12/06/18/27/cheese-1887233_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Grazalema")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage10() {

        final User user = generateUsers.generateUser10(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artisan bread")
                .withDescription("I love artisan food")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/14/10/01/bread-2142453_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Lepe")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage11() {

        final User user = generateUsers.generateUser11(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Perfect eyes")
                .withDescription("Wonderful eyes")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/06/09/25/hijab-3064633_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Ayamonte")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage12() {

        final User user = generateUsers.generateUser12(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Daily Scrum Meeting")
                .withDescription("This is my daily meetings")
                .withMedia("https://cdn.pixabay.com/photo/2016/03/09/09/22/workplace-1245776_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage13() {

        final User user = generateUsers.generateUser13(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Couple African Happy")
                .withDescription("The love is special")
                .withMedia("https://cdn.pixabay.com/photo/2015/11/07/11/01/couple-1030744_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage14() {

        final User user = generateUsers.generateUser14(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artist")
                .withDescription("Artist in the street")
                .withMedia("https://cdn.pixabay.com/photo/2014/05/13/22/40/man-343674_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("New York")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage15() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Father and son")
                .withDescription("Father and son walking")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/21/01/04/father-2770301_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cazorla")
                .withParentZoneName("Jaén")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage16() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Small castle")
                .withDescription("Small castle")
                .withMedia("https://cdn.pixabay.com/photo/2013/10/13/15/27/castle-195105_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Segovia")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage17() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Mysterious castle")
                .withDescription("I will never enter there!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/30/02/28/castle-2695680_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage18() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Bridge Romance Fantasy")
                .withDescription("Beautiful place :)")
                .withMedia("https://cdn.pixabay.com/photo/2017/10/25/09/13/bridge-2887353_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Ourense")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage19() {

        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Lost places")
                .withDescription("Misterious places")
                .withMedia("https://cdn.pixabay.com/photo/2019/02/25/11/08/lost-places-4019367_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage20() {

        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Gnome Troll")
                .withDescription("Very big gnome troll in the forest")
                .withMedia("https://cdn.pixabay.com/photo/2015/05/26/21/32/control-785555_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage21() {

        final User user = generateUsers.generateUser7(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Life is good")
                .withDescription("Sure!")
                .withMedia("https://cdn.pixabay.com/photo/2015/12/20/15/11/motivation-1101322_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Dos Hermanas")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage22() {

        final User user = generateUsers.generateUser8(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("My gadgets")
                .withDescription("I love my gadgets!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/07/23/29/still-2609154_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Utrera")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage23() {

        final User user = generateUsers.generateUser9(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("It is a airplane?")
                .withDescription("Maybe")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/05/14/26/airplane-2583982_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Reykjavik")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage24() {

        final User user = generateUsers.generateUser10(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("You can!")
                .withDescription("Yes you can my friend!")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/06/00/37/motivation-2120322_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Madrid")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage25() {

        final User user = generateUsers.generateUser11(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Friendship")
                .withDescription("The most important!")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/18/04/25/phone-2237666_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cabo de Gata")
                .withParentZoneName("Almería")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage26() {

        final User user = generateUsers.generateUser12(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby hand")
                .withDescription("Forever young")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/26/18/40/baby-3109433_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private FootprintMain getFootprintMainImage27() {

        final User user = generateUsers.generateUser13(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintMain footprintMainImage =  new FootprintMainBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Happy selfie")
                .withDescription("Friends")
                .withMedia("https://cdn.pixabay.com/photo/2016/11/14/04/08/selfie-1822563_960_720.jpg")
                .withComments(50)
                .withViews(211)
                .withLikes(99)
                .withDislikes(21)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(78)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(120)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Beijing")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintMainImage);

        return footprintMainImage;
    }

    private MyUserProfile getUserProfile() {
        return userProfilePreferencesDataSource.getUserProfilePreferences();
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
