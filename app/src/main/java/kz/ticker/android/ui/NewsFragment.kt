package kz.ticker.android.ui

import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.data.exception.handleError
import com.example.gateway.entity.Article
import kotlinx.android.synthetic.main.fragment_ticket.*
import kz.ticker.android.R
import kz.ticker.android.base.OnItemClickListener
import kz.ticker.android.vo.Status
import kz.ticker.android.ext.*
import kz.ticker.android.router.MainRouter
import kz.ticker.android.ui.news.NewsAdapter
import kz.ticker.android.utils.NetworkHandler
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject


class NewsFragment : androidx.fragment.app.Fragment(),
    androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener {


    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    private val mViewModel: NewsViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter
    private var dataList = mutableListOf<Article>()
    private val router by inject<MainRouter>()
    private val networkHandler by inject<NetworkHandler>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setUpRecyclerView()
        setListeners()
        loadData()
    }


    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter(dataList, object : OnItemClickListener {
            override fun onItemClicked(position: Int) {
                router.openDetail(context, dataList[position])
            }
        })
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recyclerView.adapter = newsAdapter
    }


    private fun loadData() {
        if (!networkHandler.isConnected) {
            showError(getString(R.string.no_internet_connect_error_message))
            return
        }
        mViewModel.getCurrencies()
    }

    private fun setListeners() {
        swipeRefreshLayout.setOnRefreshListener(this)
    }

    /**
     * Подписка на LiveData
     * @see NewsViewModel
     */

    private fun setObservers() {


        mViewModel.articleLiveData.observe(this, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        showCurrencyList(it)
                    }
                    hideProgress()

                }
                Status.ERROR -> {
                    it.error?.let {
                        showError(it)
                    }
                    hideProgress()
                }
            }
        })

    }

    private fun showCurrencyList(list: List<Article>) {
        dataList.clear()
        dataList.addAll(list)
        newsAdapter.notifyDataSetChanged()
    }


    private fun showError(throwable: Throwable) {
        handleError(throwable) { title, desc ->
            showError(title + desc)
        }

    }

    private fun showError(error: String) {
        snacbar(root_layout, error)
    }

    override fun onRefresh() {
        loadData()
    }

    private fun hideProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun showProgress() {
        swipeRefreshLayout.isRefreshing = true

    }
}