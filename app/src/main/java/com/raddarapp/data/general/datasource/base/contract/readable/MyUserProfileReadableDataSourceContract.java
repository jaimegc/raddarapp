package com.raddarapp.data.general.datasource.base.contract.readable;

import com.karumi.rosie.repository.datasource.ReadableDataSource;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.MyUserLoginSocialToken;
import com.raddarapp.domain.model.MyUserPassword;

public interface MyUserProfileReadableDataSourceContract extends ReadableDataSource<String, MyUserProfile> {

    MyUserProfile loginFacebook(MyUserLoginSocialToken userFacebookToken) throws Exception;

    MyUserProfile loginGoogle(MyUserLoginSocialToken userGoogleToken) throws Exception;

    MyUserProfile login(MyUserPassword userPassword) throws Exception;

    boolean logout() throws Exception;
}
