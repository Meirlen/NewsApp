package kz.ticker.android.ext


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*


val Context.networkInfo: NetworkInfo? get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo


fun ImageView.loadImage(url: String) {
    Glide
        .with(this.context)
        .load(url.trim())
        .into(this)
}

fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun showGroupViews(vararg view: View) {
    view.forEach {
        it.show()
    }
}

fun hideGroupViews(vararg view: View) {
    view.forEach {
        it.hide()
    }
}