
package com.bankin.task.di.modules

import com.bankin.task.base.BaseActivity
import com.bankin.task.categories.CategorySearchActivity
import com.bankin.task.categories.di.component.ActivityScope
import com.bankin.task.di.modules.base.BaseBindingModule
import com.bankin.task.di.modules.base.BaseModule
import com.bankin.task.di.modules.trendingreposearch.ResourceSearchBindingModule
import com.bankin.task.di.modules.trendingreposearch.ResourceSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [BaseBindingModule::class, ResourceSearchBindingModule::class])
abstract class ActivityBuilderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [BaseModule::class])
    abstract fun provideBaseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ResourceSearchModule::class])
    abstract fun provideSearchActivity(): CategorySearchActivity
}