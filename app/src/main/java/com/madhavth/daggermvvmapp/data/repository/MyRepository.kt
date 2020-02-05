package com.madhavth.daggermvvmapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.database.TodoEntity
import com.madhavth.daggermvvmapp.data.models.Todos
import com.madhavth.daggermvvmapp.data.service.TestApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import timber.log.Timber
import javax.inject.Inject

class MyRepository @Inject constructor()
{
    @Inject lateinit var retrofit: Retrofit
    @Inject lateinit var todoDao: TodoDao

    lateinit var todoList: LiveData<List<TodoEntity>>

    private lateinit var testApi: TestApiService

    private var _listTodos=  MutableLiveData<List<Todos>>()
    val listTodos: LiveData<List<Todos>>
        get() = _listTodos


    fun getData():String
    {
        return "data"
    }

    suspend fun getAllTodos(): List<Todos>?
    {
        try
        {
            _listTodos.value = testApi.getTodos().await()
            return _listTodos.value!!
        }
        catch (e: Exception)
        {
            Timber.d("error occured while fetching list ${e.localizedMessage}")
        }

        return null
    }

    suspend fun dataBaseStuff()
    {
        withContext(Dispatchers.IO)
        {
            val todo = TodoEntity(userId = 1,
                title = "new Title",
                completed =  true)

            todoDao.insert(todo)
        }
    }


    suspend fun clearList()
    {
        withContext(Dispatchers.IO)
        {
            todoDao.deleteAllTodos()
        }
    }


    init {
        var appComponent  = MyApplication.appComponent
        appComponent.inject(this)
        testApi = retrofit.create(TestApiService::class.java)
        todoList = todoDao.getAllTodos()
    }

}