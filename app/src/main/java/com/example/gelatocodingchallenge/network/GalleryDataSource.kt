package com.example.gelatocodingchallenge.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gelatocodingchallenge.models.GalleryModel
import com.example.gelatocodingchallenge.models.PicsumResponse


class GalleryDataSource(private val api: ApiService) : PagingSource<Int, GalleryModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getPhotos(nextPageNumber)
            val picsumResponse= PicsumResponse(response)

            LoadResult.Page(

                data = picsumResponse.data,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 999) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}


