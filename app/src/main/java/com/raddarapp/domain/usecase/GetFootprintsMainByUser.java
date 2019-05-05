package com.raddarapp.domain.usecase;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.raddarapp.data.general.FootprintsMainRepository;
import com.raddarapp.domain.model.FootprintMain;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

public class GetFootprintsMainByUser extends RosieUseCase {

    public static final String USE_CASE_GET_FOOTPRINTS_MAIN_BY_USER = "getFootprintsMainByUser";

    private final FootprintsMainRepository footprintsMainRepository;

    @Inject
    public GetFootprintsMainByUser(FootprintsMainRepository footprintsMainRepository) {
        this.footprintsMainRepository = footprintsMainRepository;
    }

    public PaginatedCollection<FootprintMain> getAllFootprintsMainInCache() {
        Collection<FootprintMain> all;

        try {
            all = footprintsMainRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<FootprintMain> footprintsMain = new PaginatedCollection<>(all);
        footprintsMain.setPage(page);
        footprintsMain.setHasMore(true);

        return footprintsMain;
    }

    public void deleteCache() {
        try {
            footprintsMainRepository.deleteAll();
        } catch (Exception e) {
        }
    }

    @UseCase(name = USE_CASE_GET_FOOTPRINTS_MAIN_BY_USER)
    public void getFootprintsMain(String userKey, Page page) throws Exception {
        PaginatedCollection<FootprintMain> footprintsMain = footprintsMainRepository.getFootprintsMainByUserId(userKey,page);
        notifySuccess(footprintsMain);
    }
}
