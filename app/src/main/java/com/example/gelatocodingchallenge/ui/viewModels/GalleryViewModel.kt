package com.example.gelatocodingchallenge.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gelatocodingchallenge.network.ApiService
import com.example.gelatocodingchallenge.network.GalleryDataSource


enum class PicsumApiStatus { LOADING, ERROR, DONE }

class GalleryViewModel(
    private val api: ApiService
) : ViewModel() {
    val photos =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            GalleryDataSource(api)
        }).flow.cachedIn(viewModelScope)
}