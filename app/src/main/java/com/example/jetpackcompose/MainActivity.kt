package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.room_db.PizzaDatabase
import com.example.jetpackcompose.screen.CartScreen
import com.example.jetpackcompose.screen.DashboardScreen
import com.example.jetpackcompose.screen.FavoriteScreen
import com.example.jetpackcompose.screen.HomeScreen
import com.example.jetpackcompose.screen.PizzaDetailsScreen
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.view_models.PizzaViewModel
import com.example.jetpackcompose.view_models.PizzaViewModelFactory
import com.example.jetpackcompose.view_models.ProfileViewModel
import com.example.jetpackcompose.view_models.SharedViewModel

class MainActivity : ComponentActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var pizzaViewModel: PizzaViewModel

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
            sharedViewModel = ViewModelProvider(this)[SharedViewModel::class.java]

            val pizzaDao = PizzaDatabase.getDatabase(this).pizzaDao()
            pizzaViewModel = ViewModelProvider(this, PizzaViewModelFactory(pizzaDao))[PizzaViewModel::class.java]

            var totalAmount by remember { mutableIntStateOf(0) }
            totalAmount = pizzaList.sumOf { pizza ->
                pizza.price * pizza.item
            }
            sharedViewModel.addAmount(totalAmount)

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
                        DashboardScreen(profileViewModel, navController, sharedViewModel, pizzaViewModel)
                    }
                    composable(NavigationRoute.HOME_SCREEN) {
                        HomeScreen(navController, sharedViewModel)
                    }
                    composable(NavigationRoute.PIZZA_DETAILS_SCREEN) {
                        PizzaDetailsScreen(sharedViewModel, navController, pizzaViewModel)
                    }
                    composable(NavigationRoute.CART_SCREEN) {
                        CartScreen(sharedViewModel)
                    }
                    composable(NavigationRoute.FAVORITE_SCREEN) {
                        FavoriteScreen(pizzaViewModel)
                    }
                })
        }
    }
}