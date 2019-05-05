package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.EnterLoginSocialModule;
import com.raddarapp.presentation.android.fragment.EnterLoginSocialFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.general.presenter.LogoutEnterLoginPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterLoginSocialActivity extends BaseNormalActivity implements LogoutEnterLoginPresenter.View {

    private EnterLoginSocialFragment enterLoginSocialFragment;
    private static final String INDEX_LOGIN_KEY_EXTRA = "EnterLoginSocialToken.IndexLoginKeyExtra";
    private static final String TOKEN_LOGIN_KEY_EXTRA = "EnterLoginSocialToken.IndexTokenKeyExtra";
    private static final int INDEX_FACEBOOK_LOGIN = 0;
    private static final int INDEX_GOOGLE_LOGIN = 1;
    private static final String GOOGLE_OAUTH_CLIENT_ID = BuildConfig.GOOGLE_OAUTH_CLIENT_ID;
    private int indexLogin;
    private String token;

    @Inject
    LogoutEnterLoginPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_login_social;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new EnterLoginSocialModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        indexLogin = extras.getInt(INDEX_LOGIN_KEY_EXTRA);
        token = extras.getString(TOKEN_LOGIN_KEY_EXTRA);

        initializeFragment(indexLogin, token);
    }

    private void initializeFragment(Integer indexLogin, String token) {
        enterLoginSocialFragment =
                (EnterLoginSocialFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (enterLoginSocialFragment == null) {
            enterLoginSocialFragment = new EnterLoginSocialFragment().newInstance(indexLogin, token);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    enterLoginSocialFragment, R.id.content_frame);
        }
    }

    public static void openEnterLoginFacebook(Context context, String token) {
        Intent intent = new Intent(context, EnterLoginSocialActivity.class);
        intent.putExtra(INDEX_LOGIN_KEY_EXTRA, INDEX_FACEBOOK_LOGIN);
        intent.putExtra(TOKEN_LOGIN_KEY_EXTRA, token);
        context.startActivity(intent);
    }

    public static void openEnterLoginGoogle(Context context, String token) {
        Intent intent = new Intent(context, EnterLoginSocialActivity.class);
        intent.putExtra(INDEX_LOGIN_KEY_EXTRA, INDEX_GOOGLE_LOGIN);
        intent.putExtra(TOKEN_LOGIN_KEY_EXTRA, token);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        presenter.logout();

        if (indexLogin == INDEX_FACEBOOK_LOGIN) {
            LoginManager.getInstance().logOut();
        } else if (indexLogin == INDEX_GOOGLE_LOGIN) {
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

        finish();
    }
}
