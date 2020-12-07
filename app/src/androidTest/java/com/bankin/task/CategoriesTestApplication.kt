package com.mvvmclean.trendingrepos

import com.bankin.task.BankinApp
import com.bankin.task.categories.di.component.AppComponent
import com.bankin.task.categories.di.component.DaggerAppComponent


class CategoriesTestApplication : BankinApp() {

    override fun onCreate() {
        super.onCreate()
        getApplicationComponent().inject(this)
    }

    override fun getApplicationComponent(): AppComponent {
        return DaggerAppComponent.factory()
            .create(applicationContext)
    }

}