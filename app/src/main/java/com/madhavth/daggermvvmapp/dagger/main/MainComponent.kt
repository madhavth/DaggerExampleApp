package com.madhavth.daggermvvmapp.dagger.main

import androidx.lifecycle.ViewModelProvider
import com.madhavth.daggermvvmapp.ui.MainActivity
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent
{
    fun inject(mainActivity: MainActivity)
    fun getFactory(factory: ViewModelProvider.Factory): ViewModelProvider.Factory
}