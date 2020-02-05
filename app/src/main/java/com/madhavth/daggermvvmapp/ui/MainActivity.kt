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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var job= Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main+ job)

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


        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        mainViewModel.getData()

        btnDoSomething.setOnClickListener {
            coroutineScope.launch {
                mainViewModel.getTodoLists()
                tvDemo.text = "fetched List Size is  ${mainViewModel.listTodos.value?.size}"
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}