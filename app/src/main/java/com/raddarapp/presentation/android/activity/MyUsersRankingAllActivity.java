package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.MyUsersRankingAllModule;
import com.raddarapp.presentation.android.fragment.MyUsersRankingAllFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyUsersRankingAllActivity extends BaseNormalActivity {

    private MyUsersRankingAllFragment myUsersRankingFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_users_ranking_all;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new MyUsersRankingAllModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        myUsersRankingFragment =
                (MyUsersRankingAllFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (myUsersRankingFragment == null) {
            myUsersRankingFragment = new MyUsersRankingAllFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    myUsersRankingFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, MyUsersRankingAllActivity.class);
        context.startActivity(intent);
    }

    public static void openNewTask(Context context) {
        Intent intent = new Intent(context, MyUsersRankingAllActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
