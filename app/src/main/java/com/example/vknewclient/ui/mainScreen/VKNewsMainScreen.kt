
package com.example.vknewclient.ui.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewclient.domain.FeedPost
import com.example.vknewclient.navigation.AppNavGraph
import com.example.vknewclient.navigation.rememberNavigationState
import com.example.vknewclient.ui.HomeScreen
import com.example.vknewclient.ui.NavItem
import com.example.vknewclient.ui.comments.CommentsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                // Хранит текущий открытый экран
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                // Хранит маршрут
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
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackPressed = {
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )
                }
            },
            favoritesScreenContent = {
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "Favorites",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            profileScreenContent = {
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "Profile",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        )
    }

}
