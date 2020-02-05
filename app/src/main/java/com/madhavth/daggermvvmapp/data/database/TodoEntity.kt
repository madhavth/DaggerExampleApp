package com.madhavth.daggermvvmapp.data.database

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.madhavth.daggermvvmapp.data.models.Todos

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val userId: Int,
    val title: String,
    val completed: Boolean
)


fun List<TodoEntity>.toBaseModel(): List<Todos>
{
    return this.map {
        val todo = Todos(it.userId,it.id?:0,it.title,it.completed)
        todo
    }
}