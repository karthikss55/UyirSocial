package com.social.uyirsocial.ui.services

import com.social.uyirsocial.domain.model.ServicesViewItem

data class ServicesViewState(
    val items: List<ServicesViewItem>? = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)