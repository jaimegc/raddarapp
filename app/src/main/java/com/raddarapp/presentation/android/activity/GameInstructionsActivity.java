package com.raddarapp.presentation.android.activity;


import android.content.Context;
import android.content.Intent;

import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.base.BaseNormalActivity;
import com.raddarapp.presentation.android.di.module.GameInstructionsModule;
import com.raddarapp.presentation.android.fragment.GameInstructionsFragment;
import com.raddarapp.presentation.android.utils.ActivityUtils;

import java.util.Arrays;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GameInstructionsActivity extends BaseNormalActivity {

    private GameInstructionsFragment gameInstructionsFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_game_instructions;
    }

    @Override
    protected List<Object> getActivityScopeModules() {
        return Arrays.asList((Object) new GameInstructionsModule());
    }

    @Override
    protected void onPrepareActivity() {
        super.onPrepareActivity();

        initializeFragment();
    }

    private void initializeFragment() {
        gameInstructionsFragment =
                (GameInstructionsFragment) getFragmentManager().findFragmentById(R.id.content_frame);

        if (gameInstructionsFragment == null) {
            gameInstructionsFragment = new GameInstructionsFragment().newInstance();

            ActivityUtils.addFragmentToActivity(getFragmentManager(),
                    gameInstructionsFragment, R.id.content_frame);
        }
    }

    public static void open(Context context) {
        Intent intent = new Intent(context, GameInstructionsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
