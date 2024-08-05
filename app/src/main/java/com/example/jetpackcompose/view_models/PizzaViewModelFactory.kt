package com.example.jetpackcompose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcompose.room_db.PizzaDao

class PizzaViewModelFactory(private val pizzaDao: PizzaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PizzaViewModel(pizzaDao) as T
    }
}