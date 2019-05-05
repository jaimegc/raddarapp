package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.EnterCompleteProfileModule;
import com.raddarapp.presentation.android.fragment.EnterCompleteProfileFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;
import com.raddarapp.presentation.general.presenter.LogoutEnterLoginPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterCompleteProfileActivity extends BaseNormalActivity implements LogoutEnterLoginPresenter.View {

    private static final String GOOGLE_OAUTH_CLIENT_ID = BuildConfig.GOOGLE_OAUTH_CLIENT_ID;

    private EnterCompleteProfileFragment enterCompleteProfileFragment;

    @Inject
    LogoutEnterLoginPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enter_complete_profile;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new EnterCompleteProfileModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        enterCompleteProfileFragment =
                (EnterCompleteProfileFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (enterCompleteProfileFragment == null) {
            enterCompleteProfileFragment = new EnterCompleteProfileFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    enterCompleteProfileFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, EnterCompleteProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (enterCompleteProfileFragment != null && enterCompleteProfileFragment.isAdded() && data != null) {
            enterCompleteProfileFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        presenter.logout();

        LoginManager.getInstance().logOut();
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

        SplashActivity.open(this);
    }
}
