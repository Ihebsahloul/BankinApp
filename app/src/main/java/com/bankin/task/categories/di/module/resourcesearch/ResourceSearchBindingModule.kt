package com.bankin.task.di.modules.trendingreposearch


import com.mvvmclean.trendingrepos.domain.repository.IResourceSearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ResourceSearchBindingModule {

    @Singleton
    @Binds
    abstract fun bindTrendingRepoRepository(
        trendingRepoSearchRepository: ResourceSearchRepository
    ): IResourceSearchRepository

}