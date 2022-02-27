package com.social.uyirsocial

import android.content.Context
import com.social.uyirsocial.domain.model.GalleryItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiParser {

    fun parseGalleryJson(context: Context, ): List<GalleryItem>? {
       return justTryOrNull {
            val jsonFile = getJsonDataFromAsset(context, "gallery_json")
            val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val listType = Types.newParameterizedType(List::class.java, GalleryItem::class.java)
            val adapter: JsonAdapter<List<GalleryItem>> = moshi.adapter(listType)
            adapter.fromJson(jsonFile)
        }
    }
}