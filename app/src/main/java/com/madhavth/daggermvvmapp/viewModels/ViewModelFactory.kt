package com.madhavth.daggermvvmapp.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.dagger.app.AppComponent
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import javax.inject.Inject

class ViewModelFactory : ViewModelProvider.Factory {

    @Inject
    lateinit var repository: MyRepository

    private lateinit var appComponent: AppComponent

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        appComponent = MyApplication.appComponent
        appComponent.inject(this)

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}