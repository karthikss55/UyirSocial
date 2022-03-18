package com.social.uyirsocial.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.social.uyirsocial.Resource
import com.social.uyirsocial.data.local.repository.MainRepositoryImpl
import com.social.uyirsocial.domain.usecase.ServicesFetchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ServicesViewModel : ViewModel() {
    val serviceUseCase = ServicesFetchUseCase(MainRepositoryImpl())
    private val servicesViewState = MutableLiveData<ServicesViewState>()
    val _servicesViewState : LiveData<ServicesViewState> = servicesViewState

    fun fetchOurServicesData(){
        viewModelScope.launch {
            serviceUseCase().onEach {
                when(it){
                    is Resource.Error -> servicesViewState.value = ServicesViewState(items = listOf(), isLoading = false,isError = true)
                    is Resource.Loading -> servicesViewState.value = ServicesViewState(items = listOf(), isLoading = true,isError = false)
                    is Resource.Success -> servicesViewState.value = ServicesViewState(items = it.data, isLoading = false,isError = false)
                }
            }.launchIn(this)
        }
    }
}