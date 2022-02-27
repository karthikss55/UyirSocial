package com.social.uyirsocial

import android.app.Application

class UyirApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: UyirApplication
    }

}