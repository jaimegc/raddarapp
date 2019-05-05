package com.raddarapp.presentation.android.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.karumi.rosie.view.Presenter;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.android.activity.EnterLoginActivity;
import com.raddarapp.presentation.android.activity.EnterLoginSocialActivity;
import com.raddarapp.presentation.android.fragment.base.BaseNormalFragment;
import com.raddarapp.presentation.android.utils.FontUtils;
import com.raddarapp.presentation.general.presenter.LoginPresenter;
import com.shobhitpuri.custombuttons.GoogleSignInButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseNormalFragment implements LoginPresenter.View {

    private static final boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    private static final String FONT_NAME = BuildConfig.BASE_FONT_NAME;
    private static final String[] FACEBOOK_PERMISSIONS = {"email"};
    private static final int GOOGLE_SIGN_REQUEST_CODE = 444;
    private static final String GOOGLE_OAUTH_CLIENT_ID = BuildConfig.GOOGLE_OAUTH_CLIENT_ID;

    @BindView(R.id.login_facebook) LoginButton loginFacebookView;
    @BindView(R.id.login_google) GoogleSignInButton loginGoogleView;
    @BindView(R.id.ready) TextView readyView;
    @BindView(R.id.have_account) TextView haveAccountView;
    @BindView(R.id.have_account_log_in) TextView haveAccountLogInView;
    @BindView(R.id.mock_text) TextView mockTextView;
    @BindView(R.id.linear_login_normal) LinearLayout linearLoginNormalView;

    // Facebook
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    // Google
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount account;

    @Inject @Presenter
    LoginPresenter presenter;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    protected void onPrepareFragment(View view) {
        super.onPrepareFragment(view);

        if (IS_DEBUG) {
            linearLoginNormalView.setVisibility(View.VISIBLE);
        }

        initializeFacebookButton();
        initializeGoogleButton();
        initializeFonts();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    private void initializeFacebookButton() {
        callbackManager = CallbackManager.Factory.create();
        loginFacebookView.setReadPermissions(FACEBOOK_PERMISSIONS);
        loginFacebookView.setFragment(this);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        if(Profile.getCurrentProfile() == null) {
                            profileTracker = new ProfileTracker() {
                                @Override
                                protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                                    profileTracker.stopTracking();
                                    loginFacebookSuccessful();
                                }
                            };
                        } else {
                            loginFacebookSuccessful();
                        }
                    }

                    @Override
                    public void onCancel() {}

                    @Override
                    public void onError(FacebookException exception) {
                        showLoginFacebookError();
                    }
                });
    }

    private void initializeGoogleButton() {

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(GOOGLE_OAUTH_CLIENT_ID)
                .requestProfile()
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(getActivity(), googleSignInOptions);
        account = GoogleSignIn.getLastSignedInAccount(getActivity());

        if (account != null) {
            googleSignInClient.revokeAccess();
            googleSignInClient.signOut();
        }
    }

    @OnClick(R.id.login_google)
    public void onLoginGoogleClicked() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_REQUEST_CODE);
    }

    private void initializeFonts() {
        FontUtils fontUtils = new FontUtils();

        fontUtils.applyFont(getActivity(), FONT_NAME, readyView, haveAccountLogInView, haveAccountView, mockTextView);

        haveAccountLogInView.setText(Html.fromHtml(getString(R.string.have_account_log_in)));
    }

    @OnClick(R.id.have_account_log_in)
    public void onLoginClicked() {
        EnterLoginActivity.open(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_REQUEST_CODE) {
            loginGoogleFromIntent(data);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void loginGoogleFromIntent(Intent data) {
        Task<GoogleSignInAccount> completedTask = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {
            account = completedTask.getResult(ApiException.class);
            loginGoogleSuccessful(account.getIdToken());
        } catch (ApiException e) {
            googleSignInClient.revokeAccess();
            googleSignInClient.signOut();
        }

    }

    private void loginFacebookSuccessful() {
        EnterLoginSocialActivity.openEnterLoginFacebook(getActivity(), AccessToken.getCurrentAccessToken().getToken());
    }

    private void loginGoogleSuccessful(String token) {
        EnterLoginSocialActivity.openEnterLoginGoogle(getActivity(), token);
    }

    private void showLoginFacebookError() {
        initSnackbarError(R.string.error_login_facebook);
    }

    private void showLoginGoogleError() {
        initSnackbarError(R.string.error_login_google);
    }
}
