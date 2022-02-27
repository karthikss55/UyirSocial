package com.social.uyirsocial.data.local.repository

import com.social.uyirsocial.MoshiParser
import com.social.uyirsocial.Resource
import com.social.uyirsocial.UyirApplication
import com.social.uyirsocial.domain.model.GalleryItem
import com.social.uyirsocial.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl : IMainRepository {
    override fun getGalleryItems(): Flow<Resource<List<GalleryItem>>> =
        flow {
            emit(Resource.Loading())
            MoshiParser.parseGalleryJson(UyirApplication.application)?.let { galleryResponse ->
                emit(Resource.Success(galleryResponse))
            }
        }

}