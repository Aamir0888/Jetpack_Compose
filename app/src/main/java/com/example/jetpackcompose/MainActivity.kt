package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.DashboardScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.PizzaDetailsScreen
import com.example.jetpackcompose.utilities.ProfileViewModel

class MainActivity : ComponentActivity() {
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home_screen", builder = {
                composable("dashboard_screen"){
                    DashboardScreen(profileViewModel, navController)
                }
                composable("home_screen"){
                    HomeScreen(navController)
                }
                composable("pizza_details_screen"){
                    PizzaDetailsScreen()
                }
            })
        }
    }
}