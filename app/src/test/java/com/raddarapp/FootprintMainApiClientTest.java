package com.raddarapp;

import static org.junit.Assert.assertEquals;

public class FootprintMainApiClientTest extends MockWebServerTest {

    /*private static int PAGE = 0;
    private static int PAGE_PER = 5;
    private static final String TOKEN = "fakeToken";
    private static final Long USER_RANGE = 500l;
    private FootprintMainApiClient footprintMainApiClient;
    private RaddarLocationDto raddarLocationDto;
    private static final String FOOTPRINT_ID = "fakeFootprintId";
    private static final int ADD_VOTE_ADD_LIKE = 0;
    private static final int ADD_VOTE_QUIT_LIKE = 1;
    private static final int ADD_VOTE_ADD_DISLIKE = 2;
    private static final int ADD_VOTE_QUIT_DISLIKE = 3;
    private static final int ADD_VOTE_ADD_LIKE_QUIT_DISLIKE = 4;
    private static final int ADD_VOTE_ADD_DISLIKE_QUIT_LIKE = 5;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        footprintMainApiClient = new FootprintMainApiClient(ServerApiConfig.withTestToken(TOKEN, getBaseEndpoint()));
        raddarLocationDto = new RaddarLocationDtoBuilder()
                .withLatitude(37.38638)
                .withLongitude(-5.9)
                .withCreationMoment("2017-05-02T14:25:23.000+0000")
                .build();
    }

    @Test
    public void send_authorization_token_header() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.getFootprintsMain(raddarLocationDto, PAGE, PAGE_PER);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_authorization_token_header_add_all_votes() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_LIKE);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_authorization_token_header_delete_footprint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.deleteFootprint(FOOTPRINT_ID);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_post_footprints_main_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.getFootprintsMain(raddarLocationDto, PAGE, PAGE_PER);

        assertPostRequestSentTo("/footprints/near?page=" + PAGE + "&page_per=" + PAGE_PER);
    }

    @Test
    public void send_post_add_vote_add_like_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_LIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_ADD_LIKE);
    }

    @Test
    public void send_post_add_vote_quit_like_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_QUIT_LIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_QUIT_LIKE);
    }

    @Test
    public void send_post_add_vote_add_dislike_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_DISLIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_ADD_DISLIKE);
    }

    @Test
    public void send_post_add_vote_quit_dislike_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_QUIT_DISLIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_QUIT_DISLIKE);
    }

    @Test
    public void send_post_add_vote_add_like_quit_dislike_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_LIKE_QUIT_DISLIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_ADD_LIKE_QUIT_DISLIKE);
    }

    @Test
    public void send_post_add_vote_add_dislike_quit_dislike_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_DISLIKE_QUIT_LIKE);

        assertPostRequestSentTo("/footprints/" + FOOTPRINT_ID + "/vote/" + ADD_VOTE_ADD_DISLIKE_QUIT_LIKE);
    }

    @Test
    public void send_delete_footprint_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        footprintMainApiClient.deleteFootprint(FOOTPRINT_ID);

        assertDeleteRequestSentTo("/footprints/" + FOOTPRINT_ID + "/delete");
    }

    @Test
    public void parse_propertly_getting_an_empty_footprint_main_collection() throws Exception {
        enqueueMockResponse(200, "emptyFootprintsResponse.json");

        FootprintServerResponseCollection<FootprintMainDto> footprintsMain =
                footprintMainApiClient.getFootprintsMain(raddarLocationDto, PAGE, PAGE_PER);

        assertEquals(0, footprintsMain.getFootprints().getResponse().size());
        assertEquals(USER_RANGE, footprintsMain.getRaddarRangeUser());
        assertEquals(5, footprintsMain.getFootprints().getMetadata().getElementsPerPage());
        assertEquals(1, footprintsMain.getFootprints().getMetadata().getPageNumber());
        assertEquals(0, footprintsMain.getFootprints().getMetadata().getTotalPages());
    }

    @Test
    public void parse_footprints_main_properly_getting_all_the_footprints_main() throws Exception {
        enqueueMockResponse(200, "getFootprintsMain.json");

        FootprintServerResponseCollection<FootprintMainDto> footprintsMain =
                footprintMainApiClient.getFootprintsMain(raddarLocationDto, PAGE, PAGE_PER);

        assertEquals(footprintsMain.getFootprints().getResponse().size(), PAGE_PER);
        assertFootprintMainContainsExpectedValues((FootprintMainDto) footprintsMain.getFootprints().getResponse().get(0));
        assertEquals(USER_RANGE, footprintsMain.getRaddarRangeUser());
        assertEquals(5, footprintsMain.getFootprints().getMetadata().getElementsPerPage());
        assertEquals(1, footprintsMain.getFootprints().getMetadata().getPageNumber());
        assertEquals(200001, footprintsMain.getFootprints().getMetadata().getTotalPages());
    }

    @Test
    public void return_voted_like_add_vote_add_like_properly() throws Exception {
        enqueueMockResponse(200, "addVoteLike.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_LIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_DISLIKE.getValue());
    }

    @Test
    public void return_no_voted_add_vote_quit_like_properly() throws Exception {
        enqueueMockResponse(200, "addVoteNoVoted.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_QUIT_LIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_LIKE.getValue());
    }

    @Test
    public void return_voted_dislike_add_vote_add_dislike_properly() throws Exception {
        enqueueMockResponse(200, "addVoteDislike.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_DISLIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_SAVE_COLLECTION_LIKE.getValue());
    }

    @Test
    public void return_no_voted_add_vote_quit_dislike_properly() throws Exception {
        enqueueMockResponse(200, "addVoteNoVoted.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_QUIT_DISLIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_LIKE.getValue());
    }

    @Test
    public void return_voted_like_add_vote_add_like_quit_dislike_properly() throws Exception {
        enqueueMockResponse(200, "addVoteLike.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_LIKE_QUIT_DISLIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_DISLIKE.getValue());
    }

    @Test
    public void return_voted_dislike_add_vote_add_dislike_quit_like_properly() throws Exception {
        enqueueMockResponse(200, "addVoteDislike.json");

        AddVoteDto addVoteDto = footprintMainApiClient.addVote(FOOTPRINT_ID, ADD_VOTE_ADD_DISLIKE_QUIT_LIKE);

        assertEquals(addVoteDto.getVotedType(), VotedType.VOTE_SAVE_COLLECTION_LIKE.getValue());
    }

    @Test
    public void return_deleted_properly_sending_delete_footprint() throws Exception {
        enqueueMockResponse(200, "deleteFootprint.json");

        DeleteFootprintDto deletedFootprint = footprintMainApiClient.deleteFootprint(FOOTPRINT_ID);

        assertEquals(deletedFootprint.isDeletedFootprint(), true);
    }

    private void assertFootprintMainContainsExpectedValues(FootprintMainDto footprintMainDto) {
        assertEquals(footprintMainDto.getId(), "590f65ea0072be24bef9db58");
        assertEquals(footprintMainDto.getDescription(), "ukboyanrlo");
        assertEquals(footprintMainDto.getMedia(), "http://webneel.com/wallpaper/sites/default/files/images/04-2013/dreamy-beach-wallpaper.preview.jpg");
        assertEquals(footprintMainDto.getMediaType(), FootprintType.IMAGE.getValue());
        assertEquals(footprintMainDto.getVisibility(), VisibilityType.PUBLIC.getValue());
        assertEquals(footprintMainDto.getSponsored(), false);
        assertEquals(footprintMainDto.getComments(), 0);
        assertEquals(footprintMainDto.getLikes(), 0);
        assertEquals(footprintMainDto.getDislikes(), 0);
        assertEquals(footprintMainDto.getViews(), 0);
        assertEquals(footprintMainDto.getVoted(), VotedType.VOTE_LIKE.getValue());
        assertEquals(footprintMainDto.getRaddarLocationDto().getLatitude(), 37.38655400310323, 0);
        assertEquals(footprintMainDto.getRaddarLocationDto().getLongitude(), -5.984097769682285, 0);
        assertEquals(footprintMainDto.getRaddarLocationDto().getCreationMoment(), "2017-05-02T14:25:23.000+0000");
        assertEquals(footprintMainDto.getUser().getUsername(), "GM9FV94T");
        assertEquals(footprintMainDto.getUser().getImage(), "https://s-media-cache-ak0.pinimg.com/736x/b4/7c/08/b47c08ca4a47327861d2480b133baefc.jpg");
        assertEquals(footprintMainDto.getUser().getRange(), 100.0, 0);
        assertEquals(footprintMainDto.getUser().getFollowers(), 0);
        assertEquals(footprintMainDto.getUser().getUserRelationship(), 0);
    }*/
}
