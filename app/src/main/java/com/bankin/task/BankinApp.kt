package com.bankin.task

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.bankin.task.categories.di.component.AppComponent
import com.bankin.task.categories.di.component.DaggerAppComponent

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BankinApp :  MultiDexApplication(), HasAndroidInjector {

  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  private lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = DaggerAppComponent.factory()
      .create(applicationContext)

    getApplicationComponent().inject(this)
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
  }

  open fun getApplicationComponent(): AppComponent = appComponent

  override fun androidInjector(): AndroidInjector<Any> = androidInjector


}