package com.example.jetpackcompose.api

sealed class ApiState<out T> {
    data object Default : ApiState<Nothing>()
    data object Loading : ApiState<Nothing>()
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
}