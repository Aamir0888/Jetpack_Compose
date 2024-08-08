package com.example.jetpackcompose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.room_db.PizzaDao
import com.example.jetpackcompose.room_db.PizzaEntity
import com.example.jetpackcompose.utilities.STATICS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PizzaViewModel @Inject constructor (private val pizzaDao: PizzaDao) : ViewModel() {
    val cartItems: StateFlow<List<PizzaEntity>> = getPizzaByStatus(STATICS.CART)
    val favoriteItems: StateFlow<List<PizzaEntity>> = getPizzaByStatus(STATICS.FAVORITE)

    private fun getPizzaByStatus(status: String): StateFlow<List<PizzaEntity>> {
        return pizzaDao.getProductsByStatus(status)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }

    fun insertPizza(pizza: PizzaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDao.insertPizza(pizza)
        }
    }

    fun updatePizza(pizza: PizzaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDao.updatePizza(pizza)
        }
    }

    fun deletePizzaByIdStatus(pizzaId: Int, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pizzaDao.deleteByIdStatus(pizzaId, status)
        }
    }
}