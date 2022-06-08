package com.example.gelatocodingchallenge.network

import com.example.gelatocodingchallenge.models.GalleryModel
import com.example.gelatocodingchallenge.models.PicsumResponse

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://picsum.photos/v2/"


interface ApiService {

    @GET("list")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("size") size: Int = 10
    ): List<GalleryModel>


    companion object {

        operator fun invoke(): ApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}