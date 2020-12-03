package com.bankin.task.mappers

import com.bankin.domain.countries.model.Resource
import com.bankin.task.models.ResourceUiModel


fun Resource.toPresentation(): ResourceUiModel {
    return ResourceUiModel(
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