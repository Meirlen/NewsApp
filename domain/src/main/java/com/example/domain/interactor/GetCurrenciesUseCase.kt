package com.example.domain.interactor

import com.example.domain.base.SingleUseCase
import com.example.domain.repository.TickerRepository
import com.example.gateway.entity.Article
import io.reactivex.Single
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(private val mRepository: TickerRepository) :
    SingleUseCase<List<Article>, GetCurrenciesUseCase.Params>() {

    override fun buildUseCaseSingle(params: Params): Single<List<Article>> =
        mRepository.getArticles()


    class Params(var page: String)

}