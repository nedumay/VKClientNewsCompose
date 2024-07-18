@file:Suppress("DEPRECATION")

package com.example.vknewclient.ui.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vknewclient.MainViewModel
import com.example.vknewclient.navigation.AppNavGraph
import com.example.vknewclient.navigation.NavigationState
import com.example.vknewclient.navigation.rememberNavigationState
import com.example.vknewclient.ui.HomeScreen
import com.example.vknewclient.ui.NavItem
import com.example.vknewclient.ui.bottomAppBar.TopAppBarPost
import com.example.vknewclient.ui.postcard.PostCardVK


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()

    Scaffold(
        topBar = {
            TopAppBarPost()
        },
        bottomBar = {
            NavigationBar {
                // Хранит текущий открытый экран
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavItem.Home,
                    NavItem.Favorites,
                    NavItem.Profile
                )
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRout == item.screen.route,
                        onClick = { navigationState.navigateTo(route = item.screen.route) },
                        icon = {
                            Icon(
                                imageVector = if (currentRout == item.screen.route) item.selectedIcon
                                else item.unselectedIcon, contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) }
                    )
                }
            }
        }
    ) { paddinValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = { HomeScreen(viewModel, paddinValues) },
            favoritesScreenContent = {
                Text(
                    modifier = Modifier.padding(paddinValues),
                    text = "Favorites",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            profileScreenContent = {
                Text(
                    modifier = Modifier.padding(paddinValues),
                    text = "Profile",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        )
    }

}
