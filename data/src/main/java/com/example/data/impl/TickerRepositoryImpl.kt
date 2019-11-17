package com.example.data.impl

import com.example.data.exception.request
import com.example.data.remote.ApiService
import com.example.domain.interactor.GetAriclesUseCase
import com.example.domain.repository.TickerRepository
import com.example.gateway.entity.*
import io.reactivex.Single

class TickerRepositoryImpl(
    private val api: ApiService
) : TickerRepository {

    override fun getArticles(params: GetAriclesUseCase.Params): Single<List<Article>> =
        request(
            api.getArticles(
                params.country,
                params.pageSize,
                params.page,
                params.apiKey
            )
        ).map { it.articles }

}