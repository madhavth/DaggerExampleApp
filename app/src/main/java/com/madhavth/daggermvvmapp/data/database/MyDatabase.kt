package com.madhavth.daggermvvmapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao

    //why not use this
//    companion object {
//        private var INSTANCE: MyDatabase? = null
//        fun getInstance(context: Context): MyDatabase {
//            if (INSTANCE == null) {
//                INSTANCE = Room.databaseBuilder(
//                    context,
//                    MyDatabase::class.java,
//                    "roomdb"
//                )
//                    .build()
//            }
//
//            return INSTANCE as MyDatabase
//        }
//    }
}