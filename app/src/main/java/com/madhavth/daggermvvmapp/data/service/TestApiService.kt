package com.madhavth.daggermvvmapp.data.service

import com.madhavth.daggermvvmapp.data.models.Todos
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET

interface TestApiService {

    @GET("/todos")
    fun getTodos(): Deferred<List<Todos>>

}