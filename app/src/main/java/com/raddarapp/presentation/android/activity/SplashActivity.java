package com.raddarapp.presentation.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.raddarapp.BuildConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.config.ServerApiConfig;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.mapper.MyUserProfileToMyUserProfileDtoMapper;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.RefreshTokenDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyUserProfileDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.builder.RefreshTokenDtoBuilder;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.rest.MyUserProfileApiRest;
import com.raddarapp.data.android.repository.datasource.origin.remote.preferences.MyUserProfilePreferencesDataSource;
import com.raddarapp.data.general.datasource.base.contract.preferences.MyUserProfilePreferencesDataSourceContract;
import com.raddarapp.domain.model.MyUserProfile;
import com.raddarapp.domain.model.enums.UserGenderType;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.android.utils.FileUtils;
import com.raddarapp.presentation.android.utils.PreferencesUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private final static String DATA_ORIGIN = BuildConfig.DATA_ORIGIN;
    private final static String API_FAKE = "API_FAKE";
    private final static int DAYS_EXPIRE_ACCESS_TOKEN = 30;
    private final static int DAYS_TRY_UPDATE_ACCESS_TOKEN = 15;
    private final static long EXPIRE_ACCESS_TOKEN = DAYS_EXPIRE_ACCESS_TOKEN * 24 * 60 * 60 * 1000;
    private final static long TRY_UPDATE_ACCESS_TOKEN = DAYS_TRY_UPDATE_ACCESS_TOKEN * 24 * 60 * 60 * 1000;
    private boolean isAccessTokenExpired = false;
    private static final String GOOGLE_OAUTH_CLIENT_ID = BuildConfig.GOOGLE_OAUTH_CLIENT_ID;
    private static final String NOTIFICATION_TOPIC = "notification_topic";

    private MyUserProfilePreferencesDataSourceContract userProfilePreferencesDataSource =
            new MyUserProfilePreferencesDataSource(RaddarApplication.getContext());

    private PreferencesUtils preferencesUtils = new PreferencesUtils(RaddarApplication.getContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (preferencesUtils.getMyNotificationToken().isEmpty()) {
            String myNotificationToken = FirebaseInstanceId.getInstance().getToken();

            if (myNotificationToken != null && !myNotificationToken.isEmpty()) {
                preferencesUtils.setMyNotificationToken(myNotificationToken);
            }
        }

        if (!preferencesUtils.showWelcomeScreen()) {
            if (userProfilePreferencesDataSource.isLoggedIn()) {
                long timeLastUpdateAccessToken = System.currentTimeMillis() - userProfilePreferencesDataSource.getRefreshTokenUpdated();

                if (!DATA_ORIGIN.equals(API_FAKE)) {
                    if (timeLastUpdateAccessToken < TRY_UPDATE_ACCESS_TOKEN) {
                        checkFirstScreen();
                    } else {
                        if (timeLastUpdateAccessToken >= EXPIRE_ACCESS_TOKEN) {
                            isAccessTokenExpired = true;
                        }

                        refreshToken();
                    }
                } else {
                    checkFirstScreen();
                }
            } else {
                goLogin();
            }
        } else {
            WelcomeScreenActivity.open(this);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    private void refreshToken() {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDtoBuilder()
                .withRefreshToken(userProfilePreferencesDataSource.getRefreshToken())
                .build();

        ServerApiConfig serverApiConfig = ServerApiConfig.with(userProfilePreferencesDataSource.getAccessToken());

        MyUserProfileApiRest service = serverApiConfig.getRetrofit().create(MyUserProfileApiRest.class);

        Call<MyUserProfileDto> callRefreshToken = service.refreshToken(refreshTokenDto);

        callRefreshToken.enqueue(new Callback<MyUserProfileDto>() {
            @Override
            public void onResponse(Call<MyUserProfileDto> call, Response<MyUserProfileDto> response) {

                if (response.isSuccessful() && response.body() != null) {
                    final MyUserProfileToMyUserProfileDtoMapper userProfileToUserProfileDtoMapper = new MyUserProfileToMyUserProfileDtoMapper();
                    MyUserProfile userProfile = userProfileToUserProfileDtoMapper.reverseMap(response.body());
                    userProfilePreferencesDataSource.setUserProfilePreferencesFull(userProfile);
                    userProfilePreferencesDataSource.setRefreshTokenUpdated(System.currentTimeMillis());
                    goMain();
                } else {
                    if (!isAccessTokenExpired) {
                        checkFirstScreen();
                    } else{
                        goLogin();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyUserProfileDto> call, Throwable t) {
                if (!isAccessTokenExpired) {
                    checkFirstScreen();
                } else{
                    goLogin();
                }
            }
        });
    }

    private void goLogin() {
        userProfilePreferencesDataSource.logout();
        new FileUtils(this).deleteAllCache();
        closeLoginSocial();
        LoginActivity.open(SplashActivity.this);
        finish();
    }

    private void closeLoginSocial() {
        // Close Facebook
        LoginManager.getInstance().logOut();

        // Close Google
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(GOOGLE_OAUTH_CLIENT_ID)
                .requestProfile()
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        if (account != null) {
            googleSignInClient.revokeAccess();
            googleSignInClient.signOut();
        }
    }

    private void goMain() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        if (intent != null && intent.getStringExtra(NOTIFICATION_TOPIC) != null) {
            FootprintMainActivity.openFromNotification(this, intent);
        } else {
            FootprintMainActivity.open(this, false, false);
        }

        finish();
    }

    private void checkFirstScreen() {
        if (preferencesUtils.getGender() != UserGenderType.OTHER.getValue() && !preferencesUtils.getBirthdate().isEmpty() &&
                !preferencesUtils.getEmail().isEmpty()) {
            if (!preferencesUtils.showWelcomeInAppScreen()) {
                goMain();
            } else {
                WelcomeInAppScreenActivity.open(SplashActivity.this);
            }
        } else {
            EnterCompleteProfileActivity.open(SplashActivity.this);
        }
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
