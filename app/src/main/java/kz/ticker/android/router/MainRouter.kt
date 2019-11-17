package kz.ticker.android.router

import android.content.Context
import com.example.gateway.entity.Article
import kz.ticker.android.ui.detail.DetailActivity


class MainRouter {

    fun openDetail(context: Context?, article: Article) {
        context?.let {
            it.startActivity(DetailActivity.getStartIntent(it,article))
        }
    }
}