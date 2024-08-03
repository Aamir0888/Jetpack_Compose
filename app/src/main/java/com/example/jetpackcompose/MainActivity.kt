package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.screen.CartScreen
import com.example.jetpackcompose.screen.DashboardScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.PizzaDetailsScreen
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.utilities.ProfileViewModel
import com.example.jetpackcompose.utilities.SharedViewModel

class MainActivity : ComponentActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = NavigationRoute.DASHBOARD_SCREEN,
//                enterTransition = {
//                    slideInHorizontally(initialOffsetX = { it }) + fadeIn(animationSpec = tween(700))
//                },
//                exitTransition = {
//                    slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(animationSpec = tween(700))
//                },
//                popEnterTransition = {
//                    slideInHorizontally(initialOffsetX = { -it }) + fadeIn(animationSpec = tween(700))
//                },
//                popExitTransition = {
//                    slideOutHorizontally(targetOffsetX = { it }) + fadeOut(animationSpec = tween(700))
//                },
                builder = {
                    composable(NavigationRoute.DASHBOARD_SCREEN) {
                        DashboardScreen(profileViewModel, navController, sharedViewModel)
                    }
                    composable(NavigationRoute.HOME_SCREEN) {
                        HomeScreen(navController, sharedViewModel)
                    }
                    composable(NavigationRoute.PIZZA_DETAILS_SCREEN) {
                        PizzaDetailsScreen(sharedViewModel, navController)
                    }
                    composable(NavigationRoute.CART_SCREEN) {
                        CartScreen()
                    }
                })
        }
    }
}