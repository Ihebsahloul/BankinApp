package com.bankin.task.di.modules.trendingreposearch


import com.bankin.domain.repository.IResourceSearchRepository
import com.stootie.data.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ResourceSearchBindingModule {

    @Singleton
    @Binds
    abstract fun bindCategoryRepository(
        categorySearchRepository: CategoriesRepository
    ): IResourceSearchRepository

}