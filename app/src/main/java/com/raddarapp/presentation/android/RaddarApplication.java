package com.raddarapp.presentation.android;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.karumi.rosie.application.RosieApplication;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.presentation.android.activity.CrashActivity;
import com.raddarapp.presentation.android.activity.FootprintMainActivity;
import com.raddarapp.presentation.android.di.module.RaddarApplicationModule;
import com.raddarapp.presentation.android.utils.EmojiUtils;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.google.GoogleEmojiProvider;

import java.util.Arrays;
import java.util.List;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Raddar application that registers the global application dependencies.
 */
public class RaddarApplication extends RosieApplication {

    private static RaddarApplication instance;
    private static FootprintMainActivity footprintMainActivity;
    // See to implment Analytics with Clean Architecture: https://github.com/lolevsky/Android-Clean-Architecture
    // https://medium.com/@lolevsky/inject-firebase-into-your-android-project-58b5d77744e9
    private static FirebaseAnalytics analytics;

    public RaddarApplication() {
        instance = this;
    }

    public static Context getContext() {
        if (instance == null) {
            instance = new RaddarApplication();
        }
        return instance;
    }

    public static FootprintMainActivity getFootprintMainActivity() {
        return footprintMainActivity;
    }

    public static void setFootprintMainActivity(FootprintMainActivity activity) {
        footprintMainActivity = activity;
    }

    public static FirebaseAnalytics getAnalytics() {
        return analytics;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        Configuration configuration = base.getResources().getConfiguration();
        configuration.fontScale = 1.0f;
        base.getResources().updateConfiguration(configuration, base.getResources().getDisplayMetrics());
    }

    @Override
    public List<Object> getApplicationModules() {
        return Arrays.asList((Object) new RaddarApplicationModule(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeCalligraphy();
        initializeCrashErrors();
        initializeAnalytics();
        initializeFabric();
        initializeEmojis();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/ " + BuildConfig.BASE_FONT_NAME)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static void showGlobalMessage() {
        Toast.makeText(getContext(), "Show Global Message... In the future :]", Toast.LENGTH_SHORT).show();
    }

    private void initializeCrashErrors() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
                .enabled(true)
                .showErrorDetails(false)
                .showRestartButton(false)
                .trackActivities(true)
                .minTimeBetweenCrashesMs(2000)
                .errorActivity(CrashActivity.class)
                .apply();
    }

    private void initializeAnalytics() {
        analytics = FirebaseAnalytics.getInstance(this);
    }

    private void initializeFabric() {
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(BuildConfig.IS_DEBUG)
                .build();
        Fabric.with(fabric);
    }

    private void initializeEmojis() {
        EmojiManager.install(new GoogleEmojiProvider());
        EmojiUtils.initializeEmojis(this);
    }
}