package com.example.jetpackcompose.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.api.ApiState
import com.example.jetpackcompose.api_models.request.LoginModel
import com.example.jetpackcompose.api_models.response.LoginResponse
import com.example.jetpackcompose.di.ApiService
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _loginResponse = mutableStateOf<ApiState<LoginResponse>>(ApiState.Default)
    val loginResponse: State<ApiState<LoginResponse>> get() = _loginResponse

    fun login(loginModel: LoginModel) {
        _loginResponse.value = ApiState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.login(loginModel)
                _loginResponse.value = ApiState.Success(response)
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is HttpException -> {
                        val errorJsonString = e.response()?.errorBody()?.string()
                        parseErrorMessage(errorJsonString)
                    }
                    else -> e.localizedMessage ?: "An unknown error occurred"
                }
                _loginResponse.value = ApiState.Error(errorMessage)
            }
        }
    }

    private fun parseErrorMessage(errorJsonString: String?): String {
        return try {
            val jsonObject = Gson().fromJson(errorJsonString, JsonObject::class.java)
            jsonObject.get("message").asString
        } catch (exception: Exception) {
            "An error occurred"
        }
    }

    fun resetLoginState() {
        _loginResponse.value = ApiState.Default
    }
}