package com.livetyping.core.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.support.annotation.DrawableRes;

import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public abstract class CoreNotification {
    public static final String KEY_FROM_PUSH = "CoreNotification.FromNotification";

    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "body";

    protected static final String STRING_EMPTY = "";

    protected RemoteMessage remoteMessage;
    private String title;
    private String content;

    public CoreNotification(RemoteMessage remoteMessage) {
        this.remoteMessage = remoteMessage;
        setTitle(remoteMessage);
        setContent(remoteMessage);
    }

    protected void setTitle(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_TITLE)) {
            title = data.get(KEY_TITLE);
        } else {
            title = STRING_EMPTY;
        }
    }

    protected void setContent(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_CONTENT)) {
            content = data.get(KEY_CONTENT);
        } else {
            content = STRING_EMPTY;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    protected abstract PendingIntent configurePendingIntent(Context context);

    protected abstract
    @DrawableRes
    int largeIcon();

    protected abstract String getNotificationTag();

}
