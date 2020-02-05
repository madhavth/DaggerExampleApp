package com.madhavth.daggermvvmapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.models.Todos
import com.madhavth.daggermvvmapp.data.repository.MyRepository
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


    private val _toggleList = MutableLiveData<Boolean>()
        val toggleList: LiveData<Boolean>
            get() = _toggleList


    suspend fun clearList()
    {
        repo.clearList()
    }


    suspend fun getTodoLists()
    {
        _listTodos.value = repo.getAllTodos()
        Timber.d("listTodos is ${_listTodos.value}")
    }

    suspend fun insertTodo()
    {
        repo.insertTodo()
        showUpdateListToast()
    }

    suspend fun deleteAllTodo()
    {
        repo.deleteAllTodo()
    }


    fun showUpdateListToast()
    {
        _doneShowingUpdateListTodos.value =false
    }


    fun doneShowingUpdateListToast()
    {
        _doneShowingUpdateListTodos.value = true
    }


    fun toggleList()
    {
        val check=  _toggleList.value!!
        _toggleList.value = !check
    }


    init
    {
        _doneShowingUpdateListTodos.value = true
        _toggleList.value = true
    }

}