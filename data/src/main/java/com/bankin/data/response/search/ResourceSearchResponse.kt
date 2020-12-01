package com.mvvmclean.trendingrepos.data.models.response.search

import com.bankin.data.response.ResourceResponse
import com.bankin.domain.countries.model.Resource

data class ResourceSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val data: List<ResourceResponse>
)