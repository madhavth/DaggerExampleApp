package com.madhavth.daggermvvmapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.R
import com.madhavth.daggermvvmapp.data.adapter.TodoListRecyclerViewAdapter
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.database.toBaseModel
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import com.madhavth.daggermvvmapp.databinding.ActivityMainBinding
import com.madhavth.daggermvvmapp.viewModels.MainViewModel
import com.madhavth.daggermvvmapp.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo_list.*
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var job= Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main+ job)


    @Inject
    lateinit var todoListAdapter: TodoListRecyclerViewAdapter



    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)

        val appComponent = MyApplication.appComponent
        appComponent.inject(this)

        val viewModelFactory = ViewModelFactory(application)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)


        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this


        //get todoList from api
        coroutineScope.launch {
            mainViewModel.getTodoLists()
        }


        //set adapter for todolist
        listRecyclerView.adapter =  todoListAdapter


        //checking for changes using the livedata list obtained from room
        mainViewModel.todoList?.observe(this, Observer {

            if(it!=null)
            {
                if(mainViewModel.toggleList.value!!)
                todoListAdapter.submitList(it.toBaseModel())
                else
                    todoListAdapter.submitList(mainViewModel.listTodos.value)
            }

            if(mainViewModel.doneShowingUpdateListTodos.value == false)
            {
                todoListAdapter.submitList(it.toBaseModel())
                Timber.d("list Updated Size- ${it.size} and values $it")
                Toast.makeText(applicationContext
                    , "list Updated size ${it.size}",
                    Toast.LENGTH_LONG).show()

                mainViewModel.doneShowingUpdateListToast()
            }
        })



        //inserting into room db
        btnAddToDB.setOnClickListener {
            coroutineScope.launch {
                mainViewModel.insertTodo()
            }
        }


        //deleting all from db
        btnDeleteAll.setOnClickListener {
            coroutineScope.launch {
                mainViewModel.deleteAllTodo()
            }
        }


        //toggle list
        btnToggleList.setOnClickListener {

            if(mainViewModel.toggleList.value!!)
            {
                Timber.d("listTodos ${mainViewModel.listTodos.value}")
                todoListAdapter.submitList(mainViewModel.listTodos.value)
                mainViewModel.toggleList()
                btnToggleList.text = "Show Room List"
            }

            else
            {
                Timber.d("showing Room Todos: ${mainViewModel.todoList?.value}")
                todoListAdapter.submitList(mainViewModel.todoList?.value?.toBaseModel())
                mainViewModel.toggleList()
                btnToggleList.text = "Show Retrofit List"
            }

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}