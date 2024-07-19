package com.example.vknewclient.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vknewclient.navigation.Screen

/**
 * Класс для списка BottomNavigation
 */

sealed class NavItem(
    val screen: Screen,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
){

    object Home :
        NavItem(screen = Screen.Home, title = "NewsFeed", Icons.Filled.Home, Icons.Outlined.Home)
    object Favorites : NavItem(screen = Screen.Favorites, title = "Favorite", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder)
    object Profile : NavItem(screen = Screen.Profile, title = "Profile", Icons.Filled.Person, Icons.Outlined.Person)
}

