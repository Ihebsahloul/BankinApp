
package com.bankin.task.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bankin.task.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initComponents(savedInstanceState)
    }

    protected fun showAlertMessage(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.app_name))
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    protected fun enableHomeUp(@StringRes title: Int) {
        enableHomeUp(getString(title))
    }

    protected fun enableHomeUp(title: String?) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbarTitle.text = title
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.menu_ic))
        toolbar.setNavigationOnClickListener { supportFinishAfterTransition() }
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initComponents(savedInstanceState: Bundle?)

}