package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.api.ApiState
import com.example.jetpackcompose.api_models.request.LoginModel
import com.example.jetpackcompose.api_models.response.LoginResponse
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.view_models.MainViewModel
import javax.inject.Inject

@Composable
fun LoginScreen(navController: NavHostController, mainViewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    val loginState by mainViewModel.loginResponse

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    when (loginState) {
        is ApiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ApiState.Success -> {
            val loginResponse = (loginState as ApiState.Success<LoginResponse>).data
            Toast.makeText(context, loginResponse.message, Toast.LENGTH_SHORT).show()
            navController.navigate(NavigationRoute.DASHBOARD_SCREEN)
            LaunchedEffect(Unit) {
                mainViewModel.resetLoginState()
            }
        }
        is ApiState.Error -> {
            val error = (loginState as ApiState.Error).message
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            LaunchedEffect(Unit) {
                mainViewModel.resetLoginState()
            }
        }
        ApiState.Default -> { }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login", style = TextStyle(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.W500,
                fontSize = 25.sp
            ), modifier = Modifier.padding(top = 22.dp)
        )

        Column(modifier = Modifier.weight(0.5f), verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.W400, color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 18.sp, fontWeight = FontWeight.W400, color = Color.Black
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (passwordVisible) R.drawable.visibility else R.drawable.visibility_off
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        modifier = Modifier.padding(end = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = icon),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Column(modifier = Modifier.weight(0.5f), verticalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    if (username.isEmpty()) {
                        Toast.makeText(context, "Username is empty", Toast.LENGTH_SHORT).show()
                    } else if (password.isEmpty()){
                        Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show()
                    } else{
                        val loginModel = LoginModel(username, password)
                        mainViewModel.login(loginModel)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RedColor, contentColor = Color.White
                )
            ) {
                Text("Login", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500))
            }
        }
    }
}