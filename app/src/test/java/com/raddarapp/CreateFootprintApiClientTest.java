package com.raddarapp;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CreateFootprintApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.CreateFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RaddarLocationDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.CreateFootprintDtoBuilder;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.RaddarLocationDtoBuilder;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.VisibilityType;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateFootprintApiClientTest extends MockWebServerTest {

    private static final String TOKEN = "fakeToken";
    private CreateFootprintApiClient createFootprintApiClient;
    private RaddarLocationDto raddarLocationDto;
    private CreateFootprintDto createFootprintDto;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createFootprintApiClient = new CreateFootprintApiClient(ServerApiConfig.withTestToken(TOKEN, getBaseEndpoint()));
        raddarLocationDto = new RaddarLocationDtoBuilder()
                .withLatitude(37.38638)
                .withLongitude(-5.9)
                .withCreationMoment("2017-05-02T14:25:23.000+0000")
                .build();

        createFootprintDto = new CreateFootprintDtoBuilder()
                .withMediaName("http://static.wixstatic.com/media/c65fd6_6a73f934bd584a05987b7acf6eb1b128.jpg")
                .withDescription("Visita de amigos por el cumpleaños de James. Gracias a tod@s por venir :)")
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withRaddarLocationDto(raddarLocationDto)
                .build();
    }

    @Test
    public void send_authorization_token_header() throws Exception {
        enqueueMockResponseEmptyBody();

        createFootprintApiClient.createFootprint(createFootprintDto, null);

        assertRequestContainsHeader("Authorization", "Bearer " + TOKEN);
    }

    @Test
    public void send_post_create_footprint_to_the_correct_endpoint() throws Exception {
        enqueueMockResponseEmptyBody();

        createFootprintApiClient.createFootprint(createFootprintDto, null);

        assertPostRequestSentTo("/footprints");
    }

    @Test
    public void parse_create_footprint_properly() throws Exception {
        enqueueMockResponse(200, "createFootprint.json");

        CreateFootprintDto createdFootprintDto =
                createFootprintApiClient.createFootprint(createFootprintDto, null);

        assertCreateFootprintContainsExpectedValues(createdFootprintDto);
    }

    private void assertCreateFootprintContainsExpectedValues(CreateFootprintDto createFootprintDto) {
        assertEquals(createFootprintDto.getId(), "590f65ea0072b114bef9db00");
        assertEquals(createFootprintDto.getDescription(), "Visita de amigos por el cumpleaños de James. Gracias a tod@s por venir :)");
        assertEquals(createFootprintDto.getMediaName(), "http://static.wixstatic.com/media/c65fd6_6a73f934bd584a05987b7acf6eb1b128.jpg");
        assertEquals(createFootprintDto.getMediaType(), FootprintMediaType.IMAGE.getValue());
        assertEquals(createFootprintDto.getVisibility(), VisibilityType.PUBLIC.getValue());
        assertEquals(createFootprintDto.getRaddarLocationDto().getLatitude(), 37.38638, 0);
        assertEquals(createFootprintDto.getRaddarLocationDto().getLongitude(), -5.9, 0);
        assertEquals(createFootprintDto.getRaddarLocationDto().getCreationMoment(), "2017-05-02T14:25:23.000+0000");
    }
}
