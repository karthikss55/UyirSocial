package com.social.uyirsocial.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics

import com.social.uyirsocial.databinding.SplashActivityBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var splashActivityBinding: SplashActivityBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashActivityBinding = SplashActivityBinding.inflate(layoutInflater)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setContentView(splashActivityBinding.root)
        startFlow()
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, bundleOf("test" to "1234"))
    }

    private fun startFlow() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}