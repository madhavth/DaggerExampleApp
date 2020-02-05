package com.madhavth.daggermvvmapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.R
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.models.Todos
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import com.madhavth.daggermvvmapp.databinding.ActivityMainBinding
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import com.madhavth.daggermvvmapp.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var job= Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main+ job)

    @Inject
    lateinit var repo: MyRepository

    @Inject
    lateinit var todoDao: TodoDao

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

                mainViewModel.doDatabaseRelatedStuff()
            }
        }

        mainViewModel.todoList.observe(this, Observer {

            if(mainViewModel.doneShowingUpdateListTodos.value == false)
            {
                Timber.d("list Updated Size- ${it.size} and values $it")
                Toast.makeText(applicationContext
                    , "list Updated size ${it.size}",
                    Toast.LENGTH_LONG).show()

                mainViewModel.doneShowingUpdateListToast()
            }
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}