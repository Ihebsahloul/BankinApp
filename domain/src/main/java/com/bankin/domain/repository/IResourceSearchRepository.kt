package com.bankin.domain.repository

import com.bankin.domain.countries.model.Resource
import kotlinx.coroutines.flow.Flow

interface IResourceSearchRepository {
    suspend fun searchTrendingRepositories(forceRefresh: Boolean): Flow<List<Resource>>
}