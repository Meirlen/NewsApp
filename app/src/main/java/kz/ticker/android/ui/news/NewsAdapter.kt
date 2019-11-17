package kz.ticker.android.ui.news

import android.content.Context
import android.view.View
import com.example.gateway.entity.Article
import kz.ticker.android.base.BaseRecyclerAdapter
import kz.ticker.android.base.OnItemClickListener


open class NewsAdapter(
    dataList: List<Article>
) :
    BaseRecyclerAdapter<Article>(dataList) {

    init {
        withHeader = true
        withFooter = true
    }

    private var isLoading = true

    override fun getItemView(context: Context, viewType: Int): View {
        return NewsItem(context)
    }

    override fun getHeaderView(context: Context): View? {
        return HeaderItem(context)
    }

    override fun getFooterView(context: Context): View? {
        return FooterItem(context)
    }

    override fun bindData(itemView: View, data: Article, position: Int) {
        if (itemView is NewsItem) {
            itemView.setData(data)
        }
    }

    override fun bindHeaderData(itemView: View, position: Int) {
        if (itemView is HeaderItem) {
            itemView.setListSize(dataList.size)
        }
    }

    override fun bindFooterData(itemView: View, position: Int) {
        if (itemView is FooterItem) {
            itemView.loading(isLoading)
        }
    }

    fun hideLoading(){
        notifyItemChanged(dataList.size - 1)
        isLoading = false
    }

}