package com.shellwoo.kinoguru.feature.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class NotificationChannelFactory(private val context: Context) {

    fun create() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createRemoteChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createRemoteChannel() {
        val channelId = context.getString(R.string.channel_remote_id)
        val name = context.getString(R.string.channel_remote_name)
        val description = context.getString(R.string.channel_remote_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance)
            .apply { this.description = description }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}