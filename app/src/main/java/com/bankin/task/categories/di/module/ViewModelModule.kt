package com.mvvmclean.trendingrepos.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bankin.task.categories.CategoryRepositoryViewModel
import com.bankin.task.categories.di.component.ViewModelKey
import com.bankin.task.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(CategoryRepositoryViewModel::class)
    abstract fun bindCharacterSearchViewModel(trendingRepositoryViewModel: CategoryRepositoryViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}