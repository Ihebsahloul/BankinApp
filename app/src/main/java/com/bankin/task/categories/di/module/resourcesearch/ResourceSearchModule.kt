package com.bankin.task.di.modules.trendingreposearch

import com.bankin.data.db.ResourceRepoDao
import com.bankin.data.model.ResourceRepoApiService
import com.bankin.data.source.ResourceSearchDataSource
import com.bankin.data.utils.SharedPrefsHelper
import com.bankin.domain.usecase.ResourceRepositoryUseCase
import com.stootie.data.repository.CategoriesRepository
import dagger.Module
import dagger.Provides

@Module
open class ResourceSearchModule {
    @Provides
    fun provideCategorySearchUseCase(
        trendingRepoSearchRepository: CategoriesRepository
    ): ResourceRepositoryUseCase =
        ResourceRepositoryUseCase(trendingRepoSearchRepository)

    @Provides
    fun provideCategorySearchDataSource(
            apiService: ResourceRepoApiService,
            trendingRepoDao: ResourceRepoDao,
            sharedPrefsHelper: SharedPrefsHelper
    ): ResourceSearchDataSource =
            ResourceSearchDataSource(apiService, trendingRepoDao, sharedPrefsHelper)
}


