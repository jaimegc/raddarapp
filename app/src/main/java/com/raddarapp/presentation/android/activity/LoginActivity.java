package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.CommentsModule;
import com.raddarapp.presentation.android.di.module.LoginModule;
import com.raddarapp.presentation.android.fragment.CommentsFragment;
import com.raddarapp.presentation.android.fragment.LoginFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends BaseNormalActivity {

    private LoginFragment loginFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new LoginModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        loginFragment =
                (LoginFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (loginFragment == null) {
            loginFragment = new LoginFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    loginFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (loginFragment != null && loginFragment.isAdded()) {
            loginFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
