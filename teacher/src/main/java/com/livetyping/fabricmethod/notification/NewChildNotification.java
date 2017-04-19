package com.livetyping.fabricmethod.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.notification.CoreNotification;
import com.livetyping.core.notification.CoreNotificationCreator;
import com.livetyping.fabricmethod.MainActivity;
import com.livetyping.fabricmethod.R;

import java.util.Map;

class NewChildNotification extends CoreNotification {

    static final String TYPE = "add_child";

    private static final String KEY_CHILD_NAME = "child_name";

    NewChildNotification(RemoteMessage remoteMessage) {
        super(remoteMessage);
    }

    @Override
    protected PendingIntent configurePendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class)
                .setPackage(context.getApplicationContext().getPackageName())
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CoreNotification.KEY_FROM_PUSH, getAddChildInfo());
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected int largeIcon() {
        return R.drawable.ic_add_child;
    }

    @Override
    protected String getNotificationTag() {
        return getClass().getName() + getChildName();
    }

    private String getChildName() {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_CHILD_NAME)) {
            return data.get(KEY_CHILD_NAME);
        }
        return STRING_EMPTY;
    }

    private String getAddChildInfo() {
        return "New child " + getChildName() + " was added to your group";
    }
}
