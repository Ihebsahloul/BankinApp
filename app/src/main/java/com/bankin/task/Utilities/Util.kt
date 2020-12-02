package com.bankin.task.Utilities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.bankin.task.R

fun launchUrl(context: Context, url: String?) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        context.toast(context.getString(R.string.error_no_browser))
    }
}