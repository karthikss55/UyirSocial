package com.social.uyirsocial

import android.app.Application

class UyirApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        createDefaultNotificationChannel(DEFAULT_NOTIFICATION_CHANNEL,DEFAULT_NOTIFICATION_CHANNEL)
    }

    companion object {
        const val DEFAULT_NOTIFICATION_CHANNEL = "Uyir common Notifications"
        lateinit var application: UyirApplication
    }

}