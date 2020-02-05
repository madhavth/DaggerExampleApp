package com.madhavth.daggermvvmapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.madhavth.daggermvvmapp.data.models.Todos

@Dao
interface TodoDao{

    @Insert
    fun insert(todo: TodoEntity)

    @Query("DELETE FROM todos WHERE ID = :id")
    fun deleteTodo(id: Int)


    @Query("UPDATE TODOS SET COMPLETED = NOT COMPLETED")
    fun toggleTodo()


    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<TodoEntity>>


    @Query("SELECT * FROM TODOS WHERE id = :id")
    fun getTodoById(id: Int): TodoEntity

    @Query("DELETE FROM TODOS")
    fun deleteAllTodos()
}