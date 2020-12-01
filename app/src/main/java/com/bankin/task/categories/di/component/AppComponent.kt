package com.bankin.task.categories.di.component

import android.content.Context
import com.bankin.task.BankinApp
import com.bankin.task.di.modules.ActivityBuilderModule
import com.bankin.task.di.modules.ApplicationModule
import com.bankin.task.di.modules.ResourceRepoApiModule
import com.mvvmclean.trendingrepos.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Core Application Dagger Component
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ResourceRepoApiModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: BankinApp)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}

