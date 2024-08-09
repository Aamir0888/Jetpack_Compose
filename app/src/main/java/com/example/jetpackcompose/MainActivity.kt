package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.CartScreen
import com.example.jetpackcompose.screen.DashboardScreen
import com.example.jetpackcompose.screen.FavoriteScreen
import com.example.jetpackcompose.screen.ForgotPasswordScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.LoginScreen
import com.example.jetpackcompose.screen.OtpScreen
import com.example.jetpackcompose.screen.PizzaDetailsScreen
import com.example.jetpackcompose.screen.ResetPasswordScreen
import com.example.jetpackcompose.screen.SignUpScreen
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.utilities.PreferencesHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = if (PreferencesHelper.getBoolean(PreferencesHelper.IS_LOGIN)) {
                    NavigationRoute.DASHBOARD_SCREEN
                } else {
                    NavigationRoute.LOGIN_SCREEN
                },
                builder = {
                    composable(NavigationRoute.DASHBOARD_SCREEN) {
                        DashboardScreen(navController)
                    }

                    composable(NavigationRoute.HOME_SCREEN) {
                        HomeScreen(navController)
                    }
                    composable(NavigationRoute.PIZZA_DETAILS_SCREEN) {
                        PizzaDetailsScreen(navController)
                    }
                    composable(NavigationRoute.CART_SCREEN) {
                        CartScreen()
                    }
                    composable(NavigationRoute.FAVORITE_SCREEN) {
                        FavoriteScreen()
                    }
                    composable(NavigationRoute.LOGIN_SCREEN) {
                        LoginScreen(navController)
                    }
                    composable(NavigationRoute.FORGOT_PASSWORD_SCREEN){
                        ForgotPasswordScreen(navController)
                    }
                    composable(NavigationRoute.SIGN_UP_SCREEN){
                        SignUpScreen(navController)
                    }
                    composable(NavigationRoute.OTP_SCREEN){
                        OtpScreen(navController)
                    }
                    composable(NavigationRoute.RESET_PASSWORD_SCREEN){
                        ResetPasswordScreen(navController)
                    }
                })
        }
    }
}