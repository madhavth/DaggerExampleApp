package com.madhavth.daggermvvmapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.data.models.Todos
import com.madhavth.daggermvvmapp.data.service.TestApiService
import retrofit2.Retrofit
import retrofit2.create
import timber.log.Timber
import javax.inject.Inject

class MyRepository @Inject constructor()
{
    @Inject lateinit var retrofit: Retrofit
    private lateinit var testApi: TestApiService

    private var _listTodos=  MutableLiveData<List<Todos>>()
    val listTodos: LiveData<List<Todos>>
        get() = _listTodos

    fun getData():String
    {
        return "data"
    }

    suspend fun getAllTodos()
    {
        try
        {
            _listTodos.value = testApi.getTodos().await()
        }
        catch (e: Exception)
        {
            Timber.d("error occured while fetching list ${e.localizedMessage}")
        }
    }


    init {
        testApi = retrofit.create(TestApiService::class.java)
        var appComponent  = MyApplication.appComponent
        appComponent.inject(this)
    }

}