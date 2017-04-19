package com.livetyping.fabricmethod.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.notification.CoreNotification;
import com.livetyping.fabricmethod.MainActivity;
import com.livetyping.fabricmethod.R;

import java.util.Map;

class PickUpNotification extends CoreNotification {

    static final String TYPE = "pick_up";

    PickUpNotification(RemoteMessage remoteMessage) {
        super(remoteMessage);
    }

    @Override
    protected PendingIntent configurePendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class)
                .setPackage(context.getApplicationContext().getPackageName())
                .setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CoreNotification.KEY_FROM_PUSH, getPickUpText());
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private String getPickUpText() {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_PICKUP_TIME)) {
            return PICKUP_STRING + " at " + data.containsKey(KEY_PICKUP_TIME);
        }
        return PICKUP_STRING;
    }


    @Override
    protected int largeIcon() {
        return R.drawable.ic_action_time;
    }

    private static final String KEY_PICKUP_TIME = "time";
    private static final String PICKUP_STRING = "don`t forget pick up your child";

    @Override
    protected String getNotificationTag() {
        return getClass().getName() + getPickUpText();
    }
}
