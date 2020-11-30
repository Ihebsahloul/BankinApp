package com.mvvmclean.trendingrepos.data.models.response.search

import com.mvvmclean.trendingrepos.data.models.response.TrendingRepoResponse

data class ResourceSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val data: List<TrendingRepoResponse>
)