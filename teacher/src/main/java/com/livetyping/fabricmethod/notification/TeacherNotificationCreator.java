package com.livetyping.fabricmethod.notification;


import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.notification.CoreNotification;
import com.livetyping.core.notification.CoreNotificationCreator;

public class TeacherNotificationCreator extends CoreNotificationCreator {

    public TeacherNotificationCreator(Context context) {
        super(context);
    }

    @Nullable
    @Override
    protected CoreNotification factoryMethod(String messageType, RemoteMessage remoteMessage) {
        switch (messageType) {
            case NewChildNotification.TYPE:
                return new NewChildNotification(remoteMessage);
            case BirthdayNotification.TYPE:
                return new BirthdayNotification(remoteMessage);
        }
        return null;
    }
}
