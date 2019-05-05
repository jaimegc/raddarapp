package com.raddarapp.data.android.repository.datasource.origin.remote.api.config;

import com.google.gson.Gson;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerAlreadyExchangedPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerConnectionApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerExpiredPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerFootprintAlreadyVotedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerAccessDataApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerBannedUserApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerIncorrectPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerInvalidPromoCodeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerNoLikedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerNoUnlikedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerNullApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRangeException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedEmailApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedLikeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedUnlikeApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerRepeatedUserApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUnknownApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUploadFileApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserFollowedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserNotExistApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUserUnauthorizedApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.ServerUsernamePatternApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerApiException;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.error.base.ServerError;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ServerApiClient {

    public static final String ERROR_USER_UNAUTHORIZED = "E00";
    public static final String ERROR_USERNAME_EXISTS = "E01";
    public static final String ERROR_EMAIL_EXISTS = "E02";
    public static final String ERROR_BANNED_USER = "E03";
    public static final String ERROR_INCORRECT_ACCESS_DATA = "E04";
    public static final String ERROR_USER_NOT_EXIST = "E05";
    public static final String ERROR_LIKED = "E06";
    public static final String ERROR_NOT_LIKED = "E07";
    public static final String ERROR_UNLIKED = "E08";
    public static final String ERROR_NOT_UNLIKED = "E09";
    public static final String ERROR_UPLOAD_FILE_ERROR = "E10";
    public static final String ERROR_USER_FOLLOWED = "E11";
    public static final String ERROR_FOOTPRINT_ALREADY_VOTED = "E14";
    public static final String ERROR_EXPIRED_PROMO_CODE = "E15";
    public static final String ERROR_INCORRECT_PROMO_CODE = "E16"; // Incorrect Promotion
    public static final String ERROR_PROMO_CODE_ALREADY_EXCHANGED = "E17";
    public static final String ERROR_USERNAME_PATTERN = "E24";
    public static final String ERROR_INVALID_PROMO_CODE = "E26"; // Incorrect code
    public static final String ERROR_INCORRECT_PATTERN = "E96";
    public static final String ERROR_INCORRECT_RANGE = "E97";
    public static final String ERROR_NULL_ELEMENT = "E98";
    public static final String ERROR_UNKNOWN = "E99";

    // FIXME Bad place :[
    public static String SERVER_DATE;

    private final ServerApiConfig serverApiConfig;

    public ServerApiClient(ServerApiConfig serverApiConfig) {
        this.serverApiConfig = serverApiConfig;
    }

    public <T> T getApi(Class<T> apiRest) {
        return serverApiConfig.getRetrofit().create(apiRest);
    }

    public <T> T execute(Call<T> call) throws ServerApiException {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new ServerConnectionApiException();
        } catch(Exception e) {
            throw new ServerConnectionApiException();
        }

        if (response.isSuccessful()) {
            SERVER_DATE = response.headers().get("Date");
            return response.body();
        } else {
            parseError(response);
            return null;
        }
    }

    private void parseError(Response execute) throws ServerApiException {
        if (execute.errorBody() != null) {
            Gson gson = new Gson();
            try {
                String errorBody = execute.errorBody().string();
                ServerError serverError = gson.fromJson(errorBody, ServerError.class);
                String errorCode = serverError.getErrorCode();

                if (errorCode != null) {
                    switch(errorCode) {
                        case ERROR_USER_UNAUTHORIZED:
                            throw new ServerUserUnauthorizedApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_USERNAME_EXISTS:
                            throw new ServerRepeatedUserApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_EMAIL_EXISTS:
                            throw new ServerRepeatedEmailApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_BANNED_USER:
                            throw new ServerBannedUserApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_INCORRECT_ACCESS_DATA:
                            throw new ServerAccessDataApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_USER_NOT_EXIST:
                            throw new ServerUserNotExistApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_LIKED:
                            throw new ServerRepeatedLikeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_NOT_LIKED:
                            throw new ServerNoLikedApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_UNLIKED:
                            throw new ServerRepeatedUnlikeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_NOT_UNLIKED:
                            throw new ServerNoUnlikedApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_UPLOAD_FILE_ERROR:
                            throw new ServerUploadFileApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_USER_FOLLOWED:
                            throw new ServerUserFollowedApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_FOOTPRINT_ALREADY_VOTED:
                            throw new ServerFootprintAlreadyVotedApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_EXPIRED_PROMO_CODE:
                            throw new ServerExpiredPromoCodeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_INCORRECT_PROMO_CODE:
                            throw new ServerIncorrectPromoCodeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_INVALID_PROMO_CODE:
                            throw new ServerInvalidPromoCodeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_PROMO_CODE_ALREADY_EXCHANGED:
                            throw new ServerAlreadyExchangedPromoCodeApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_USERNAME_PATTERN:
                            throw new ServerUsernamePatternApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_INCORRECT_RANGE:
                            throw new ServerRangeException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_NULL_ELEMENT:
                            throw new ServerNullApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        case ERROR_UNKNOWN:
                            throw new ServerUnknownApiException(serverError.getHttpCode(), serverError.getHttpMessage(), serverError.getErrorCode());
                        default:
                            throw new ServerUnknownApiException();
                    }
                } else {
                    throw new ServerUnknownApiException();
                }
            } catch (IOException e) {
                throw new ServerUnknownApiException();
            }
        } else {
            throw new ServerUnknownApiException();
        }
    }
}
