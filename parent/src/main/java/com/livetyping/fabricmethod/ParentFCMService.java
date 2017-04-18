package com.livetyping.fabricmethod;


import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.CoreApplication;
import com.livetyping.core.firebase.CoreFirebaseMessagingService;
import com.livetyping.core.notification.CoreNotificationCreator;
import com.livetyping.fabricmethod.notification.ParentNotificationCreator;

public class ParentFCMService extends CoreFirebaseMessagingService {

    CoreNotificationCreator notificationCreator = new ParentNotificationCreator(CoreApplication.getAppContext());

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notificationCreator.showNotification(getApplicationContext(), remoteMessage);
    }
}
