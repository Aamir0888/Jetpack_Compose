package com.example.jetpackcompose.di

import com.example.jetpackcompose.api_models.request.LoginModel
import com.example.jetpackcompose.api_models.response.CommonResponse
import com.example.jetpackcompose.api_models.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("employee/signIn")
    suspend fun login(@Body loginModel: LoginModel): Response<LoginResponse>

    @GET("employee/signout/{id}")
    suspend fun logout(
        @Header("Authorization") authorization: String,
        @Path("id") id: String
    ): Response<CommonResponse>
}