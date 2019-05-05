package com.raddarapp;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UserApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.FollowDto;
import com.raddarapp.domain.model.enums.UserRelationshipType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserApiClientTest extends MockWebServerTest {

    private static final String TOKEN = "fakeToken";
    private static final String ID_FOLLOW_USER = "590f65ea7777b114bef9db00";
    private static final String ID_UNFOLLOW_USER = "590f65ea3333b114bef9db00";
    private static final String ID_FOOTPRINT = "590f65ea3333b114bef9db99";
    private UserApiClient userApiClient;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        userApiClient = new UserApiClient(ServerApiConfig.withTestToken(TOKEN, getBaseEndpoint()));
    }

    @Test
    public void send_authorization_token_header_follow_user() throws Exception {
        enqueueMockResponseEmptyBody();

        userApiClient.followUser(ID_FOLLOW_USER);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_authorization_token_header_unfollow_user() throws Exception {
        enqueueMockResponseEmptyBody();

        userApiClient.unfollowUser(ID_UNFOLLOW_USER);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_post_follow_user_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        userApiClient.followUser(ID_FOLLOW_USER);

        assertPostRequestSentTo("/users/follow/" + ID_FOLLOW_USER);
    }

    @Test
    public void send_post_unfollow_user_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        userApiClient.unfollowUser(ID_UNFOLLOW_USER);

        assertPostRequestSentTo("/users/unfollow/" + ID_UNFOLLOW_USER);
    }

    @Test
    public void return_type_follow_user_properly() throws Exception {
        enqueueMockResponse(200, "followUser.json");

        FollowDto followDto = userApiClient.followUser(ID_FOLLOW_USER);

        assertEquals(followDto.getUserRelationship(), UserRelationshipType.FOLLOWING.getValue());
    }

    @Test
    public void return_type_unfollow_user_properly() throws Exception {
        enqueueMockResponse(200, "unfollowUser.json");

        FollowDto followDto = userApiClient.unfollowUser(ID_UNFOLLOW_USER);

        assertEquals(followDto.getUserRelationship(), UserRelationshipType.UNKNOWN.getValue());
    }
}
