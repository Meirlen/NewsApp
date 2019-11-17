
package kz.ticker.android.base


import android.graphics.PorterDuff

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


import kz.ticker.android.R


abstract class BaseActivity : AppCompatActivity() {


    private lateinit var mToolbar: Toolbar


    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        initToolbar()
    }

    private fun initToolbar() {
        mToolbar = findViewById<Toolbar>(R.id.mToolBar)

        mToolbar.title = ""
        setSupportActionBar(mToolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }

        mToolbar.setNavigationOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_righ)
        }

        mToolbar.navigationIcon!!.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
    }



}