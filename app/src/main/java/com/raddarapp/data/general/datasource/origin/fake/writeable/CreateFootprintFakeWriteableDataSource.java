package com.raddarapp.data.general.datasource.origin.fake.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateFootprintWriteableDataSourceContract;
import com.raddarapp.domain.model.CreateFootprint;
import com.raddarapp.domain.model.RaddarLocation;
import com.raddarapp.domain.model.builder.CreateFootprintBuilder;
import com.raddarapp.domain.model.builder.RaddarLocationBuilder;
import com.raddarapp.domain.model.enums.AspectRatioType;
import com.raddarapp.domain.model.enums.FootprintCategory;
import com.raddarapp.domain.model.enums.FootprintMediaType;
import com.raddarapp.domain.model.enums.FootprintType;
import com.raddarapp.domain.model.enums.SponsoredType;
import com.raddarapp.domain.model.enums.VisibilityType;

import java.util.Random;

import javax.inject.Inject;

public class CreateFootprintFakeWriteableDataSource extends EmptyWriteableDataSource<String, CreateFootprint>
        implements CreateFootprintWriteableDataSourceContract {

    private static final Random RANDOM = new Random(System.nanoTime());
    private static final String CREATE_FOOTPRINT_KEY = "fakeKey";
    private static final long FAKE_DELAY_MILLISECONDS = 1500;
    private static final String EMOJI_SPAIN = "ES";

    @Inject
    public CreateFootprintFakeWriteableDataSource() {
    }

    @Override
    public CreateFootprint addOrUpdate(CreateFootprint createFootprint, String uriImage) {
        fakeDelay();

        final RaddarLocation raddarLocation = new RaddarLocationBuilder()
                .withLatitude(37.38638)
                .withLongitude(-5.9)
                .withCreationMoment("2017-11-02T14:25:23.000+0000")
                .build();

        final CreateFootprint createdFootprint =  new CreateFootprintBuilder()
                .withKey(CREATE_FOOTPRINT_KEY + RANDOM.nextInt(Integer.MAX_VALUE))
                .withMediaName("https://cdn.pixabay.com/photo/2018/03/01/09/33/wood-3190203_1280.jpg")
                .withTitle("This is your fake photo taken")
                .withDescription("Description of your fake photo taken")
                .withMediaType(FootprintMediaType.IMAGE.getValue())
                .withVisibility(VisibilityType.PUBLIC.getValue())
                .withAspectRatio(AspectRatioType.SQUARE.getValue())
                .withSponsored(SponsoredType.NO_SPONSORED.getValue())
                .withRaddarLocation(raddarLocation)
                .withCurrentZoneName("Montequinto")
                .withCurrentZoneParentName("Dos Hermanas")
                .withCountryEmoji(EMOJI_SPAIN)
                .withCategory(FootprintCategory.CATEGORY_EMOJI_UNCLASSIFIED.getValue())
                .withType(FootprintType.FOOTPRINT.getValue())
                .withVisible(true)
                .build();

        return createdFootprint;
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_DELAY_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
