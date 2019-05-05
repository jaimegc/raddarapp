package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder;

import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RefreshTokenDto;

public class RefreshTokenDtoBuilder {

    private String refreshToken;

    public RefreshTokenDtoBuilder() {}

    public RefreshTokenDto build() {
        return new RefreshTokenDto(refreshToken);
    }

    public RefreshTokenDtoBuilder withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
