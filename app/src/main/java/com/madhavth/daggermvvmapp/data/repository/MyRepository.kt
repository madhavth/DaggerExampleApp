package com.madhavth.daggermvvmapp.data.repository

import retrofit2.Retrofit
import javax.inject.Inject

class MyRepository @Inject constructor()
{
    @Inject lateinit var retrofit: Retrofit

    fun getData():String
    {
        return "data"
    }

}