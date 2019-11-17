package kz.ticker.android.ui.news

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.example.gateway.entity.Article
import kotlinx.android.synthetic.main.item_article.view.*
import kz.ticker.android.R
import kz.ticker.android.base.Constant.PLACEHOLDER_IMG_URL
import kz.ticker.android.ext.loadImage


class NewsItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        View.inflate(context, R.layout.item_article, this)
    }

    fun setData(article: Article) {
        titleTv.text = article.title
        articleIv.loadImage(article.urlToImage ?: PLACEHOLDER_IMG_URL)
    }

}