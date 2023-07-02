package com.shellwoo.kinoguru.feature.notification

import android.app.Notification
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class RemoteNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val remoteNotification = message.notification
        if (remoteNotification != null) {
            val localNotification = createLocalNotification(remoteNotification)
            NotificationManagerCompat.from(this).notify(0, localNotification)
        }
    }

    private fun createLocalNotification(remoteNotification: RemoteMessage.Notification): Notification =
        NotificationCompat.Builder(this, getString(R.string.channel_remote_id))
            .setSmallIcon(com.shellwoo.kinoguru.design.resource.R.mipmap.ic_launcher_round)
            .setContentTitle(remoteNotification.title)
            .setContentText(remoteNotification.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
}