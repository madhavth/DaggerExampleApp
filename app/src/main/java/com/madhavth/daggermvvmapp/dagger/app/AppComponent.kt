package com.madhavth.daggermvvmapp.dagger.app

import com.madhavth.daggermvvmapp.dagger.main.MainComponent
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModules::class], dependencies = [MainComponent::class])
interface AppComponent
{
    fun inject(repo: MyRepository)
}