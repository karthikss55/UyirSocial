package com.social.uyirsocial.domain.usecase

import com.social.uyirsocial.Resource
import com.social.uyirsocial.data.local.repository.MainRepositoryImpl
import com.social.uyirsocial.domain.model.ServicesViewItem
import kotlinx.coroutines.flow.Flow

class ServicesFetchUseCase(
    private val repository: MainRepositoryImpl
    ) {

    operator fun invoke(): Flow<Resource<List<ServicesViewItem>>> {
        return repository.getServiceItems()
    }
}