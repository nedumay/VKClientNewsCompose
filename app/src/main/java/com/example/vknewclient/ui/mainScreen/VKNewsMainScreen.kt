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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewclient.navigation.AppNavGraph
import com.example.vknewclient.navigation.rememberNavigationState
import com.example.vknewclient.ui.HomeScreen
import com.example.vknewclient.ui.NavItem
import com.example.vknewclient.ui.comments.CommentsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                // Хранит текущий открытый экран
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavItem.Home,
                    NavItem.Favorites,
                    NavItem.Profile
                )

                items.forEach { item ->
                    // Данная проверка необходима для того, чтобы дочерние экраны привязать к родительским
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            // Добавляем проверку для того, чтобы не переходить заново на тот экран, где уже находимся
                            if (!selected) navigationState.navigateTo(route = item.screen.route)
                        },
                        icon = {
                            val currentRout = navBackStackEntry?.destination?.route
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
            newsFeedPostScreenContent = {
                HomeScreen(
                    paddingValues,
                    onCommentClickListener = {
                        // Передаем маршрут комментариев
                        navigationState.navigateToComments(it)
                    }
                )
            },
            favoritesScreenContent = {
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "Favorites",
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
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
