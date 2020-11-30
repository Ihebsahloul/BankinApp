package com.bankin.task.mappers

import com.bankin.domain.countries.model.Resource
import com.mvvmclean.trendingrepos.models.ResourceRepositoryUiModel


fun Resource.toPresentation(): ResourceRepositoryUiModel {
    return ResourceRepositoryUiModel(
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