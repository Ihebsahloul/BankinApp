package com.bankin.task.di

import android.content.Context
import com.bankin.task.categories.di.component.AppComponent
import com.bankin.task.di.modules.ActivityBuilderModule
import com.bankin.task.di.modules.ApplicationModule
import com.mvvmclean.trendingrepos.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        FakeCategoriesApiModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface TestApplicationComponent : AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestApplicationComponent
    }

}