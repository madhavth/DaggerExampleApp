package com.madhavth.daggermvvmapp.dagger.main

import android.content.Context
import com.madhavth.daggermvvmapp.dagger.ActivityContext
import com.madhavth.daggermvvmapp.dagger.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule
{
    @Provides
    fun getAppContext(@ApplicationContext context: Context): Context
    {
        return context.applicationContext
    }

    @Provides
    fun getContext(@ActivityContext context: Context): Context
    {
        return context
    }
}