package com.example.jetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.common.AppIconButton
import com.example.jetpackcompose.common.SpacerHeight
import com.example.jetpackcompose.common.SpacerWidth
import com.example.jetpackcompose.data.BottomNavItem
import com.example.jetpackcompose.data.DrawerItem
import com.example.jetpackcompose.data.Pizza
import com.example.jetpackcompose.data.drawerItemList
import com.example.jetpackcompose.data.pizzaList
import com.example.jetpackcompose.ui.theme.BackgroundColor
import com.example.jetpackcompose.ui.theme.DarkBlackColor
import com.example.jetpackcompose.ui.theme.LightGrayColor
import com.example.jetpackcompose.ui.theme.RedColor
import com.example.jetpackcompose.ui.theme.YellowColor
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Cart", Icons.Default.ShoppingCart),
        BottomNavItem("Profile", Icons.Default.Person)
    )

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
        drawerContent = { DrawerContent() },
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
        content = {
            it
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> CartScreen()
                2 -> ProfileScreen()
            }
        })
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
fun DrawerContent() {
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
                text = "Aamir Khan",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )
            )
            SpacerHeight(2.dp)
            Text(
                text = "aamirkhan@gmail.com",
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
                DrawerSingleItem(it)
            }
        }
    }
}

@Composable
fun DrawerSingleItem(drawerItem: DrawerItem) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                Toast
                    .makeText(context, "${drawerItem.title} clicked", Toast.LENGTH_SHORT)
                    .show()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = drawerItem.icon,
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        SpacerWidth()
        Text(
            text = drawerItem.title,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.W500)
        )
    }
}