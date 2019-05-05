package com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper;

import com.karumi.rosie.mapper.Mapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.ResponseDto;

public class BooleanToResponseDtoMapper extends Mapper<Boolean, ResponseDto> {

    @Override
    public Boolean reverseMap(ResponseDto responseDto) {
        boolean res = false;

        if (responseDto != null && responseDto.getResponse() != null &&
                !responseDto.getResponse().isEmpty()) {
            res = true;
        }

        return res;
    }

    @Override
    public ResponseDto map(Boolean value) {
        throw new UnsupportedOperationException();
    }
}
