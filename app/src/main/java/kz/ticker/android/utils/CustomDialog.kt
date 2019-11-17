package kz.ticker.android.utils

import android.app.AlertDialog
import android.content.Context

class CustomDialog(context: Context, callBack: () -> Unit) : AlertDialog.Builder(context) {

    init {
        setMessage("Не удалось загрузить новости")
        setPositiveButton("Повторить") { dialog, _ ->
            callBack.invoke()
        }
    }

}