package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserPasswordDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.MyUserPasswordDtoBuilder;
import com.raddarapp.domain.model.MyUserPassword;

public class MyUserPasswordToMyUserPasswordDtoMapper extends Mapper<MyUserPassword, MyUserPasswordDto> {

    @Override
    public MyUserPasswordDto map(MyUserPassword userPassword) {
        final MyUserPasswordDto userPasswordDto = new MyUserPasswordDtoBuilder()
                .withUser(userPassword.getUser())
                .withPassword(userPassword.getPassword())
                .build();

        return userPasswordDto;
    }

    @Override
    public MyUserPassword reverseMap(MyUserPasswordDto value) {
        throw new UnsupportedOperationException();
    }
}
