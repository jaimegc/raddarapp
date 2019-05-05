package com.raddarapp.data.android.repository.datasource.origin.remote.writeable;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.client.UpdateMyUserProfileApiClient;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserProfileToMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.UpdateMyUserProfileToUpdateMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.general.datasource.base.contract.writeable.UpdateMyUserProfileWriteableDataSourceContract;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.UpdateMyUserProfile;

import javax.inject.Inject;

public class UpdateMyUserProfileApiWriteableDataSource extends EmptyWriteableDataSource<String, UpdateMyUserProfile>
        implements UpdateMyUserProfileWriteableDataSourceContract {

    private final UpdateMyUserProfileApiClient updateComplimentApiClient;
    private final UpdateMyUserProfileToUpdateMyUserProfileDtoMapper mapperUpdateMyUserProfile = new UpdateMyUserProfileToUpdateMyUserProfileDtoMapper();
    private final MyUserProfileToMyUserProfileDtoMapper mapper = new MyUserProfileToMyUserProfileDtoMapper();

    @Inject
    public UpdateMyUserProfileApiWriteableDataSource(UpdateMyUserProfileApiClient updateMyUserProfileApiClient) {
        this.updateComplimentApiClient = updateMyUserProfileApiClient;
    }

    @Override
    public MyUserProfile updateMyUserProfile(UpdateMyUserProfile updateMyUserProfile) throws Exception {
        MyUserProfile userProfile;
        MyUserProfileDto userProfileDto;

        userProfileDto = updateComplimentApiClient.updateMyUserProfile(mapperUpdateMyUserProfile.map(updateMyUserProfile));
        userProfile = mapper.reverseMap(userProfileDto);

        return userProfile;
    }

}