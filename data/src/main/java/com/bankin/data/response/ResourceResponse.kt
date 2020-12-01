package com.bankin.data.response

import com.bankin.data.model.ParentEntity

data class ResourceResponse(

    val custom: Boolean,
    val id: Int,
    val is_deleted: Boolean,
    val name: String,
    val other: Boolean,
    val parent: ParentEntity,
    val resource_type: String,
    val resource_uri: String
)