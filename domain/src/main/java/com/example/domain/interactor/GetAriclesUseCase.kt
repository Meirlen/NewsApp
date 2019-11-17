package com.example.domain.interactor

import com.example.domain.base.SingleUseCase
import com.example.domain.repository.TickerRepository
import com.example.gateway.entity.Article
import io.reactivex.Single
import javax.inject.Inject

class GetAriclesUseCase @Inject constructor(private val mRepository: TickerRepository) :
    SingleUseCase<List<Article>, GetAriclesUseCase.Params>() {

    override fun buildUseCaseSingle(params: Params): Single<List<Article>> =
        mRepository.getArticles(params)


    class Params(
        var country: String,
        var pageSize: String,
        var apiKey: String,
        var page: Int
    )

}