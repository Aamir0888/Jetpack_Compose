package com.example.jetpackcompose.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.api.ApiState
import com.example.jetpackcompose.api_models.request.LoginModel
import com.example.jetpackcompose.api_models.response.LoginResponse
import com.example.jetpackcompose.di.ApiService
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class MainViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _loginResponse: MutableStateFlow<ApiState<LoginResponse>> = MutableStateFlow(ApiState.Default)
    val loginResponse: StateFlow<ApiState<LoginResponse>> get() = _loginResponse

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            try {
                _loginResponse.value = ApiState.Loading
                val result = apiService.login(loginModel)
                if (result.isSuccessful && result.body() != null && result.code() == 200) {
                    _loginResponse.value = ApiState.Success(result.body()!!)
                } else if (result.errorBody() != null) {
                    val errorObj = JSONObject(result.errorBody()!!.charStream().readText())
                    val error = errorObj.getString("message")
                    _loginResponse.value = ApiState.Error(error)
                } else {
                    _loginResponse.value = ApiState.Error("An unknown error occurred")
                }
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