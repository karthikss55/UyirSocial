package com.social.uyirsocial.ui.gallery

import androidx.lifecycle.*
import com.social.uyirsocial.Resource
import com.social.uyirsocial.data.local.repository.MainRepositoryImpl
import com.social.uyirsocial.domain.usecase.GalleryFetchUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GalleryViewModel() : ViewModel() {

    val galleryItemsUseCase = GalleryFetchUseCase(MainRepositoryImpl())

    private val galleryViewState = MutableLiveData<GalleryViewState>()
    val _galleryViewState: LiveData<GalleryViewState> = galleryViewState

    fun fetchGalleryItems(){
        viewModelScope.launch {
            galleryItemsUseCase().onEach {result ->
                when(result){
                    is Resource.Error -> TODO()
                    is Resource.Loading -> galleryViewState.value = GalleryViewState(null, true)
                    is Resource.Success -> galleryViewState.value = GalleryViewState(result.data, false)
                }
            }.launchIn(this)
        }
    }


}