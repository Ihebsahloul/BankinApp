package com.mvvmclean.trendingrepos.data.models.response.search

import com.bankin.data.response.ResourceResponse
import com.bankin.domain.countries.model.Pagination
import com.bankin.domain.countries.model.Resource

data class ResourceSearchResponse(
        val pagination: Pagination,
        val resources: List<ResourceResponse>
)