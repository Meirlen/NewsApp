package com.example.domain.repository
import com.example.domain.interactor.GetAriclesUseCase
import com.example.gateway.entity.*
import io.reactivex.Single

interface TickerRepository {
    fun getArticles(params: GetAriclesUseCase.Params): Single<List<Article>>
}