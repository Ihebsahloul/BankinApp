package com.bankin.task.mappers

import com.bankin.domain.countries.model.Resource
import com.bankin.task.models.ResourceCategoryUiModel


fun Resource.toPresentation(): ResourceCategoryUiModel {
    return ResourceCategoryUiModel(
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