package com.bankin.domain.usecase

import com.mvvmclean.trendingrepos.domain.repository.IResourceSearchRepository
import javax.inject.Inject

class ResourceRepositoryUseCase @Inject constructor(
    private val searchRepository: IResourceSearchRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean) =
        searchRepository.searchTrendingRepositories(forceRefresh)
}