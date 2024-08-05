package com.example.jetpackcompose.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.data.Pizza

class SharedViewModel: ViewModel() {
    var pizza by mutableStateOf<Pizza?>(null)
        private set

    var totalAmount by mutableIntStateOf(0)
        private set

    fun addPizza(newPizza: Pizza) {
        pizza = newPizza
    }

    fun addAmount(newAmount: Int) {
        totalAmount = newAmount
    }
}