package com.madhavth.daggermvvmapp.dagger.main

import com.madhavth.daggermvvmapp.dagger.activityScope
import com.madhavth.daggermvvmapp.dagger.app.AppComponent
import com.madhavth.daggermvvmapp.ui.MainActivity
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import dagger.Component


@activityScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent
{
//    fun inject(mainActivity: MainActivity)
//    fun inject(viewModel: MainViewModel)
}