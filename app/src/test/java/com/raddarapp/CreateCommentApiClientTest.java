package com.raddarapp;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CreateCommentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateCommentDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.CreateCommentDtoBuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateCommentApiClientTest extends MockWebServerTest {

    private static final String TOKEN = "fakeToken";
    private CreateCommentApiClient createCommentApiClient;
    private CreateCommentDto createCommentDto;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createCommentApiClient = new CreateCommentApiClient(ServerApiConfig.withTestToken(TOKEN, getBaseEndpoint()));

        createCommentDto = new CreateCommentDtoBuilder()
                .withId("590f65ea0072b114bef9db00")
                .withBody("Que bien lo pasamos en tu cumpleaños James, fue una pasada")
                .withCreationMoment("2017-05-02T14:25:23.000+0000")
                .build();
    }

    @Test
    public void send_authorization_token_header() throws Exception {
        enqueueMockResponseEmptyBody();

        //createCommentApiClient.createComment(createCommentDto);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_post_create_comment_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        //createCommentApiClient.createComment(createCommentDto);

        assertPostRequestSentTo("/comments");
    }

    @Test
    public void parse_create_comment_properly() throws Exception {
        enqueueMockResponse(200, "createComment.json");

        //CreateCommentDto addedCommentDto = createCommentApiClient.createComment(createCommentDto);

        //assertCreateCommentContainsExpectedValues(addedCommentDto);
    }

    private void assertCreateCommentContainsExpectedValues(CreateCommentDto createCommentDto) {
        assertEquals(createCommentDto.getId(), "590f65ea0072b114bef9db00");
        assertEquals(createCommentDto.getBody(), "Que bien lo pasamos en tu cumpleaños James, fue una pasada");
        assertEquals(createCommentDto.getCreationMoment(), "2017-05-02T14:25:23.000+0000");
    }
}
