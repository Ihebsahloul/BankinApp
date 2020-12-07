package com.bankin.task.utils

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(onChangeHandler)
    //Lifecycle owner and observer
    observe(observer, observer)
}