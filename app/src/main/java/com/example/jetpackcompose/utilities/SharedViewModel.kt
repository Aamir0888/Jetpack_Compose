package com.example.jetpackcompose.utilities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.data.Pizza

class SharedViewModel: ViewModel() {
    var pizza by mutableStateOf<Pizza?>(null)
        private set

    fun addPizza(newPizza: Pizza) {
        pizza = newPizza
    }
}