package wcecil.firstapp

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import wcecil.firstapp.constants.Constants

class NotificationListener extends NotificationListenerService {

    private void sendBroadcast(StatusBarNotification sbn, boolean isDelete) {
        Intent new_intent = new Intent();
        new_intent.setAction(Constants.ACTION_STRING_ACTIVITY);
        new_intent.putExtra(Constants.SBN, sbn)
        new_intent.putExtra(Constants.NOTIFICATION_DELETE, isDelete)
        sendBroadcast(new_intent);
    }

    @Override
    void onNotificationPosted(StatusBarNotification sbn) {
        println "TestPost: ${sbn}"

        sendBroadcast(sbn, false)
        super.onNotificationPosted(sbn)

    }

    @Override
    void onNotificationRemoved(StatusBarNotification sbn) {
        println "TestRemove: ${sbn}"

        sendBroadcast(sbn, true)
        super.onNotificationRemoved(sbn)
    }
}