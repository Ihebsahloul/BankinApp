package com.bankin.task

import android.app.Application
import com.bankin.task.dagger.component.AppComponent
import com.bankin.task.dagger.component.DaggerAppComponent
import com.bankin.task.dagger.module.AppModule

class BankinApp : Application() {
  val applicationComponent: AppComponent by lazy {
    DaggerAppComponent.builder()
      .appModule(AppModule(this))
      .build()
  }

  override fun onCreate() {
    super.onCreate()
    initInjector()
  }

  private fun initInjector() {
    applicationComponent.inject(this)
  }

}
