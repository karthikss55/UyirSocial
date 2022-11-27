package com.social.uyirsocial.ui.events

import com.social.uyirsocial.domain.model.GalleryItem

data class EventsViewState(
    val galleryItems: List<GalleryItem>? = listOf(),
    val isLoading: Boolean = false
)