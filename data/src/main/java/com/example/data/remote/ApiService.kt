package com.example.data.remote


import com.example.gateway.entity.*
import io.reactivex.Single
import retrofit2.Response

import retrofit2.http.*


interface ApiService {

    @GET("/v2/top-headlines?country=us&pageSize=20&page=1&apiKey=d4c4edff327c4fce883a5364f8b20f82")
    fun getArticles(): Single<Response<NewsResponse>>


}