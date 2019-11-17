package kz.ticker.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.GetCurrenciesUseCase
import com.example.gateway.entity.*
import kz.ticker.android.vo.Resource


open class TicketViewModel(
    private val getCurrenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {


    var currencyLiveData: MutableLiveData<Resource<List<Currency>>> = MutableLiveData()


    fun getCurrencies() {

        currencyLiveData.value = Resource.loading(null)
        getCurrenciesUseCase.execute(
            { currencies ->

                currencyLiveData.value = Resource.success(currencies)
            },
            {
                currencyLiveData.value = Resource.error(error = it)

            }, GetCurrenciesUseCase.Params("1")
        )
    }


}

