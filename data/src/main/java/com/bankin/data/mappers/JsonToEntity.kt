package com.bankin.data.mappers

import com.bankin.data.model.ResourceEntity
import com.bankin.data.response.ResourceResponse
import com.bankin.domain.countries.model.Resource

internal fun ResourceResponse.toEntity(primaryKey: Int): ResourceEntity {
    return ResourceEntity(
        this.custom,
        this.id,
        this.is_deleted,
        this.name,
        this.other,
        this.parent,
        this.resource_type,
        this.resource_uri
    )
}