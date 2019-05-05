package com.raddarapp.data.android.external.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.raddarapp.BuildConfig;
import com.raddarapp.presentation.android.RaddarApplication;
import com.raddarapp.presentation.android.utils.PreferencesUtils;

import java.util.Locale;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIDService";
    private static final boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    private static final String DEBUG = "Debug";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        new PreferencesUtils(this).setMyNotificationToken(refreshedToken);
        // We need to subscribe a topic for silent notifications
        String debug = IS_DEBUG ? DEBUG : "";
        String language = Locale.getDefault().getLanguage();
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices" + debug + "_" + language);
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
    }
}
