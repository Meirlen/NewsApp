package kz.ticker.android.ext


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.TextView
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*


val Context.networkInfo: NetworkInfo? get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo




