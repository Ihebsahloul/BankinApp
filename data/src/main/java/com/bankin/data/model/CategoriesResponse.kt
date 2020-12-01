package com.bankin.data.model

data class CategoriesResponse(
    val pagination: Pagination,
    val resources: List<ResourceEntity>
)