package com.stootie.data.repository

import com.bankin.data.mappers.toDomain
import com.bankin.data.source.ResourceSearchDataSource
import com.bankin.domain.countries.model.Resource
import com.mvvmclean.trendingrepos.domain.repository.IResourceSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val ResourceRepoSearchDataSource: ResourceSearchDataSource
) : IResourceSearchRepository {

    override suspend fun searchTrendingRepositories(forceRefresh: Boolean): Flow<List<Resource>> {
        return ResourceRepoSearchDataSource.query(forceRefresh)
            .map { it.map { results -> results.toDomain() } }
    }

}