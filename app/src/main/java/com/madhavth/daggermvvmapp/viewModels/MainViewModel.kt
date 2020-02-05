package com.madhavth.daggermvvmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.models.Todos
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: MyRepository): ViewModel()
{
    private lateinit var todoDao: TodoDao
    val todoList = repo.todoList

    private var _simpleData = MutableLiveData<String>()
        val simpleData : LiveData<String>
            get() = _simpleData


    private var _listTodos= MutableLiveData<List<Todos>>()
        val listTodos: LiveData<List<Todos>>
            get() = _listTodos


    private val _doneShowingUpdateListTodos = MutableLiveData<Boolean>()
        val doneShowingUpdateListTodos: LiveData<Boolean>
            get() = _doneShowingUpdateListTodos

    fun getData()
    {
        _simpleData.value =  repo.getData()
    }

    suspend fun clearList()
    {
        repo.clearList()
    }


    suspend fun getTodoLists()
    {
        _listTodos.value = repo.getAllTodos()
        Timber.d("listTodos is ${_listTodos.value}")
    }

    suspend fun doDatabaseRelatedStuff()
    {
        repo.dataBaseStuff()
        showUpdateListToast()
    }

    fun showUpdateListToast()
    {
        _doneShowingUpdateListTodos.value =false
    }


    fun doneShowingUpdateListToast()
    {
        _doneShowingUpdateListTodos.value = true
    }


    init
    {
        _doneShowingUpdateListTodos.value = true
    }

}