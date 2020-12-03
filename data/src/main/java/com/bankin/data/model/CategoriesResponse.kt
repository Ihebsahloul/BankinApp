package com.bankin.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
        @SerializedName("resources")
    val resources: ArrayList<ResourceEntity>,
        @SerializedName("pagination")
    val pagination: PaginationEntity

)