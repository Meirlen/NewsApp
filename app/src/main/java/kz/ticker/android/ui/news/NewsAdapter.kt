package kz.ticker.android.ui.news

import android.content.Context
import android.view.View
import com.example.gateway.entity.Article
import kz.ticker.android.base.BaseRecyclerAdapter
import kz.ticker.android.base.OnItemClickListener


open class NewsAdapter(
    dataList: List<Article>, onItemClickListener: OnItemClickListener
) :
    BaseRecyclerAdapter<Article>(dataList,onItemClickListener) {

    init {
        withHeader = true
    }

    override fun getItemView(context: Context, viewType: Int): View {
        return NewsItem(context)
    }

    override fun getHeaderView(context: Context): View? {
        return HeaderItem(context)
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

}