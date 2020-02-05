package com.madhavth.daggermvvmapp.dagger.main

import androidx.lifecycle.ViewModelProvider
import com.madhavth.daggermvvmapp.dagger.app.AppComponent
import com.madhavth.daggermvvmapp.ui.MainActivity
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent
{
    fun inject(mainActivity: MainActivity)
    fun inject(viewModel: MainViewModel)
    fun getFactory(factory: ViewModelProvider.Factory): ViewModelProvider.Factory
}