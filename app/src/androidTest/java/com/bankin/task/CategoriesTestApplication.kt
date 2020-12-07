package com.bankin.task

import com.bankin.task.BankinApp
import com.bankin.task.categories.di.component.AppComponent
import com.bankin.task.categories.di.component.DaggerAppComponent
import com.bankin.task.di.DaggerTestApplicationComponent
import dagger.android.HasAndroidInjector


class CategoriesTestApplication : BankinApp(), HasAndroidInjector {

    override fun onCreate() {
        super.onCreate()
        getApplicationComponent().inject(this)
    }

    override fun getApplicationComponent(): AppComponent {
        return DaggerTestApplicationComponent.factory()
            .create(applicationContext)
    }

}