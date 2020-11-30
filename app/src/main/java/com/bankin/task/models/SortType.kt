package com.mvvmclean.trendingrepos.models

sealed class SortType {
    object SortByName : SortType()
    object SortByValue : SortType()
}