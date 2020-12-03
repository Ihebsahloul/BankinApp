package com.bankin.data.mappers

import com.bankin.data.model.ResourceEntity
import com.bankin.domain.countries.model.Resource


internal fun ResourceEntity.toDomain(): Resource {
    return Resource(
            this.id,
            this.resource_uri,
            this.resource_type,
            this.name,
            this.parent,
            this.custom,
            this.other,
            this.is_deleted





    )
}