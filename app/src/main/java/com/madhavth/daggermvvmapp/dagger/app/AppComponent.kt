package com.madhavth.daggermvvmapp.dagger.app

import com.madhavth.daggermvvmapp.data.repository.MyRepository
import com.madhavth.daggermvvmapp.ui.MainActivity
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModules::class])
interface AppComponent
{
    fun getRepo(): MyRepository
    fun inject(activity: MainActivity)
    fun inject(repo: MyRepository)
    fun inject(viewModel: MainViewModel)
}