package com.example.data.remote


import com.example.gateway.entity.*
import io.reactivex.Single
import retrofit2.Response

import retrofit2.http.*


interface ApiService {

    @GET("/v2/top-headlines")
    fun getArticles(
        @Query("country") country: String,
        @Query("pageSize") pageSize: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): Single<Response<NewsResponse>>

}