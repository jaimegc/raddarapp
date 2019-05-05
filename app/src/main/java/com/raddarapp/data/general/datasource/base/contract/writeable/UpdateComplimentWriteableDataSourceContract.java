package com.raddarapp.data.general.datasource.base.contract.writeable;


import com.karumi.rosie.repository.datasource.WriteableDataSource;
import com.raddarapp.domain.model.UpdateCompliment;

public interface UpdateComplimentWriteableDataSourceContract extends WriteableDataSource<String, UpdateCompliment> {

    boolean updateMyCompliments(UpdateCompliment updateCompliment) throws Exception;

    boolean updateCompliments(UpdateCompliment updateCompliment) throws Exception;
}
