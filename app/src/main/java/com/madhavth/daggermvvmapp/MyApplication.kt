package com.madhavth.daggermvvmapp

import android.app.Application
import android.content.Context
import com.madhavth.daggermvvmapp.dagger.app.AppComponent
import com.madhavth.daggermvvmapp.dagger.app.AppModules
import com.madhavth.daggermvvmapp.dagger.app.DaggerAppComponent

class MyApplication : Application() {
    companion object {
        var ctx: Context? = null
        private lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        appComponent =  initAppComponent()
    }

    fun initAppComponent(): AppComponent
    {
        appComponent = DaggerAppComponent
            .builder()
            .appModules(AppModules(""))
            .build()

        return appComponent
    }
}
