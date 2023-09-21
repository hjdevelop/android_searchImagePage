package com.example.android_searchimagepage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageAPI {
    @GET("v2/search/image")
    fun imageSearch(
        @Header("Authorization") apiKey : String?,
        @Query("query") query : String?,
        @Query("sort") sort : String?,
        @Query("page") page : Int,
        @Query("size") size : Int
    ) : Call<SearchDataModel?>?
}