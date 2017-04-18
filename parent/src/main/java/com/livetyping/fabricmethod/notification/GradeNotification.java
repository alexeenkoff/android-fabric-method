package com.livetyping.fabricmethod.notification;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.notification.CoreNotification;
import com.livetyping.fabricmethod.MainActivity;
import com.livetyping.fabricmethod.R;

import java.util.Map;

public class GradeNotification extends CoreNotification {

    static final String TYPE = "grade";

    GradeNotification(RemoteMessage remoteMessage) {
        super(remoteMessage);
    }

    @Override
    protected PendingIntent configurePendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class)
                .setPackage(context.getApplicationContext().getPackageName())
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(CoreNotification.KEY_FROM_PUSH, getGradeText());
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static final String KEY_GRADE_VALUE = "grade_value";
    private static final String GRADE_TEXT = "your child get new grade";

    private String getGradeText() {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey(KEY_GRADE_VALUE)) {
            return GRADE_TEXT + " : " + data.get(KEY_GRADE_VALUE);
        }
        return GRADE_TEXT;
    }

    @Override
    protected int largeIcon() {
        return R.drawable.ic_star;
    }

    @Override
    protected String getNotificationTag() {
        return getClass().getName();
    }
}
