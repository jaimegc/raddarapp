package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.FootprintMainApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.AddVoteDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.FootprintsMainWriteableDataSourceContract;
import com.raddarapp.domain.model.FootprintMain;

import javax.inject.Inject;

public class FootprintMainApiWriteableDataSource extends EmptyWriteableDataSource<String, FootprintMain>
        implements FootprintsMainWriteableDataSourceContract {

    private final FootprintMainApiClient footprintMainApiClient;

    @Inject
    public FootprintMainApiWriteableDataSource(FootprintMainApiClient footprintMainApiClient) {
        this.footprintMainApiClient = footprintMainApiClient;
    }

    @Override
    public double addVote(String footprintKey, int addVoteType) throws Exception {
        AddVoteDto addVoteDto = footprintMainApiClient.addVote(footprintKey, addVoteType);

        return addVoteDto.getActualScope();
    }
}