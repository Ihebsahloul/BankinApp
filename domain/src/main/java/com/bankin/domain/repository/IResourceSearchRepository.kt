package com.bankin.domain.repository

import com.bankin.data.model.ResourceEntity
import com.bankin.data.response.ResourceResponse
import com.bankin.domain.countries.model.Resource
import kotlinx.coroutines.flow.Flow

interface IResourceSearchRepository {
    suspend fun searchCategoryRepositories(forceRefresh: Boolean): Flow<List<ResourceEntity>>
}