
package com.bankin.task.di.modules

import com.bankin.data.model.ResourceRepoApiService
import com.mvvmclean.trendingrepos.data.api.HttpClient
import com.mvvmclean.trendingrepos.data.api.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
open class ResourceRepoApiModule {

    @Provides
    open fun provideLoggingInterceptor(): HttpLoggingInterceptor = LoggingInterceptor.create()

    @Provides
    open fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return HttpClient.setupOkhttpClient(httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    open fun provideCtegoriesApi(retrofit: Retrofit): ResourceRepoApiService {
        return retrofit.create(ResourceRepoApiService::class.java)
    }

    @Singleton
    @Provides
    open fun provideRetrofit(okHttpClient: OkHttpClient, @Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrl")
    open fun provideBaseUrl(): String = "https://raw.githubusercontent.com/bankin-engineering/challenge-android/master/categories.json"

}