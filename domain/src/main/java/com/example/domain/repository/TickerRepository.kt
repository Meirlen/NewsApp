package com.example.domain.repository
import com.example.gateway.entity.*
import io.reactivex.Single

interface TickerRepository {
    fun getCurrenciesFromRemote(): Single<List<Currency>>

}