package com.social.uyirsocial.notification

import com.social.uyirsocial.NotificationUtils.sendNotification
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.social.uyirsocial.UyirApplication

class UyirMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d("Push Notification", "token---> $token")
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleMessage(message)
    }

    private fun handleMessage(message: RemoteMessage) {
        message.notification?.body?.let {
            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.sendNotification(message,it, UyirApplication.application)
        }
    }
}