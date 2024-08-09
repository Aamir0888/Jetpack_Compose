package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetpackcompose.R
import com.example.jetpackcompose.api.ApiState
import com.example.jetpackcompose.api_models.response.CommonResponse
import com.example.jetpackcompose.api_models.response.LoginResponse
import com.example.jetpackcompose.common.AppIconButton
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.common.SpacerWidth
import com.example.jetpackcompose.data.BottomNavItem
import com.example.jetpackcompose.data.DrawerItem
import com.example.jetpackcompose.data.drawerItemList
import com.example.jetpackcompose.ui.theme.BackgroundColor
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.utilities.NavigationRoute
import com.example.jetpackcompose.utilities.PreferencesHelper
import com.example.jetpackcompose.view_models.MainViewModel
import com.example.jetpackcompose.view_models.PizzaViewModel
import com.example.jetpackcompose.view_models.ProfileViewModel
import com.example.jetpackcompose.view_models.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val mainViewModel = hiltViewModel<MainViewModel>()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Cart", Icons.Default.ShoppingCart),
        BottomNavItem("Favorite", Icons.Default.Favorite),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    val logoutResponse by mainViewModel.logoutResponse

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor),
        scaffoldState = scaffoldState,
        topBar = {
            CustomTopBar(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            })
        },
        drawerContent = { DrawerContent(profileViewModel, onItemSelected = {
            selectedIndex = it
            scope.launch {
                scaffoldState.drawerState.close()
            }
        }, onLogoutClick = {
            Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show()
            mainViewModel.logout(PreferencesHelper.getString(PreferencesHelper.TOKEN)!!, PreferencesHelper.getString(PreferencesHelper.USER_ID)!!)
        }) },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Blue
            ) {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        alwaysShowLabel = true,
                    )
                }
            }
        },
        content = { it
            when (selectedIndex) {
                0 -> HomeScreen(navController)
                1 -> CartScreen()
                2 -> FavoriteScreen()
                3 -> ProfileScreen()
            }
        })

    when (logoutResponse) {
        is ApiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.CircularProgressIndicator()
            }
        }
        is ApiState.Success -> {
            val logoutResponse = (logoutResponse as ApiState.Success<CommonResponse>).data
            Toast.makeText(context, logoutResponse.message, Toast.LENGTH_SHORT).show()
            PreferencesHelper.setBoolean(PreferencesHelper.IS_LOGIN, false)
            navController.navigate(NavigationRoute.LOGIN_SCREEN) {
                popUpTo(NavigationRoute.DASHBOARD_SCREEN)
                { inclusive = true }
            }
        }
        is ApiState.Error -> {
            val error = (logoutResponse as ApiState.Error).message
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
        ApiState.Default -> {}
    }

    // Handle back button press
    BackHandler(enabled = scaffoldState.drawerState.isOpen) {
        scope.launch {
            // Close the drawer if it's open
            scaffoldState.drawerState.close()
        }
    }
}

@Composable
fun CustomTopBar(onClick: () -> Unit) {
    Box(
        modifier = Modifier.background(RedColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                AppIconButton(icon = R.drawable.menu, onClick = {
                    onClick()
                })
                SpacerWidth()
                Text(
                    text = "Pizza", style = TextStyle(
                        color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.W600
                    )
                )
            }
            AppIconButton(size = 32.dp, icon = R.drawable.more, onClick = {

            })
        }
    }
}

@Composable
fun DrawerContent(viewModel: ProfileViewModel, onItemSelected: (Int) -> Unit, onLogoutClick: () -> Unit) {
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .height(70.dp)
            )
            SpacerHeight()
            Text(
                text = name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
            )
            SpacerHeight(2.dp)
            Text(
                text = email,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400
                ),
            )
        }
        SpacerHeight()
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray, thickness = 0.5.dp)
        SpacerHeight(15.dp)
        LazyColumn {
            items(drawerItemList) {
                DrawerSingleItem(it, onItemSelected = { onItemSelected->
                    onItemSelected(onItemSelected)
                })
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray, thickness = 0.5.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .clickable {
                    onLogoutClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            SpacerWidth()
            Text(
                text = "Logout",
                style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W500)
            )
        }
    }
}

@Composable
fun DrawerSingleItem(drawerItem: DrawerItem, onItemSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onItemSelected(drawerItem.index)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = drawerItem.icon,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        SpacerWidth()
        Text(
            text = drawerItem.title,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W500)
        )
    }
}