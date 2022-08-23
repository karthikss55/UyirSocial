package com.social.uyirsocial

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailabilityLight
import java.io.IOException


typealias SimpleResource = Resource<Unit>

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
}

fun Context.getDrawableByName(resName: String): Drawable? {
    val resourceId = resources.getIdentifier(
        resName, "drawable",
        packageName
    );
    return ContextCompat.getDrawable(this, resourceId)
}

fun Context.getDrawableIdByName(resName: String): Int? {
    return resources.getIdentifier(
        resName, "drawable",
        packageName
    );

}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
 inline fun <T> justTryOrNull(block: () -> T): T?{
     return try {
         block()
     } catch (ex: Exception){
         null
     }
 }

fun List<String>.mapToSliderItems(context: Context): List<SlideModel> {
    return map {
        SlideModel(context.getDrawableIdByName(it), ScaleTypes.FIT)
    }
}

private fun Context.checkGooglePlayServices(): Boolean {
    // 1
    val status = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this)
    // 2
    return if (status != ConnectionResult.SUCCESS) {
        Log.e("TAG", "Error")
        // ask user to update google play services and manage the error.
        false
    } else {
        // 3
        Log.i("TAG", "Google play services updated")
        true
    }
}

fun Context.createDefaultNotificationChannel(channelId: String, channelName: String){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel =
            NotificationChannel(channelId, channelName, importance)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        mNotificationManager.createNotificationChannel(notificationChannel)
    }
}
