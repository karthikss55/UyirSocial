package com.social.uyirsocial

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import java.io.IOException
import java.lang.Exception

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
 inline fun<T> justTryOrNull(block:() -> T): T?{
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