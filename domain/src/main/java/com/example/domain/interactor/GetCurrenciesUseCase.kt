package com.example.domain.interactor

import com.example.domain.base.SingleUseCase
import com.example.domain.repository.TickerRepository
import com.example.gateway.entity.Currency
import io.reactivex.Single
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(private val mRepository: TickerRepository) :
    SingleUseCase<List<Currency>, GetCurrenciesUseCase.Params>() {

    override fun buildUseCaseSingle(params: Params): Single<List<Currency>> =
        mRepository.getCurrenciesFromRemote()


    class Params(var page: String)

}