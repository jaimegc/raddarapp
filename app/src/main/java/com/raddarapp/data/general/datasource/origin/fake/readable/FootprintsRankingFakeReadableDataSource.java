package com.raddarapp.data.general.datasource.origin.fake.readable;


import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.raddarapp.data.general.datasource.base.contract.base.PaginatedGenericTotalCollection;
import com.raddarapp.data.general.datasource.base.contract.readable.FootprintsRankingReadableDataSourceContract;
import com.raddarapp.domain.model.FootprintRanking;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.User;
import com.raddarapp.domain.model.builder.FootprintRankingBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.AspectRatioType;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintLevel;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.SponsoredType;
import com.raddarapp.domain.model.enums.VisibilityType;
import com.raddarapp.domain.model.enums.VotedType;
import com.raddarapp.domain.model.util.GenerateUsers;
import com.raddarapp.presentation.android.utils.MapUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class FootprintsRankingFakeReadableDataSource extends EmptyPaginatedReadableDataSource<String, FootprintRanking>
        implements FootprintsRankingReadableDataSourceContract {

    private static final int NUMBER_OF_MY_FOOTPRINTS_COLLECTION = 100;
    private static final int MAX_SCOPE_OF_MY_FOOTPRINTS_COLLECTION = 999;
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String EMOJI_WORLD = ":earth_africa:";
    private static final String EMOJI_SPAIN = "ES";
    private static final String EMOJI_MEXICO = "MX";
    private static final Random RANDOM = new Random(System.nanoTime());
    private Map<String, FootprintRanking> items = new HashMap<>();
    private int primaryKey = 0, totalScope = MAX_SCOPE_OF_MY_FOOTPRINTS_COLLECTION;
    private MapUtils mapUtils = new MapUtils();
    private GenerateUsers generateUsers = new GenerateUsers();

    @Override
    public FootprintRanking getByKey(String key) {
        fakeDelay();

        return items.get(key);
    }

    @Override
    public PaginatedGenericTotalCollection<FootprintRanking> getFootprintsRankingByZoneKey(String zoneKey, Integer pageNumber, Page page) throws Exception {
        Collection<FootprintRanking> userFootprints = new LinkedList<>();

        fakeDelay();

        int offset = page.getOffset();
        int limit = page.getLimit();

        primaryKey = offset * limit;

        for (int i = offset; i - offset < limit && i < NUMBER_OF_MY_FOOTPRINTS_COLLECTION; i++) {
            userFootprints.add(getFootprintRanking());
            primaryKey++;
            totalScope -= 5;
        }

        boolean hasMore = offset + userFootprints.size() < NUMBER_OF_MY_FOOTPRINTS_COLLECTION;

        PaginatedCollection<FootprintRanking> paginatedFootprints = new PaginatedCollection<>(userFootprints);
        paginatedFootprints.setPage(page);
        paginatedFootprints.setHasMore(hasMore);

        PaginatedGenericTotalCollection<FootprintRanking> paginatedFootprintsFake = new PaginatedGenericTotalCollection<>(paginatedFootprints, NUMBER_OF_MY_FOOTPRINTS_COLLECTION);

        return paginatedFootprintsFake;
    }

    private FootprintRanking getFootprintRanking() {
        FootprintRanking[] allFootprints = {getFootprintRankingImage1(), getFootprintRankingImage2(), getFootprintRankingImage3(),
                getFootprintRankingImage4(), getFootprintRankingImage5(), getFootprintRankingImage6(), getFootprintRankingImage7(),
                getFootprintRankingImage8(), getFootprintRankingImage9(), getFootprintRankingImage10(), getFootprintRankingImage11(),
                getFootprintRankingImage12(), getFootprintRankingImage13(), getFootprintRankingImage14(), getFootprintRankingImage15(),
                getFootprintRankingImage16(), getFootprintRankingImage17(), getFootprintRankingImage18(), getFootprintRankingImage19(),
                getFootprintRankingImage20(), getFootprintRankingImage21(), getFootprintRankingImage22(), getFootprintRankingImage23(),
                getFootprintRankingImage24(), getFootprintRankingImage25(), getFootprintRankingImage26(), getFootprintRankingImage27()};

        FootprintRanking footprintRanking = allFootprints[RANDOM.nextInt(allFootprints.length)];

        return footprintRanking;
    }

    private FootprintRanking getFootprintRankingImage1() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2018/10/27/18/24/cat-3777201_1280.jpg")
                .withTitle(("My baby cat"))
                .withDescription("I love my cat")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage2() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withMedia("https://cdn.pixabay.com/photo/2019/04/06/15/58/cat-4107662_1280.jpg")
                .withTitle("What are you looking at?")
                .withDescription("My cat is a piece of cake :P")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Alcalá de Guadaira")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage3() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Full Moon")
                .withDescription("Cat in the dark")
                .withMedia("https://cdn.pixabay.com/photo/2015/04/23/21/59/tree-736877_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage4() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby turtle")
                .withDescription("Baby turtle arriving at the beach")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/04/14/24/turtle-2201433_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Chipiona")
                .withParentZoneName("Provincia de Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage5() {

        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-02-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Kingfisher")
                .withDescription("This bird is a machine!")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/28/14/41/bird-3113835_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("Cancun")
                .withParentZoneName("Quintana Roo")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage6() {

        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-06-22T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Piranha")
                .withDescription("Dangerous fish")
                .withMedia("https://cdn.pixabay.com/photo/2018/11/02/23/32/piranha-3791257_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_ANIMALS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Provincia de Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage7() {

        final User user = generateUsers.generateUser7(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-02-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Strong coffee")
                .withDescription("This coffee can be dangerous")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/04/18/39/coffee-2714970_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .withZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage8() {

        final User user = generateUsers.generateUser8(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Healthy Menu")
                .withDescription("Healthy menu every morning")
                .withMedia("https://cdn.pixabay.com/photo/2015/01/09/15/34/chunks-594496_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Triana")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage9() {

        final User user = generateUsers.generateUser9(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Cheese with honey")
                .withDescription("I love this combination")
                .withMedia("https://cdn.pixabay.com/photo/2016/12/06/18/27/cheese-1887233_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Grazalema")
                .withParentZoneName("Sevilla")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage10() {

        final User user = generateUsers.generateUser10(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artisan bread")
                .withDescription("I love artisan food")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/14/10/01/bread-2142453_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_GASTRONOMY.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Lepe")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage11() {

        final User user = generateUsers.generateUser11(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Perfect eyes")
                .withDescription("Wonderful eyes")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/06/09/25/hijab-3064633_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Ayamonte")
                .withParentZoneName("Huelva")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage12() {

        final User user = generateUsers.generateUser12(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Daily Scrum Meeting")
                .withDescription("This is my daily meetings")
                .withMedia("https://cdn.pixabay.com/photo/2016/03/09/09/22/workplace-1245776_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Montequinto")
                .withParentZoneName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage13() {

        final User user = generateUsers.generateUser13(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Couple African Happy")
                .withDescription("The love is special")
                .withMedia("https://cdn.pixabay.com/photo/2015/11/07/11/01/couple-1030744_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage14() {

        final User user = generateUsers.generateUser14(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Artist")
                .withDescription("Artist in the street")
                .withMedia("https://cdn.pixabay.com/photo/2014/05/13/22/40/man-343674_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("New York")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage15() {

        final User user = generateUsers.generateUser1(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Father and son")
                .withDescription("Father and son walking")
                .withMedia("https://cdn.pixabay.com/photo/2017/09/21/01/04/father-2770301_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PEOPLE.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Cazorla")
                .withParentZoneName("Jaén")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage16() {

        final User user = generateUsers.generateUser2(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Small castle")
                .withDescription("Small castle")
                .withMedia("https://cdn.pixabay.com/photo/2013/10/13/15/27/castle-195105_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Segovia")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage17() {

        final User user = generateUsers.generateUser3(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Mysterious castle")
                .withDescription("I will never enter there!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/30/02/28/castle-2695680_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Puebla de Sanabria")
                .withParentZoneName("Zamora")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage18() {

        final User user = generateUsers.generateUser4(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Bridge Romance Fantasy")
                .withDescription("Beautiful place :)")
                .withMedia("https://cdn.pixabay.com/photo/2017/10/25/09/13/bridge-2887353_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SIXTEEN_NINE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Ourense")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage19() {

        final User user = generateUsers.generateUser5(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2015-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Lost places")
                .withDescription("Misterious places")
                .withMedia("https://cdn.pixabay.com/photo/2019/02/25/11/08/lost-places-4019367_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage20() {

        final User user = generateUsers.generateUser6(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2014-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Gnome Troll")
                .withDescription("Very big gnome troll in the forest")
                .withMedia("https://cdn.pixabay.com/photo/2015/05/26/21/32/control-785555_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_PLACES.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Zacatecas")
                .withParentZoneName("Mexico")
                .withCountryEmoji(EMOJI_MEXICO)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage21() {

        final User user = generateUsers.generateUser7(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2013-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Life is good")
                .withDescription("Sure!")
                .withMedia("https://cdn.pixabay.com/photo/2015/12/20/15/11/motivation-1101322_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Dos Hermanas")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage22() {

        final User user = generateUsers.generateUser8(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2012-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("My gadgets")
                .withDescription("I love my gadgets!")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/07/23/29/still-2609154_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Utrera")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage23() {

        final User user = generateUsers.generateUser9(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2011-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("It is a airplane?")
                .withDescription("Maybe")
                .withMedia("https://cdn.pixabay.com/photo/2017/08/05/14/26/airplane-2583982_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Reykjavik")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage24() {

        final User user = generateUsers.generateUser10(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2019-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("You can!")
                .withDescription("Yes you can my friend!")
                .withMedia("https://cdn.pixabay.com/photo/2017/03/06/00/37/motivation-2120322_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FIVE_FOUR.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_THINGS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Madrid")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage25() {

        final User user = generateUsers.generateUser11(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2018-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Friendship")
                .withDescription("The most important!")
                .withMedia("https://cdn.pixabay.com/photo/2017/04/18/04/25/phone-2237666_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Cabo de Gata")
                .withParentZoneName("Almería")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage26() {

        final User user = generateUsers.generateUser12(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2017-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Baby hand")
                .withDescription("Forever young")
                .withMedia("https://cdn.pixabay.com/photo/2018/01/26/18/40/baby-3109433_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Barcelona")
                .withParentZoneName("Spain")
                .withCountryEmoji(EMOJI_SPAIN)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private FootprintRanking getFootprintRankingImage27() {

        final User user = generateUsers.generateUser13(String.valueOf(primaryKey));

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(mapUtils.generateLatitude())
                .withLongitude(mapUtils.generateLongitude())
                .withCreationMoment("2016-05-02T14:25:23.000+0200")
                .build();

        final FootprintRanking footprintRankingImage =  new FootprintRankingBuilder()
                .withKey(String.valueOf(primaryKey))
                .withTitle("Happy selfie")
                .withDescription("Friends")
                .withMedia("https://cdn.pixabay.com/photo/2016/11/14/04/08/selfie-1822563_960_720.jpg")
                .withComments(50)
                .withViews(totalScope)
                .withLikes(totalScope)
                .withDislikes(0)
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVoted(VotedType.VOTE_LIKE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.FOUR_FIVE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withScope(totalScope)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_MOMENTS.getValue())
                .withCaptures(totalScope)
                .withLevel(FootprintLevel.CONVENCIONAL.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(false)
                .withZoneName("Beijing")
                .withParentZoneName("World")
                .withCountryEmoji(EMOJI_WORLD)
                .withRaddarLocation(raddarLocation)
                .withUser(user)
                .build();

        items.put(String.valueOf(primaryKey), footprintRankingImage);

        return footprintRankingImage;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
