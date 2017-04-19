package com.livetyping.core.notification;


import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.R;

import java.util.Map;
import java.util.Set;

public abstract class CoreNotificationCreator {

    private static final String KEY_NOTIFICATION_TAG = "CoreNotificationCreator.TagKey";
    private static final String DEFAULT_TAG = "CoreNotificationCreator.DefaultTag";

    private static final String KEY_TYPE = "type";

    private NotificationManager notificationManager;

    private static final
    @DrawableRes
    int SMALL_ICON_RES_ID = R.drawable.ic_notification_small;


    public CoreNotificationCreator(Context context) {
        notificationManager = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
    }

    public void showNotification(Context context, RemoteMessage remoteMessage) {
        String notificationType = getNotificationType(remoteMessage);
        CoreNotification notification = factoryMethod(notificationType, remoteMessage);
        if (notification != null) {
            NotificationCompat.Builder builder = builderFromPushNotification(context, notification);
            notify(builder);
        }
    }

    @Nullable
    protected abstract CoreNotification factoryMethod(String messageType, RemoteMessage remoteMessage);

    private String getNotificationTag(NotificationCompat.Builder builder) {
        Bundle extras = builder.getExtras();
        if (extras.containsKey(KEY_NOTIFICATION_TAG)) {
            return extras.getString(KEY_NOTIFICATION_TAG);
        } else {
            return DEFAULT_TAG;
        }
    }

    private final static int DEFAULT_NOTIFICATION_ID = 15;

    private void notify(@NonNull NotificationCompat.Builder builder) {
        final String notificationTag = getNotificationTag(builder);
        notificationManager.cancel(notificationTag, DEFAULT_NOTIFICATION_ID);
        notificationManager.notify(notificationTag, DEFAULT_NOTIFICATION_ID, builder.build());
    }

    private String getNotificationType(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        Set<String> dataKeySet = data.keySet();
        for (String key : dataKeySet) {
            if (key.trim().equals(KEY_TYPE)) {
                return data.get(key);
            }
        }
        return "";
    }

    protected NotificationCompat.Builder builderFromPushNotification(Context context, CoreNotification notification) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), notification.largeIcon());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(SMALL_ICON_RES_ID)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getContent())
                .setLargeIcon(largeIcon);
        builder.getExtras().putString(KEY_NOTIFICATION_TAG, notification.getNotificationTag());
        builder.setContentIntent(notification.configurePendingIntent(context));
        return builder;
    }
}

