package com.madhavth.daggermvvmapp.data.service

import com.madhavth.daggermvvmapp.data.models.Todos
import retrofit2.Call
import retrofit2.http.GET

interface TestApiService {

    @GET("/todos")
    fun getTodos(): Call<List<Todos>>

}