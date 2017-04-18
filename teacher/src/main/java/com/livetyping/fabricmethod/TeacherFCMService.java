package com.livetyping.fabricmethod;


import com.google.firebase.messaging.RemoteMessage;
import com.livetyping.core.CoreFirebaseMessagingService;

public class TeacherFCMService extends CoreFirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
    }
}
