package com.bankin.data.mappers

import com.bankin.data.model.Parent
import com.bankin.data.model.ResourceEntity
import com.bankin.domain.countries.model.Resource


internal fun ResourceEntity.toDomain(): Resource {
    return Resource(

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