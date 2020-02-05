package com.madhavth.daggermvvmapp.dagger.app

import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.madhavth.daggermvvmapp.MyApplication
import com.madhavth.daggermvvmapp.data.adapter.TodoListRecyclerViewAdapter
import com.madhavth.daggermvvmapp.data.database.MyDatabase
import com.madhavth.daggermvvmapp.data.database.TodoDao
import com.madhavth.daggermvvmapp.data.repository.MyRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModules(baseUrl: String) {
    var baseURL: String= baseUrl

    @Provides
    fun provideRepository(): MyRepository
    {
        return MyRepository()
    }

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory
    {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideCoroutineAdapter(): CoroutineCallAdapterFactory
    {
        return CoroutineCallAdapterFactory()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        callAdapter: CoroutineCallAdapterFactory
                        ): Retrofit
    {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @Singleton
    @Provides
    fun createRoomDB(): MyDatabase
    {
        val myDatabase = Room.databaseBuilder(MyApplication.ctx!!,
            MyDatabase::class.java,
            "my-db")
            .build()
        return myDatabase
    }


    @Singleton
    @Provides
    fun providesTodoDao(myDatabase: MyDatabase): TodoDao
    {
        return myDatabase.todoDao()
    }


    @Singleton
    @Provides
    fun provideTodoListAdapter(): TodoListRecyclerViewAdapter
    {
        return TodoListRecyclerViewAdapter()
    }

}