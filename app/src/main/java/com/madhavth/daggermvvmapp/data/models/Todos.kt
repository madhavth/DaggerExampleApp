package com.madhavth.daggermvvmapp.data.models

data class Todos(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)