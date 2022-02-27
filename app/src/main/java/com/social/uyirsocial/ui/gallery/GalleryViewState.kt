package com.social.uyirsocial.ui.gallery

import com.social.uyirsocial.domain.model.GalleryItem

data class GalleryViewState(
    val galleryItems: List<GalleryItem>? = listOf(),
    val isLoading: Boolean = false
)