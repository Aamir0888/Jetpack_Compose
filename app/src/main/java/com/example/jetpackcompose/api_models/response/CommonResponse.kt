package com.example.jetpackcompose.api_models.response

data class CommonResponse(
    val code: Int,
    val error: String,
    val message: String,
    val result: Any
)