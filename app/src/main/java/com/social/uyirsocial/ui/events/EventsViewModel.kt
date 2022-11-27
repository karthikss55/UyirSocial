package com.social.uyirsocial.ui.events

import androidx.lifecycle.*
import com.social.uyirsocial.Resource
import com.social.uyirsocial.data.local.repository.MainRepositoryImpl
import com.social.uyirsocial.domain.usecase.GalleryFetchUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class EventsViewModel() : ViewModel() {

    val galleryItemsUseCase = GalleryFetchUseCase(MainRepositoryImpl())

    private val galleryViewState = MutableLiveData<EventsViewState>()
    val _galleryViewState: LiveData<EventsViewState> = galleryViewState

    fun fetchGalleryItems(){
        viewModelScope.launch {
            galleryItemsUseCase().onEach {result ->
                when(result){
                    is Resource.Error -> TODO()
                    is Resource.Loading -> galleryViewState.value = EventsViewState(null, true)
                    is Resource.Success -> galleryViewState.value = EventsViewState(result.data, false)
                }
            }.launchIn(this)
        }
    }


}