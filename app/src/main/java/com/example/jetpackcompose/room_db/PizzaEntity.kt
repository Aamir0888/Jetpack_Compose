package com.example.jetpackcompose.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pizza_table", primaryKeys = ["pizzaId", "status"])
data class PizzaEntity(
    val pizzaId: Int,
    val image: Int,
    val name: String,
    val description: String,
    val price: Int,
    val status: String,
    var items: Int = 1
)
