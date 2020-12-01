package com.bankin.task.mappers

import com.bankin.domain.countries.model.Resource
import com.bankin.task.models.ResourceUiModel


fun Resource.toPresentation(): ResourceUiModel {
    return ResourceUiModel(
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