package kz.ticker.android.ui.news

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_loading.view.*
import kz.ticker.android.R
import kz.ticker.android.ext.setVisibility
import kz.ticker.android.ext.show


class FooterItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        View.inflate(context, R.layout.item_loading, this)
    }

    fun loading(isLoading:Boolean) {
        progressBar.setVisibility(isLoading)
    }

}