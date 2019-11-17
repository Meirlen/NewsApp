package kz.ticker.android.di

import com.example.data.impl.*

import com.example.domain.interactor.GetAriclesUseCase

import com.example.domain.repository.*

import kz.ticker.android.ui.NewsViewModel

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val archModule = module {
    module("repository") {

        factory {
            TickerRepositoryImpl(get()) as TickerRepository
        }
        factory {
            GetAriclesUseCase(get())
        }

        module("viewModel") {

            viewModel {
                NewsViewModel(get())
            }

        }
    }

}


