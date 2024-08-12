package com.example.jetpackcompose.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.jetpackcompose.utilities.NavigationRoute
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.theme.RedColor

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Forgot Password", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 5.dp))
        } else {
            Text(text = "", modifier = Modifier.padding(top = 5.dp))
        }

        Spacer(modifier = Modifier.height(26.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = RedColor,
                contentColor = Color.White
            ),
            onClick = {
                if (email.text.isEmpty()) {
                    errorMessage = "Please enter a valid email address"
                } else {
                    errorMessage = ""
                    navController.navigate(NavigationRoute.RESET_PASSWORD_SCREEN)
                }
            }
        ) {
            Text("Next", color = Color.White)
        }
    }
}

@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    // State variables to hold input values
    var email by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Reset Password", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("New Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisibility) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm New Password") },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(id = if (passwordVisibility) R.drawable.visibility else R.drawable.visibility_off),
                        contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Call the reset password handler
                navController.navigate(NavigationRoute.LOGIN_SCREEN) {
                    popUpTo(NavigationRoute.LOGIN_SCREEN) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Password")
        }
    }
}

