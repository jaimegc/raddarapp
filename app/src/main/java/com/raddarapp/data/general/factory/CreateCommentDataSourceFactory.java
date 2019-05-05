package com.raddarapp.data.general.factory;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.CreateCommentApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.writeable.CreateCommentApiWriteableDataSource;
import com.raddarapp.data.general.datasource.base.BaseDataSourceFactory;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.data.general.datasource.base.contract.writeable.CreateCommentWriteableDataSourceContract;
import com.raddarapp.data.general.datasource.origin.fake.writeable.CreateCommentFakeWriteableDataSource;

import javax.inject.Inject;

public class CreateCommentDataSourceFactory extends BaseDataSourceFactory {

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource;

    @Inject
    public CreateCommentDataSourceFactory(MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource) {
        this.userProfilePreferencesDataSource = userProfilePreferencesDataSource;
    }

    public CreateCommentWriteableDataSourceContract createWriteableDataSource() {
        if (getOrigin().equals(API_FAKE)) {
            return new CreateCommentFakeWriteableDataSource();
        } else {
            ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());
            CreateCommentApiClient createCommentApiClient = new CreateCommentApiClient(serverApiConfig);
            return new CreateCommentApiWriteableDataSource(createCommentApiClient);
        }
    }
}
