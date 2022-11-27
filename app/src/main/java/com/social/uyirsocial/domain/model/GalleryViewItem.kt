package com.social.uyirsocial.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GalleryViewItem(
    val imageResName: String?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class GalleryItem(
    @Json(name = "id")
    val id: Int = -1,
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "place")
    val place: String? = "",
    @Json(name = "date")
    val date: String? = "",
    @Json(name = "description")
    val description: String? = "",
    @Json(name = "image_list")
    val imageList: List<String>? = emptyList()
) : Parcelable

