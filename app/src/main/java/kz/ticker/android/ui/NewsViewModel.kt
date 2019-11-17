package kz.ticker.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.GetAriclesUseCase
import com.example.gateway.entity.*
import kz.ticker.android.base.Constant.DEFAULT_API_KEY
import kz.ticker.android.base.Constant.DEFAULT_COUNTRY
import kz.ticker.android.base.Constant.DEFAULT_PER_PAGE_COUNT
import kz.ticker.android.vo.Resource


open class NewsViewModel(
    private val getAriclesUseCase: GetAriclesUseCase
) : ViewModel() {


    var articleLiveData: MutableLiveData<Resource<List<Article>>> = MutableLiveData()


    fun getArticles(page: Int) {

        articleLiveData.value = Resource.loading(null)
        getAriclesUseCase.execute(
            { currencies ->

                articleLiveData.value = Resource.success(currencies)
            },
            {
                articleLiveData.value = Resource.error(error = it)

            },
            GetAriclesUseCase.Params(
                DEFAULT_COUNTRY,
                DEFAULT_PER_PAGE_COUNT,
                DEFAULT_API_KEY,
                page
            )
        )
    }

}

