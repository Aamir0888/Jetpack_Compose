package com.example.jetpackcompose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.room_db.PizzaDao
import com.example.jetpackcompose.room_db.PizzaEntity
import com.example.jetpackcompose.utilities.STATICS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PizzaViewModel(private val pizzaDao: PizzaDao): ViewModel() {
    private fun getPizzaByStatus(status: String): StateFlow<List<PizzaEntity>> {
        return pizzaDao.getProductsByStatus(status)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    // StateFlows for managing and observing pizza entities based on their status
    val cartItems: StateFlow<List<PizzaEntity>> = getPizzaByStatus(STATICS.CART)
    val favoriteItems: StateFlow<List<PizzaEntity>> = getPizzaByStatus(STATICS.FAVORITE)

    fun insertPizza(pizza: PizzaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDao.insertPizza(pizza)
        }
    }

    fun deletePizzaByIdStatus(pizzaId: Int, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDao.deleteByIdStatus(pizzaId, status)
        }
    }
}