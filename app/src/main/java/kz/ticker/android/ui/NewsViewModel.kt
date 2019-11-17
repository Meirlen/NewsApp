package kz.ticker.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.GetCurrenciesUseCase
import com.example.gateway.entity.*
import kz.ticker.android.vo.Resource


open class NewsViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {


    var articleLiveData: MutableLiveData<Resource<List<Article>>> = MutableLiveData()


    fun getCurrencies() {

        articleLiveData.value = Resource.loading(null)
        getCurrenciesUseCase.execute(
            { currencies ->

                articleLiveData.value = Resource.success(currencies)
            },
            {
                articleLiveData.value = Resource.error(error = it)

            }, GetCurrenciesUseCase.Params("1")
        )
    }


}

