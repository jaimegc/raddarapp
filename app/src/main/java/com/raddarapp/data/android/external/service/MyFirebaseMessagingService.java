package com.raddarapp.data.android.external.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.GsonBuilder;
import com.raddarapp.BuildConfig;
import com.raddarapp.R;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.MyFootprintDto;
import com.raddarapp.data.android.repository.datasource.origin.remote.api.model.NotificationUrlDto;
import com.raddarapp.presentation.android.activity.MyFootprintDetailsActivity;
import com.raddarapp.presentation.android.utils.PreferencesUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String SERVER_IMAGES_API_BASE_URL_COMPLETE = BuildConfig.SERVER_IMAGES_API_BASE_URL_COMPLETE;
    private static final String SERVER_IMAGES_MEDIUM_SUFFIX = BuildConfig.SERVER_IMAGES_MEDIUM_SUFFIX;

    public static final String ANDROID_CHANNEL_ID = "com.raddarapp.ANDROID";

    private static final String KEY_DATA_NOTIFICATION_TOPIC = "notification_topic";
    private static final String KEY_DATA_NOTIFICATION_CONTENT = "content";

    private static final String NOTIFICATION_TOPIC_ALL_DEVICES = "allDevices";
    private static final String NOTIFICATION_TOPIC_ALL_DEVICES_DEBUG = "allDevicesDebug";
    private static final String NOTIFICATION_TOPIC_COMMENTS = "new_comment";
    private static final String NOTIFICATION_TOPIC_FAVOURITE_FOOTPRINTS = "favourite_flag";
    private static final String NOTIFICATION_TOPIC_FOOTPRINTS_DEAD = "flag_dead";
    private static final String NOTIFICATION_TOPIC_URL = "url";

    // Here comes "notification" content
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        PreferencesUtils preferencesUtils = new PreferencesUtils(this);

        if (preferencesUtils.isLoggedIn()) {
            Intent intent = new Intent();

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent = new Intent(this, MyFootprintDetailsActivity.class);
            HashMap<String, String> dataHash = new HashMap<>(remoteMessage.getData());
            intent.putExtra(KEY_DATA_NOTIFICATION_TOPIC, remoteMessage.getData().get(KEY_DATA_NOTIFICATION_TOPIC));
            intent.putExtra(KEY_DATA_NOTIFICATION_CONTENT, remoteMessage.getData().get(KEY_DATA_NOTIFICATION_CONTENT));
            sendBroadcast(intent);

            Boolean isNotificationUrl = null;
            PendingIntent pendingIntent;

            Uri customSoundUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.coin_mining);

            NotificationCompat.Builder builder;

            NotificationCompat.Style notificationStyle;

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (dataHash.size() > 0 ) {
                isNotificationUrl = dataHash.get(KEY_DATA_NOTIFICATION_TOPIC).equals(NOTIFICATION_TOPIC_URL);

                if (!isNotificationUrl) {
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                } else {
                    Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
                    NotificationUrlDto notificationUrlDto = new GsonBuilder().create().fromJson(remoteMessage.getData().get(KEY_DATA_NOTIFICATION_CONTENT), NotificationUrlDto.class);
                    notificationIntent.setData(Uri.parse(notificationUrlDto.getUrl()));
                    pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                }

                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Bitmap bitmap = null;

                if (applicationInForeground() && isNotificationUrl != null && !isNotificationUrl)  {
                    MyFootprintDto myFootprintDto = new GsonBuilder().create().fromJson(remoteMessage.getData().get(KEY_DATA_NOTIFICATION_CONTENT), MyFootprintDto.class);
                    String url = SERVER_IMAGES_API_BASE_URL_COMPLETE + myFootprintDto.getMedia();
                    bitmap = getBitmapfromUrl(url);
                }

                if (bitmap == null) {
                    notificationStyle =
                            new NotificationCompat.BigTextStyle().setBigContentTitle(
                                    remoteMessage.getNotification().getTitle()).bigText(remoteMessage.getNotification().getBody());
                } else {
                    notificationStyle =
                            new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(
                                    remoteMessage.getNotification().getTitle()).setSummaryText(remoteMessage.getNotification().getBody());
                }

                builder = new NotificationCompat.Builder(this, ANDROID_CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_logo_notification)
                        .setBadgeIconType(R.mipmap.ic_launcher)
                        .setColor(getResources().getColor(R.color.blue_dark_raddar))
                        .setContentIntent(pendingIntent)
                        .setSound(customSoundUri)
                        .setStyle(notificationStyle)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            } else {
                pendingIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);



                builder = new NotificationCompat.Builder(this, ANDROID_CHANNEL_ID)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.ic_logo_notification)
                        .setBadgeIconType(R.mipmap.ic_launcher)
                        .setColor(getResources().getColor(R.color.blue_dark_raddar))
                        .setContentIntent(pendingIntent)
                        .setContentTitle(remoteMessage.getNotification().getTitle())
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setSound(customSoundUri)
                        .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            }

            notificationManager.notify(0, builder.build());
        }
    }

    private Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean applicationInForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> services = activityManager.getRunningAppProcesses();
        boolean isActivityFound = false;

        if (services.get(0).processName.equalsIgnoreCase(getPackageName())) {
            isActivityFound = true;
        }

        return isActivityFound;
    }
}
