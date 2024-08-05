package com.example.jetpackcompose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val index: Int, val title: String, val icon: ImageVector
)

val drawerItemList: List<DrawerItem> = listOf(
    DrawerItem(index = 0, title = "Home", icon = Icons.Default.Home),
    DrawerItem(index = 1, title = "Shopping Cart", icon = Icons.Default.ShoppingCart),
    DrawerItem(index = 2, title = "Favorite", icon = Icons.Default.Favorite),
    DrawerItem(index = 3, title = "Profile", icon = Icons.Default.Person)
)