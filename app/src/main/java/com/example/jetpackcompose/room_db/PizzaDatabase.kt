package com.example.jetpackcompose.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [PizzaEntity::class], version = 2, exportSchema = false)
abstract class PizzaDatabase: RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao

    companion object{
        private var INSTANCE : PizzaDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): PizzaDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = databaseBuilder(context, PizzaDatabase::class.java, "my_database")
                        .fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE!!
        }
    }
}