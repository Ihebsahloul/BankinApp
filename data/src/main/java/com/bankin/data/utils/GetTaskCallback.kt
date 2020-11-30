package com.bankin.data.utils

interface GetTaskCallback<T> {
    fun onTaskLoaded(loadedTask: T)
    fun onDataNotAvailable()
}