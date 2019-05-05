package com.raddarapp;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CommentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic.ServerResponseCollection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommentApiClientTest extends MockWebServerTest {

    /*private static int PAGE = 0;
    private static int PAGE_PER = 5;
    private static final String TOKEN = "fakeToken";
    private static final String FOOTPRINT_ID = "fakeFootprintId";
    private CommentApiClient commentApiClient;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        commentApiClient = new CommentApiClient(ServerApiConfig.withTestToken(TOKEN, getBaseEndpoint()));
    }

    @Test
    public void send_authorization_token_header() throws Exception {
        enqueueMockResponseEmptyBody();

        commentApiClient.getCommentsByFootprintId(FOOTPRINT_ID, PAGE, PAGE_PER);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_get_comments_by_footprints_id_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        commentApiClient.getCommentsByFootprintId(FOOTPRINT_ID, PAGE, PAGE_PER);

        assertGetRequestSentTo("/footprints/" + FOOTPRINT_ID + "/comment?page=" + PAGE + "&page_per=" + PAGE_PER);
    }

    @Test
    public void parse_propertly_getting_an_empty_comment_collection() throws Exception {
        enqueueMockResponse(200, "emptyCommentsResponse.json");

        ServerResponseCollection<CommentDto> commentsByFootprintId =
                commentApiClient.getCommentsByFootprintId(FOOTPRINT_ID, PAGE, PAGE_PER);

        assertEquals(0, commentsByFootprintId.getResponse().size());
        assertEquals(5, commentsByFootprintId.getMetadata().getElementsPerPage());
        assertEquals(1, commentsByFootprintId.getMetadata().getPageNumber());
        assertEquals(0, commentsByFootprintId.getMetadata().getTotalPages());
    }

    @Test
    public void parse_comments_by_footprint_id_properly_getting_all_the_comments_by_footprint_id() throws Exception {
        enqueueMockResponse(200, "getCommentsByFootprintId.json");

        ServerResponseCollection<CommentDto> commentsByFootprintId =
                commentApiClient.getCommentsByFootprintId(FOOTPRINT_ID, PAGE, PAGE_PER);

        assertEquals(commentsByFootprintId.getResponse().size(), 3);
        assertCommentsByFootprintIdContainsExpectedValues(commentsByFootprintId.getResponse().get(0));
        assertEquals(5, commentsByFootprintId.getMetadata().getElementsPerPage());
        assertEquals(1, commentsByFootprintId.getMetadata().getPageNumber());
        assertEquals(1, commentsByFootprintId.getMetadata().getTotalPages());
    }

    private void assertCommentsByFootprintIdContainsExpectedValues(CommentDto commentDto) {
        assertEquals(commentDto.getId(), "590f65ea0072b114bef9db58");
        assertEquals(commentDto.getBody(), "Hello");
        assertEquals(commentDto.getCreationMoment(), "2017-05-02T14:25:23.000+0000");
        assertEquals(commentDto.getOwnerDto().getId(), "590f65ea0072b1149999db58");
        assertEquals(commentDto.getOwnerDto().getName(), "james");
        assertEquals(commentDto.getOwnerDto().getSurname(), "surjames");
        assertEquals(commentDto.getOwnerDto().getUsername(), "j4m3s");
        assertEquals(commentDto.getOwnerDto().getPhoto(), "https://s-media-cache-ak0.pinimg.com/736x/b4/7c/08/b47c08ca4a47327861d2480b133baefc.jpg");
    }*/
}
