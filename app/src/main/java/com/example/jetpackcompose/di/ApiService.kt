package com.example.jetpackcompose.di

import com.example.jetpackcompose.api_models.request.LoginModel
import com.example.jetpackcompose.api_models.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("employee/signIn")
    suspend fun login(@Body loginModel: LoginModel) : LoginResponse
}