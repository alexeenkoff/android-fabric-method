package com.livetyping.fabricmethod.notification;


import android.content.Context;
import android.support.annotation.Nullable;

import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.notification.CoreNotification;
import com.livetyping.core.notification.CoreNotificationCreator;

public class ParentNotificationCreator extends CoreNotificationCreator {

    public ParentNotificationCreator(Context context) {
        super(context);
    }

    @Nullable
    @Override
    protected CoreNotification factoryMethod(String messageType, RemoteMessage remoteMessage) {
        switch (messageType) {
            case PickUpNotification.TYPE:
                return new PickUpNotification(remoteMessage);
        }
        return null;
    }
}
