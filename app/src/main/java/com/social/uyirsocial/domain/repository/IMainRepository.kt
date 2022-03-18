package com.social.uyirsocial.domain.repository

import com.social.uyirsocial.Resource
import com.social.uyirsocial.domain.model.GalleryItem
import com.social.uyirsocial.domain.model.ServicesViewItem
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getGalleryItems(): Flow<Resource<List<GalleryItem>>>
    fun getServiceItems(): Flow<Resource<List<ServicesViewItem>>>
}