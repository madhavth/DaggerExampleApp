package com.madhavth.daggermvvmapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.R
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import com.madhavth.daggermvvmapp.databinding.ActivityMainBinding
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import com.madhavth.daggermvvmapp.viewModels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: MyRepository

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        val appComponent = MyApplication.appComponent
        appComponent.inject(this)

        val viewModelFactory = ViewModelFactory(application, repo)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)


        mainViewModel.getData()

    }
}