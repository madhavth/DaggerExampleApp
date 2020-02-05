package com.madhavth.daggermvvmapp.dagger.app

import com.google.gson.Gson
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModules constructor(baseUrl: String)
{
    var baseURL: String= ""

    init {
        this.baseURL = baseUrl
    }

    @Provides
    fun provideRepository(): MyRepository
    {
        return MyRepository()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory
    {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun sth()
    {

    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit
    {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(gsonConverterFactory)

            .build()
    }
}