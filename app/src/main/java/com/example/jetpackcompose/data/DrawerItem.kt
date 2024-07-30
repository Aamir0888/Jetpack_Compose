package com.example.jetpackcompose.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val title: String, val icon: ImageVector
)

val drawerItemList: List<DrawerItem> = listOf(
    DrawerItem(title = "Home", icon = Icons.Default.Home),
    DrawerItem(title = "Shopping Cart", icon = Icons.Default.ShoppingCart),
    DrawerItem(title = "Notifications", icon = Icons.Default.Notifications),
    DrawerItem(title = "CheckCircle", icon = Icons.Default.CheckCircle),
    DrawerItem(title = "ArrowDropDown", icon = Icons.Default.ArrowDropDown),
    DrawerItem(title = "Refresh", icon = Icons.Default.Refresh),
    DrawerItem(title = "Settings", icon = Icons.Default.Settings),
    DrawerItem(title = "ThumbUp", icon = Icons.Default.ThumbUp)
)