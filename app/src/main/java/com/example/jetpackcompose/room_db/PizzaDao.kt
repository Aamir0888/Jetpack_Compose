package com.example.jetpackcompose.room_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPizza(product: PizzaEntity)

    @Update
    suspend fun updatePizza(pizza: PizzaEntity)

    @Query("DELETE FROM pizza_table WHERE pizzaId = :pizzaId AND status = :status")
    suspend fun deleteByIdStatus(pizzaId: Int, status: String)

    @Query("SELECT * FROM pizza_table WHERE status = :status")
    fun getProductsByStatus(status: String): Flow<List<PizzaEntity>>
}