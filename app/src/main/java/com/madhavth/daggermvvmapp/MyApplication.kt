package com.madhavth.daggermvvmapp

import android.app.Application
import android.content.Context
import com.madhavth.daggermvvmapp.dagger.app.AppComponent
import com.madhavth.daggermvvmapp.dagger.app.AppModules
import com.madhavth.daggermvvmapp.dagger.app.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class MyApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    companion object {
        var ctx: Context? = null
        private lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        init()
    }


    private fun init()
    {
        applicationScope.launch {
            appComponent =  initAppComponent()
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initAppComponent(): AppComponent
    {
        appComponent = DaggerAppComponent
            .builder()
            .appModules(AppModules(""))
            .build()

        return appComponent
    }
}
