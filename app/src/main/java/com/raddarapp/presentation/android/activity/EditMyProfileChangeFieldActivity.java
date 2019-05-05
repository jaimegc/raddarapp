package com.raddarapp.presentation.android.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.EditMyProfileChangeFieldModule;
import com.raddarapp.presentation.android.fragment.EditMyProfileChangeFieldFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditMyProfileChangeFieldActivity extends BaseNormalActivity {

    private static final String CHANGE_FIELD_TYPE_KEY_EXTRA = "EditMyProfileChangeFieldActivity.TypeChangeField";
    private static final String CHANGE_FIELD_TEXT_KEY_EXTRA = "EditMyProfileChangeFieldActivity.TypeChangeText";
    private static final int CHANGE_FIELD_TYPE_USERNAME = 0;
    private static final int CHANGE_FIELD_TYPE_NAME = 1;
    private static final int CHANGE_FIELD_TYPE_SURNAME = 2;
    private static final int CHANGE_FIELD_TYPE_EMAIL = 3;
    private static final int CHANGE_FIELD_TYPE_PASSWORD = 4;

    private EditMyProfileChangeFieldFragment editMyProfileChangeFieldFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_my_profile_change_field;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new EditMyProfileChangeFieldModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        Bundle extras = getIntent().getExtras();
        int changeFieldType = extras.getInt(CHANGE_FIELD_TYPE_KEY_EXTRA);
        String text = extras.getString(CHANGE_FIELD_TEXT_KEY_EXTRA);

        initializeFragment(changeFieldType, text);
    }

    private void initializeFragment(int changeFieldType, String text) {
        editMyProfileChangeFieldFragment =
                (EditMyProfileChangeFieldFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (editMyProfileChangeFieldFragment == null) {
            editMyProfileChangeFieldFragment = new EditMyProfileChangeFieldFragment().newInstance(changeFieldType, text);

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    editMyProfileChangeFieldFragment, R.id.content_frame);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static void openChangeUsername(Activity activity, String text) {
        Intent intent = new Intent(activity, EditMyProfileChangeFieldActivity.class);
        intent.putExtra(CHANGE_FIELD_TYPE_KEY_EXTRA, CHANGE_FIELD_TYPE_USERNAME);
        intent.putExtra(CHANGE_FIELD_TEXT_KEY_EXTRA, text);
        activity.startActivity(intent);
    }

    public static void openChangeName(Activity activity, String text) {
        Intent intent = new Intent(activity, EditMyProfileChangeFieldActivity.class);
        intent.putExtra(CHANGE_FIELD_TYPE_KEY_EXTRA, CHANGE_FIELD_TYPE_NAME);
        intent.putExtra(CHANGE_FIELD_TEXT_KEY_EXTRA, text);
        activity.startActivity(intent);
    }

    public static void openChangeSurname(Activity activity, String text) {
        Intent intent = new Intent(activity, EditMyProfileChangeFieldActivity.class);
        intent.putExtra(CHANGE_FIELD_TYPE_KEY_EXTRA, CHANGE_FIELD_TYPE_SURNAME);
        intent.putExtra(CHANGE_FIELD_TEXT_KEY_EXTRA, text);
        activity.startActivity(intent);
    }

    public static void openChangeEmail(Activity activity, String text) {
        Intent intent = new Intent(activity, EditMyProfileChangeFieldActivity.class);
        intent.putExtra(CHANGE_FIELD_TYPE_KEY_EXTRA, CHANGE_FIELD_TYPE_EMAIL);
        intent.putExtra(CHANGE_FIELD_TEXT_KEY_EXTRA, text);
        activity.startActivity(intent);
    }

    public static void openChangePassword(Activity activity) {
        Intent intent = new Intent(activity, EditMyProfileChangeFieldActivity.class);
        intent.putExtra(CHANGE_FIELD_TYPE_KEY_EXTRA, CHANGE_FIELD_TYPE_PASSWORD);
        intent.putExtra(CHANGE_FIELD_TEXT_KEY_EXTRA, "");
        activity.startActivity(intent);
    }
}
