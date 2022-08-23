package com.social.uyirsocial

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.social.uyirsocial.ui.MainActivity
import java.util.concurrent.atomic.AtomicInteger

object NotificationUtils {
    const val NOTIFICATION_ID = 0

    fun NotificationManager.sendNotification(
        message: RemoteMessage,
        messageBody: String,
        applicationContext: Context
    ) {
        val contentIntent = Intent(applicationContext, MainActivity::class.java)
        val contentPendingIntent = PendingIntent.getActivity(
            applicationContext,
            NOTIFICATION_ID,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        // Build the notification
        val builder = NotificationCompat.Builder(
            applicationContext,
            UyirApplication.DEFAULT_NOTIFICATION_CHANNEL
        ).run {
            setSmallIcon(R.drawable.splash_logo)
            setContentTitle(applicationContext.getString(R.string.app_name))
            setContentText(messageBody)
            setContentIntent(contentPendingIntent)
            setStyle(getNotificationStyle(applicationContext, message))
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
        }
        // Deliver the notification
        notify(getID(), builder.build())
    }

    /**
     * Cancels all notifications.
     *
     */
    fun NotificationManager.cancelNotifications() {
        cancelAll()
    }

    private fun getNotificationStyle(
        context: Context,
        message: RemoteMessage
    ): NotificationCompat.Style {
        message.data.apply {
            val value = get("bigPictureStyle")?.toBoolean()
            return if (value.orFalse()) {
               NotificationCompat.BigPictureStyle()
                    .bigPicture(getGenericNotificationBigPictureBitmap(context))
                    .setBigContentTitle(context.getString(R.string.app_name))

            } else {
                NotificationCompat.BigTextStyle()
                    .bigText(message.notification?.body)

            }
        }
    }

    private fun getGenericNotificationBigPictureBitmap(context: Context) =
        BitmapFactory.decodeResource(context.resources, R.drawable.splash_logo)


    private val c = AtomicInteger(0)
    fun getID(): Int {
        return c.incrementAndGet()
    }


}