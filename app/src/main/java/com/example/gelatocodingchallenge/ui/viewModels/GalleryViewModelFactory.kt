package com.example.gelatocodingchallenge.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gelatocodingchallenge.network.ApiService

class GalleryViewModelFactory (
    private val api: ApiService
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(api) as T
    }
}