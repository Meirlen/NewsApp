package kz.ticker.android.ui

import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.exception.handleError
import com.example.gateway.entity.Article
import kotlinx.android.synthetic.main.fragment_news.*
import kz.ticker.android.R
import kz.ticker.android.base.Constant.LAST_PAGE
import kz.ticker.android.base.Constant.START_PAGE
import kz.ticker.android.vo.Status
import kz.ticker.android.ext.*
import kz.ticker.android.ui.news.NewsAdapter
import kz.ticker.android.utils.CustomDialog
import kz.ticker.android.utils.PaginationScrollListener
import org.koin.android.viewmodel.ext.android.viewModel


class NewsFragment : androidx.fragment.app.Fragment() {


    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    private val mViewModel: NewsViewModel by viewModel()
    private lateinit var errorDialog: CustomDialog
    private lateinit var newsAdapter: NewsAdapter
    private var articleList = mutableListOf<Article>()
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var currentPage = START_PAGE


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUpRecyclerView()
        setUpDialog()
    }

    private fun setUpDialog() {
        errorDialog = CustomDialog(context!!) {
            loadData(currentPage)
        }
    }


    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter(articleList)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = newsAdapter
        addScrollListener(layoutManager)
    }


    private fun addScrollListener(layoutManager: LinearLayoutManager) {
        recyclerView?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                loadData(currentPage)
                currentPage++

            }
        })
    }

    /**
     * load data from remote
     */
    private fun loadData(page: Int) {
        if (currentPage > LAST_PAGE) {
            showError(getString(R.string.last_page_error_text))
            newsAdapter.hideLoading()
            return
        }
        mViewModel.getArticles(page)
    }

    /**
     * Подписка на LiveData
     * @see NewsViewModel
     */

    private fun setUpObservers() {

        mViewModel.articleLiveData.observe(this, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    showProgress()
                }
                Status.SUCCESS -> {
                    it.data?.let {
                        showResult(it)
                        isLoading = false
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

    private fun showResult(list: List<Article>) {
        if (list.isEmpty()) {
            newsAdapter.hideLoading()
        }
        articleList.addAll(list)
        newsAdapter.notifyDataSetChanged()
    }


    private fun showError(throwable: Throwable) {
        handleError(throwable) { title, desc ->
            showError(title + desc)
            showErrorDialog()
        }
    }

    private fun showError(error: String) {
        snacbar(root_layout, error)
        showErrorDialog()
    }

    private fun showErrorDialog() {
        if (articleList.isEmpty()) {
            errorDialog.show()
        }
    }


    private fun hideProgress() {
        progressBar.hide()
    }

    private fun showProgress() {
        progressBar.show()
    }
}