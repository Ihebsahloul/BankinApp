package com.bankin.data.response

import com.bankin.data.model.ParentEntity
import com.bankin.domain.countries.model.Parent

data class ResourceResponse(

        val custom: Boolean?,
        val id: Int?,
        val is_deleted: Boolean?,
        val name: String?,
        val other: Boolean?,
        val parent: Parent?,
        val resource_type: String?,
        val resource_uri: String?
)