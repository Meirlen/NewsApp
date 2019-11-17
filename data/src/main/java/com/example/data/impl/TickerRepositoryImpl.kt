package com.example.data.impl

import com.example.data.exception.request
import com.example.data.remote.ApiService
import com.example.domain.repository.TickerRepository
import com.example.gateway.entity.*
import io.reactivex.Single

class TickerRepositoryImpl(
    private val api: ApiService
) : TickerRepository {



    override fun getCurrenciesFromRemote(): Single<List<Currency>> = request(api.getCurrencies())


}