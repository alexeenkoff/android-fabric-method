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

    public CoreNotification(RemoteMessage remoteMessage) {
        this.remoteMessage = remoteMessage;
    }

    protected String getTitleFromMessage() {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_TITLE)) {
            return data.get(KEY_TITLE);
        } else {
            return STRING_EMPTY;
        }
    }

    protected String getContentFromMessage() {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_CONTENT)) {
            return data.get(KEY_CONTENT);
        } else {
            return STRING_EMPTY;
        }
    }

    public String getTitle() {
        return getTitleFromMessage();
    }

    public String getContent() {
        return getContentFromMessage();
    }

    protected abstract PendingIntent configurePendingIntent(Context context);

    protected abstract
    @DrawableRes
    int largeIcon();

    protected abstract String getNotificationTag();

}
