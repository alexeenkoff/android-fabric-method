package com.livetyping.fabricmethod;


import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.CoreApplication;
import com.livetyping.core.firebase.CoreFirebaseMessagingService;
import com.livetyping.core.notification.CoreNotificationCreator;
import com.livetyping.fabricmethod.notification.TeacherNotificationCreator;

public class TeacherFCMService extends CoreFirebaseMessagingService {

    CoreNotificationCreator notificationCreator = new TeacherNotificationCreator(CoreApplication.getAppContext());

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notificationCreator.showNotification(getApplicationContext(), remoteMessage);
    }
}
